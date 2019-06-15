package com.mario6.wheel.config.modular.cfg.service.impl;

import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mario6.wheel.config.modular.cfg.constant.ConfigStatus;
import com.mario6.wheel.config.modular.cfg.service.IConfigVersionService;
import com.mario6.wheel.config.modular.system.dao.AppMapper;
import com.mario6.wheel.config.modular.system.dao.ConfigVersionMapper;
import com.mario6.wheel.config.modular.system.dao.EnvMapper;
import com.mario6.wheel.config.modular.system.dao.VersionMapper;
import com.mario6.wheel.config.modular.system.model.App;
import com.mario6.wheel.config.modular.system.model.ConfigVersion;
import com.mario6.wheel.config.modular.system.model.Env;
import com.mario6.wheel.config.modular.system.model.Version;
import com.mario6.wheel.config.modular.system.transfer.AppConfigFetchDto;
import com.mario6.wheel.config.modular.system.transfer.AppConfigPullDto;
import com.mario6.wheel.config.modular.system.transfer.ConfigItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mario6.wheel.config.modular.cfg.constant.ConfigStatus.*;

/**
 * 配置项(已发布) 服务实现类
 */
@Service
public class ConfigVersionServiceImpl extends ServiceImpl<ConfigVersionMapper, ConfigVersion> implements IConfigVersionService {

    @Autowired
    private ConfigVersionMapper configVersionMapper;
    @Autowired
    private VersionMapper versionMapper;
    @Autowired
    private AppMapper appMapper;
    @Autowired
    private EnvMapper envMapper;

    @Override
    public AppConfigFetchDto getAppFetchConfig(String appName, String envName) {
        App app = appMapper.selectByName(appName);
        if (app == null) {
            throw new ServiceException(400, "不存在应用" + appName);
        }
        Env env = envMapper.selectByName(envName);
        if (env == null) {
            throw new ServiceException(400, "不存在环境" + envName);
        }


        AppConfigFetchDto fetchDto = new AppConfigFetchDto();
        fetchDto.setAppName(appName);
        fetchDto.setEnvName(envName);
        fetchDto.setVersion(0L);

        // 未发布过配置
        Long version = getLastedVersionId(app.getId(), env.getId());
        if (version == null) {
            return fetchDto;
        }

        List<ConfigVersion> configs = configVersionMapper.selectListByAppAndEnvAndVersion(app.getId(), env.getId(), version);
        List<ConfigItem> items = new ArrayList<>(configs.size());
        for (ConfigVersion configVersion : configs) {
            ConfigItem item = new ConfigItem();
            item.setKey(configVersion.getConfigKey());
            item.setValue(configVersion.getConfigValue());
            items.add(item);
        }

        fetchDto.setVersion(version);
        fetchDto.setConfigItems(items);
        return fetchDto;
    }

    @Override
    public AppConfigPullDto getAppPullConfig(String appName, String envName, Long preVersion) {
        App app = appMapper.selectByName(appName);
        if (app == null) {
            throw new ServiceException(400, "不存在应用" + appName);
        }
        Env env = envMapper.selectByName(envName);
        if (env == null) {
            throw new ServiceException(400, "不存在环境" + envName);
        }

        AppConfigPullDto pullDto = new AppConfigPullDto();
        pullDto.setAppName(appName);
        pullDto.setEnvName(envName);
        pullDto.setChanged(true);
        long version = getLastedVersionId(app.getId(), env.getId());
        pullDto.setVersion(version);

        // 未变化
        if (preVersion >= version || version <= 0) {
            pullDto.setChanged(false);
            return pullDto;
        }

        // 数据分类
        List<ConfigVersion> configs = configVersionMapper.selectListByAppAndEnvAndVersion(app.getId(), env.getId(), version);
        for (ConfigVersion configVersion : configs) {
            ConfigItem item = new ConfigItem();
            item.setKey(configVersion.getConfigKey());
            item.setValue(configVersion.getConfigValue());

            ConfigStatus type = ConfigStatus.of(configVersion.getChangeType());
            if (type == DEL) {
                pullDto.getDeleteConfigItems().add(item);
            } else if (type == NEW) {
                pullDto.getNewConfigItems().add(item);
            } else if (type == UPDATE) {
                pullDto.getUpdateConfigItems().add(item);
            }
        }
        return pullDto;
    }

    private long getLastedVersionId(Integer appId, Integer envId) {
        Version version = versionMapper.selectLastedVersionByAppIdAndEnvId(appId, envId);
        return version != null ? version.getId() : 0;
    }

}
