package com.mario6.wheel.config.modular.cfg.service.impl;

import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mario6.wheel.config.modular.cfg.service.IAppService;
import com.mario6.wheel.config.modular.system.dao.AppMapper;
import com.mario6.wheel.config.modular.system.model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 应用相关服务实现
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App> implements IAppService {

    @Autowired
    private AppMapper appMapper;

    @Override
    public void addApp(App app) {
        String name = app.getAppName();
        App orgApp = appMapper.selectByName(name);
        if (orgApp != null) {
            throw new ServiceException(500, "重复的应用名");
        }

        App appToSave = new App();
        appToSave.setAppName(app.getAppName());
        appToSave.setAppDesc(app.getAppDesc());
        appToSave.setCreateUser(app.getCreateUser());
        appToSave.setCreateTime(new Date());
        appMapper.insert(appToSave);
    }

    @Override
    public App getAppByName(String name) {
        return appMapper.selectByName(name);
    }

    @Override
    public List<App> getAppsByUser(Integer userId) {
        return appMapper.selectByUser(userId);
    }
}
