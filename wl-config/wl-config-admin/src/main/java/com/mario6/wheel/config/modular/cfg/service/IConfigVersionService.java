package com.mario6.wheel.config.modular.cfg.service;

import com.baomidou.mybatisplus.service.IService;
import com.mario6.wheel.config.modular.system.model.ConfigVersion;
import com.mario6.wheel.config.modular.system.transfer.AppConfigFetchDto;
import com.mario6.wheel.config.modular.system.transfer.AppConfigPullDto;

/**
 * 配置项(已发布) 服务类
 */
public interface IConfigVersionService extends IService<ConfigVersion> {

    /**
     * 获取配置项
     */
    AppConfigFetchDto getAppFetchConfig(String appName, String envName);

    /**
     * 获取拉去配置项
     */
    AppConfigPullDto getAppPullConfig(String appName, String envName, Long preVersion);
}
