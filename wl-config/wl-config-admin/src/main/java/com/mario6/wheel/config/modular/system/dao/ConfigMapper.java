package com.mario6.wheel.config.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mario6.wheel.config.modular.system.model.Config;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 配置项 Mapper 接口
 */
public interface ConfigMapper extends BaseMapper<Config> {

    @Select("select * from cc_config where app_id = #{appId} and env_id = #{envId} and config_key=#{configKey}")
    Config selectByKey(@Param("appId") Integer appId, @Param("envId") Integer envId, @Param("configKey") String key);


    @Select("select * from cc_config where app_id = #{appId} and env_id = #{envId}")
    List<Config> selectListByAppAndEnv(@Param("appId") Integer appId, @Param("envId") Integer envId);

    void updateStatusByIds(@Param("ids") List<Integer> ids, @Param("status") Integer status);
}
