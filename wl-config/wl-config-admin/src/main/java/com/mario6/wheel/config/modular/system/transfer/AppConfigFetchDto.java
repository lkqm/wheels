package com.mario6.wheel.config.modular.system.transfer;

import java.io.Serializable;
import java.util.List;

public class AppConfigFetchDto implements Serializable {

    private String appName;
    private String envName;
    private Long version;

    List<ConfigItem> configItems;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<ConfigItem> getConfigItems() {
        return configItems;
    }

    public void setConfigItems(List<ConfigItem> configItems) {
        this.configItems = configItems;
    }
}
