package com.mario6.wheel.config.modular.cfg.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mario6.wheel.config.modular.cfg.service.IEnvService;
import com.mario6.wheel.config.modular.system.dao.EnvMapper;
import com.mario6.wheel.config.modular.system.model.Env;
import org.springframework.stereotype.Service;

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

}
