package com.mario6.wheel.config.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mario6.wheel.config.modular.system.model.App;
import org.apache.ibatis.annotations.Select;

/**
 * 应用 Mapper 接口
 */
public interface AppMapper extends BaseMapper<App> {

    @Select("select * from cc_app where app_name = #{name} limit 1")
    App selectByName(String name);
}
