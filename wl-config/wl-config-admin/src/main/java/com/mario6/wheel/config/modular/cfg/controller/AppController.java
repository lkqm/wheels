package com.mario6.wheel.config.modular.cfg.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.mario6.wheel.config.core.log.LogObjectHolder;
import com.mario6.wheel.config.core.shiro.ShiroKit;
import com.mario6.wheel.config.core.shiro.ShiroUser;
import com.mario6.wheel.config.modular.cfg.service.IAppService;
import com.mario6.wheel.config.modular.system.model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 应用管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-15 03:03:47
 */
@Controller
@RequestMapping("/app")
public class AppController extends BaseController {

    private String PREFIX = "/cfg/app/";

    @Autowired
    private IAppService appService;

    /**
     * 跳转到应用管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "app.html";
    }

    /**
     * 跳转到添加应用管理
     */
    @RequestMapping("/app_add")
    public String appAdd() {
        return PREFIX + "app_add.html";
    }

    /**
     * 跳转到修改应用管理
     */
    @RequestMapping("/app_update/{appId}")
    public String appUpdate(@PathVariable Integer appId, Model model) {
        App app = appService.selectById(appId);
        model.addAttribute("item", app);
        LogObjectHolder.me().set(app);
        return PREFIX + "app_edit.html";
    }

    /**
     * 获取应用管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return appService.selectList(null);
    }

    /**
     * 新增应用管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(App app) {
        ShiroUser user = ShiroKit.getUser();
        app.setCreateUser(user.getName());
        appService.addApp(app);
        return SUCCESS_TIP;
    }

    /**
     * 修改应用管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(App app) {
        appService.updateById(app);
        return SUCCESS_TIP;
    }

    /**
     * 删除应用管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer appId) {
        appService.deleteById(appId);
        return SUCCESS_TIP;
    }

    /**
     * 应用管理详情
     */
    @RequestMapping(value = "/detail/{appId}")
    @ResponseBody
    public Object detail(@PathVariable("appId") Integer appId) {
        return appService.selectById(appId);
    }


    @RequestMapping("/getByUser")
    @ResponseBody
    public Object getUserAppList() {
        ShiroUser user = ShiroKit.getUser();
        List<App> apps = appService.getAppsByUser(user.getId());
        return apps;
    }
}
