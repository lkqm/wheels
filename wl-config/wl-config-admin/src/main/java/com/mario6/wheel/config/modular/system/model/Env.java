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
 * 环境
 * </p>
 *
 * @author Mario Luo
 * @since 2019-06-15
 */
@TableName("cc_env")
public class Env extends Model<Env> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 环境名称
     */
    @TableField("env_name")
    private String envName;
    /**
     * 环境描述
     */
    @TableField("env_desc")
    private String envDesc;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public String getEnvDesc() {
        return envDesc;
    }

    public void setEnvDesc(String envDesc) {
        this.envDesc = envDesc;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Env{" +
                ", id=" + id +
                ", envName=" + envName +
                ", envDesc=" + envDesc +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                "}";
    }
}
