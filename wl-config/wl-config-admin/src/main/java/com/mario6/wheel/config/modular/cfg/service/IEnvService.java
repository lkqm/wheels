package com.mario6.wheel.config.modular.cfg.service;

import com.baomidou.mybatisplus.service.IService;
import com.mario6.wheel.config.modular.system.model.Env;

/**
 * 环境相关服务
 */
public interface IEnvService extends IService<Env> {

    void addEnv(Env env);
}
