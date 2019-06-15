package com.mario6.wheel.config.modular.system.transfer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppConfigPullDto implements Serializable {

    private String appName;
    private String envName;
    private Long version;
    private Boolean changed;

    /**
     * 新增的配置
     */
    List<ConfigItem> newConfigItems = new ArrayList<>();

    /**
     * 更新的配置
     */
    List<ConfigItem> updateConfigItems = new ArrayList<>();

    /**
     * 删除的配置
     */
    List<ConfigItem> deleteConfigItems = new ArrayList<>();


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

    public Boolean getChanged() {
        return changed;
    }

    public void setChanged(Boolean changed) {
        this.changed = changed;
    }

    public List<ConfigItem> getNewConfigItems() {
        return newConfigItems;
    }

    public void setNewConfigItems(List<ConfigItem> newConfigItems) {
        this.newConfigItems = newConfigItems;
    }

    public List<ConfigItem> getUpdateConfigItems() {
        return updateConfigItems;
    }

    public void setUpdateConfigItems(List<ConfigItem> updateConfigItems) {
        this.updateConfigItems = updateConfigItems;
    }

    public List<ConfigItem> getDeleteConfigItems() {
        return deleteConfigItems;
    }

    public void setDeleteConfigItems(List<ConfigItem> deleteConfigItems) {
        this.deleteConfigItems = deleteConfigItems;
    }
}
