package com.mario6.wheel.config;

import cn.stylefeng.roses.core.config.WebAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.treeleafj.xdoc.boot.EnableXDoc;

/**
 * 配置中心
 */
@SpringBootApplication(exclude = WebAutoConfiguration.class)
@EnableXDoc
public class ConfigAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigAdminApplication.class, args);
    }
}
