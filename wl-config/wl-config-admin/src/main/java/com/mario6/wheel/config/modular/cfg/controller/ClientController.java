package com.mario6.wheel.config.modular.cfg.controller;

import com.mario6.wheel.config.modular.cfg.service.IConfigVersionService;
import com.mario6.wheel.config.modular.system.transfer.AppConfigFetchDto;
import com.mario6.wheel.config.modular.system.transfer.AppConfigPullDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提供客户端使用接口
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private IConfigVersionService configVersionService;

    @GetMapping("/fetch")
    public AppConfigFetchDto fetch(@RequestParam String appName, @RequestParam String envName) {
        return configVersionService.getAppFetchConfig(appName, envName);
    }

    @GetMapping("/pull")
    public AppConfigPullDto pull(@RequestParam String appName, @RequestParam String envName, @RequestParam Long preVersion) {
        return configVersionService.getAppPullConfig(appName, envName, preVersion);
    }
}
