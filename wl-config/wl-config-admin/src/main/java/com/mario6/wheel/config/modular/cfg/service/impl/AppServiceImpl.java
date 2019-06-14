package com.mario6.wheel.config.modular.cfg.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mario6.wheel.config.modular.cfg.service.IAppService;
import com.mario6.wheel.config.modular.system.dao.AppMapper;
import com.mario6.wheel.config.modular.system.model.App;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 应用 服务实现类
 * </p>
 *
 * @author Mario Luo
 * @since 2019-06-15
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App> implements IAppService {

}
