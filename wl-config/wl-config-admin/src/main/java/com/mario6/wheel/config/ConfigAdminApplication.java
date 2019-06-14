package com.mario6.wheel.config;

import cn.stylefeng.roses.core.config.WebAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 配置中心
 */
@SpringBootApplication(exclude = WebAutoConfiguration.class)
public class ConfigAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigAdminApplication.class, args);
    }
}
