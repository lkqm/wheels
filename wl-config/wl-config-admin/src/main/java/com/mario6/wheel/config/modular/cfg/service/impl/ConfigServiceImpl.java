package com.mario6.wheel.config.modular.cfg.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mario6.wheel.config.modular.cfg.service.IConfigService;
import com.mario6.wheel.config.modular.system.dao.ConfigMapper;
import com.mario6.wheel.config.modular.system.model.Config;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 配置项 服务实现类
 * </p>
 *
 * @author Mario Luo
 * @since 2019-06-15
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
