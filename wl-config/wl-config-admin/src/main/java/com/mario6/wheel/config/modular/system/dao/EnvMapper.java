package com.mario6.wheel.config.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mario6.wheel.config.modular.system.model.Env;
import org.apache.ibatis.annotations.Select;

/**
 * 环境 Mapper 接口
 */
public interface EnvMapper extends BaseMapper<Env> {

    @Select("select * from cc_env where env_name = #{envName} ")
    Env selectByName(String envName);
}
