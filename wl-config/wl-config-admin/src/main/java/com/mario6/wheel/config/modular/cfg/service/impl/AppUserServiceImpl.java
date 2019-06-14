package com.mario6.wheel.config.modular.cfg.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mario6.wheel.config.modular.cfg.service.IAppUserService;
import com.mario6.wheel.config.modular.system.dao.AppUserMapper;
import com.mario6.wheel.config.modular.system.model.AppUser;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 应用关联用户 服务实现类
 * </p>
 *
 * @author Mario Luo
 * @since 2019-06-15
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements IAppUserService {

}
