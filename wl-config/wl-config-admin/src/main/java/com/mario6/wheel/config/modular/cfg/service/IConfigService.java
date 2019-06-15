package com.mario6.wheel.config.modular.cfg.service;

import com.baomidou.mybatisplus.service.IService;
import com.mario6.wheel.config.modular.system.model.Config;

/**
 * 配置项 服务类
 */
public interface IConfigService extends IService<Config> {

    void addConfig(Config config);

    void deleteConfigById(Integer configId);

    void updateConfigById(Config config);

    void publish(Integer appId, Integer envId);

    void reset(Integer appId, Integer envId);
}
