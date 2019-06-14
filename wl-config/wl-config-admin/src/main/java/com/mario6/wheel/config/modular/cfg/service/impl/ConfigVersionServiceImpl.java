package com.mario6.wheel.config.modular.cfg.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mario6.wheel.config.modular.cfg.service.IConfigVersionService;
import com.mario6.wheel.config.modular.system.dao.ConfigVersionMapper;
import com.mario6.wheel.config.modular.system.model.ConfigVersion;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 配置项(已发布) 服务实现类
 * </p>
 *
 * @author Mario Luo
 * @since 2019-06-15
 */
@Service
public class ConfigVersionServiceImpl extends ServiceImpl<ConfigVersionMapper, ConfigVersion> implements IConfigVersionService {

}
