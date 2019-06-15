package com.mario6.wheel.config.modular.system.transfer;

import java.io.Serializable;

/**
 * 配置项
 */
public class ConfigItem implements Serializable {

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
