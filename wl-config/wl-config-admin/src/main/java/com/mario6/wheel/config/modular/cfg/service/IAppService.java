package com.mario6.wheel.config.modular.cfg.service;

import com.baomidou.mybatisplus.service.IService;
import com.mario6.wheel.config.modular.system.model.App;

import java.util.List;

/**
 * 应用相关服务
 */
public interface IAppService extends IService<App> {

    /**
     * 添加应用
     */
    void addApp(App app);

    App getAppByName(String name);

    List<App> getAppsByUser(Integer userId);
}
