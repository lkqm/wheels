package com.mario6.wheel.config.modular.cfg.constant;

/**
 * 配置状态(草稿箱)
 */
public enum ConfigStatus {

    DEL(-1), OLD(0), NEW(1), UPDATE(2);

    private Integer value;

    ConfigStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static ConfigStatus of(Integer value) {
        for (ConfigStatus status : values()) {
            if (status.getValue().equals(value)) return status;
        }
        return null;
    }


}
