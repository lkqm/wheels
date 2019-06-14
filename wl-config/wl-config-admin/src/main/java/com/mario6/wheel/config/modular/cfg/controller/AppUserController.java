package com.mario6.wheel.config.modular.cfg.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.mario6.wheel.config.core.log.LogObjectHolder;
import com.mario6.wheel.config.modular.cfg.service.IAppUserService;
import com.mario6.wheel.config.modular.system.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 关联用户控制器
 *
 * @author fengshuonan
 * @Date 2019-06-15 03:17:31
 */
@Controller
@RequestMapping("/appUser")
public class AppUserController extends BaseController {

    private String PREFIX = "/cfg/appUser/";

    @Autowired
    private IAppUserService appUserService;

    /**
     * 跳转到关联用户首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "appUser.html";
    }

    /**
     * 跳转到添加关联用户
     */
    @RequestMapping("/appUser_add")
    public String appUserAdd() {
        return PREFIX + "appUser_add.html";
    }

    /**
     * 跳转到修改关联用户
     */
    @RequestMapping("/appUser_update/{appUserId}")
    public String appUserUpdate(@PathVariable Integer appUserId, Model model) {
        AppUser appUser = appUserService.selectById(appUserId);
        model.addAttribute("item", appUser);
        LogObjectHolder.me().set(appUser);
        return PREFIX + "appUser_edit.html";
    }

    /**
     * 获取关联用户列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return appUserService.selectList(null);
    }

    /**
     * 新增关联用户
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(AppUser appUser) {
        appUserService.insert(appUser);
        return SUCCESS_TIP;
    }

    /**
     * 删除关联用户
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer appUserId) {
        appUserService.deleteById(appUserId);
        return SUCCESS_TIP;
    }

    /**
     * 修改关联用户
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(AppUser appUser) {
        appUserService.updateById(appUser);
        return SUCCESS_TIP;
    }

    /**
     * 关联用户详情
     */
    @RequestMapping(value = "/detail/{appUserId}")
    @ResponseBody
    public Object detail(@PathVariable("appUserId") Integer appUserId) {
        return appUserService.selectById(appUserId);
    }
}
