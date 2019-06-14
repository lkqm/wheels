package com.mario6.wheel.config.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 应用关联用户
 * </p>
 *
 * @author Mario Luo
 * @since 2019-06-15
 */
@TableName("cc_app_user")
public class AppUser extends Model<AppUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 应用id
     */
    @TableField("app_id")
    private Integer appId;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;


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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                ", id=" + id +
                ", appId=" + appId +
                ", userId=" + userId +
                "}";
    }
}
