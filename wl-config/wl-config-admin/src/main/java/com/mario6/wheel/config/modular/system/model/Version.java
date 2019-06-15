package com.mario6.wheel.config.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 发布版本
 */
@TableName("cc_version")
public class Version implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
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
     * 发布时间
     */
    @TableField("publish_time")
    private Date publishTime;

    /**
     * 发布人
     */
    @TableField("publish_user")
    private String publishUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(String publishUser) {
        this.publishUser = publishUser;
    }

    @Override
    public String toString() {
        return "Version{" +
                "id=" + id +
                ", appId=" + appId +
                ", envId=" + envId +
                ", publishTime=" + publishTime +
                ", publishUser='" + publishUser + '\'' +
                '}';
    }
}
