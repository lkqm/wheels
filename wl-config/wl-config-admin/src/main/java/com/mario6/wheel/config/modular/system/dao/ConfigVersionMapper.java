package com.mario6.wheel.config.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mario6.wheel.config.modular.system.model.ConfigVersion;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 配置项(已发布) Mapper 接口
 */
public interface ConfigVersionMapper extends BaseMapper<ConfigVersion> {

    @Select("select * from cc_config_version where app_id = #{appId} and env_id = #{envId} and version=#{version}")
    List<ConfigVersion> selectListByAppAndEnvAndVersion(@Param("appId") Integer appId, @Param("envId") Integer envId, @Param("version") Integer version);
}
