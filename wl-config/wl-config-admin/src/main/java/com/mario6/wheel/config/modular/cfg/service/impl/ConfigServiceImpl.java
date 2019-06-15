package com.mario6.wheel.config.modular.cfg.service.impl;

import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mario6.wheel.config.modular.cfg.constant.ConfigStatus;
import com.mario6.wheel.config.modular.cfg.service.IConfigService;
import com.mario6.wheel.config.modular.cfg.service.IConfigVersionService;
import com.mario6.wheel.config.modular.system.dao.ConfigMapper;
import com.mario6.wheel.config.modular.system.dao.ConfigVersionMapper;
import com.mario6.wheel.config.modular.system.dao.VersionMapper;
import com.mario6.wheel.config.modular.system.model.Config;
import com.mario6.wheel.config.modular.system.model.ConfigVersion;
import com.mario6.wheel.config.modular.system.model.Version;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mario6.wheel.config.modular.cfg.constant.ConfigStatus.*;

/**
 * 配置项 服务实现类
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

    @Autowired
    private ConfigMapper configMapper;
    @Autowired
    private IConfigVersionService configVersionService;
    @Autowired
    private VersionMapper versionMapper;
    @Autowired
    private ConfigVersionMapper configVersionMapper;


    @Override
    public void addConfig(Config dto) {
        // 原来无添加, 原来删除变为修改， 原来
        Config orgConfig = configMapper.selectByKey(dto.getAppId(), dto.getEnvId(), dto.getConfigKey());

        boolean isAdd = true;
        if (orgConfig != null) {
            ConfigStatus orgStatus = ConfigStatus.of(orgConfig.getStatus());
            if (orgStatus != DEL) {
                throw new ServiceException(400, "配置项已存在");
            } else {
                isAdd = false;
            }
        }

        Config config = new Config();
        if (isAdd) {
            config.setAppId(dto.getAppId());
            config.setEnvId(dto.getEnvId());
            config.setConfigKey(dto.getConfigKey());
            config.setConfigValue(dto.getConfigValue());
            config.setConfigDesc(dto.getConfigDesc());
            config.setCreateUser(dto.getCreateUser());
            config.setCreateTime(new Date());
            config.setStatus(NEW.getValue());
            configMapper.insert(config);
        } else {
            BeanUtils.copyProperties(orgConfig, config);
            config.setConfigValue(dto.getConfigValue());
            config.setConfigDesc(dto.getConfigDesc());
            config.setUpdateTime(new Date());
            config.setUpdateUser(dto.getCreateUser());
            config.setStatus(UPDATE.getValue());
            configMapper.updateById(config);
        }
    }

    @Override
    public void deleteConfigById(Integer configId) {
        Config config = configMapper.selectById(configId);
        if (config == null) return;

        ConfigStatus status = ConfigStatus.of(config.getStatus());
        if (status == NEW) {
            configMapper.deleteById(configId);
        } else {
            Config configToUpdate = new Config();
            configToUpdate.setId(configId);
            configToUpdate.setStatus(DEL.getValue());
            configMapper.updateById(configToUpdate);
        }
    }

    @Override
    public void updateConfigById(Config dto) {
        Config original = configMapper.selectById(dto.getId());
        if (original == null) return;

        Config config = new Config();
        config.setId(dto.getId());
        config.setConfigValue(dto.getConfigValue());
        config.setConfigDesc(dto.getConfigDesc());
        config.setUpdateTime(new Date());
        config.setUpdateUser(dto.getUpdateUser());

        ConfigStatus status = ConfigStatus.of(original.getStatus());
        if (status == NEW || status == DEL) {
            config.setStatus(status.getValue());
        } else {
            config.setStatus(UPDATE.getValue());
        }
        configMapper.updateById(config);
    }

    @Override
    @Transactional
    public void publish(Integer appId, Integer envId) {
        List<Config> configs = configMapper.selectListByAppAndEnv(appId, envId);
        if (CollectionUtils.isEmpty(configs)) return;


        List<Integer> delIds = new ArrayList<>();
        List<Integer> updateIds = new ArrayList<>();

        // 版本号
        Version version = new Version();
        version.setAppId(appId);
        version.setEnvId(envId);
        version.setPublishTime(new Date());
        versionMapper.insert(version);

        List<ConfigVersion> configVersions = new ArrayList<>(configs.size());
        for (Config config : configs) {
            ConfigVersion configVersion = new ConfigVersion();
            BeanUtils.copyProperties(config, configVersion);
            configVersion.setChangeType(config.getStatus());
            configVersion.setVersion(version.getId());
            configVersions.add(configVersion);

            ConfigStatus status = ConfigStatus.of(config.getStatus());
            if (status == DEL) {
                delIds.add(config.getId());
            } else {
                updateIds.add(config.getId());
            }
        }

        configVersionService.insertBatch(configVersions);
        if (!CollectionUtils.isEmpty(delIds)) {
            configMapper.deleteBatchIds(delIds);
        }
        if (!CollectionUtils.isEmpty(updateIds)) {
            configMapper.updateStatusByIds(updateIds, OLD.getValue());
        }
    }

    @Override
    @Transactional
    public void reset(Integer appId, Integer envId) {
        Version version = versionMapper.selectLastedVersionByAppIdAndEnvId(appId, envId);
        if (version == null) return;

        List<ConfigVersion> configVersions = configVersionMapper.selectListByAppAndEnvAndVersion(appId, envId, version.getId());

        List<Config> configs = new ArrayList<>(configVersions.size());
        for (ConfigVersion configVersion : configVersions) {
            Config config = new Config();
            BeanUtils.copyProperties(configVersion, config);

            config.setId(null);
            config.setStatus(OLD.getValue());
            configs.add(config);
        }

        // 清空
        Config deleteEntity = new Config();
        deleteEntity.setAppId(appId);
        deleteEntity.setEnvId(envId);
        Wrapper<Config> deleteWrapper = new EntityWrapper<>(deleteEntity);
        configMapper.delete(deleteWrapper);

        // 插入
        insertBatch(configs);
    }
}
