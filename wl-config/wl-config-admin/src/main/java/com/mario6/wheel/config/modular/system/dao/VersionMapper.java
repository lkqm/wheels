package com.mario6.wheel.config.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mario6.wheel.config.modular.system.model.Version;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface VersionMapper extends BaseMapper<Version> {

    @Select("select max(id) from cc_version where app_id=#{appId} and env_id = #{envId} ")
    Integer selectMaxByAppIdAndEnvId(@Param("appId") Integer appId, @Param("envId") Integer envId);
}
