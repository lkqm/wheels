package com.mario6.wheel.config.modular.cfg.service.impl;

import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mario6.wheel.config.modular.cfg.service.IEnvService;
import com.mario6.wheel.config.modular.system.dao.EnvMapper;
import com.mario6.wheel.config.modular.system.model.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 环境 服务实现类
 * </p>
 *
 * @author Mario Luo
 * @since 2019-06-15
 */
@Service
public class EnvServiceImpl extends ServiceImpl<EnvMapper, Env> implements IEnvService {

    @Autowired
    private EnvMapper envMapper;

    @Override
    public void addEnv(Env envDto) {
        Env orgEnv = envMapper.selectByName(envDto.getEnvName());
        if (orgEnv != null) {
            throw new ServiceException(500, "重复的环境名称");
        }

        Env env = new Env();
        env.setEnvName(envDto.getEnvName());
        env.setEnvDesc(envDto.getEnvDesc());
        env.setCreateUser(envDto.getCreateUser());
        env.setCreateTime(new Date());
        envMapper.insert(env);
    }
}
