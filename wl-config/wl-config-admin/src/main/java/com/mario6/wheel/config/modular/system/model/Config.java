package com.mario6.wheel.config.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 配置项
 * </p>
 *
 * @author Mario Luo
 * @since 2019-06-15
 */
@TableName("cc_config")
public class Config extends Model<Config> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 应用id
     */
    @TableField("app_id")
    private Integer appId;
    /**
     * 环境id
     */
    @TableField("env_id")
    private Integer envId;
    /**
     * 配置key
     */
    @TableField("config_key")
    private String configKey;
    /**
     * 配置value
     */
    @TableField("config_value")
    private String configValue;
    /**
     * 描述
     */
    @TableField("config_desc")
    private String configDesc;
    /**
     * 状态, -1:删除, 0:已发布, 1:新增, 2:修改
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField("create_user")
    private String createUser;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 更新人
     */
    @TableField("update_user")
    private String updateUser;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getEnvId() {
        return envId;
    }

    public void setEnvId(Integer envId) {
        this.envId = envId;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigDesc() {
        return configDesc;
    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Config{" +
                ", id=" + id +
                ", appId=" + appId +
                ", envId=" + envId +
                ", configKey=" + configKey +
                ", configValue=" + configValue +
                ", configDesc=" + configDesc +
                ", status=" + status +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", updateTime=" + updateTime +
                ", updateUser=" + updateUser +
                "}";
    }
}
