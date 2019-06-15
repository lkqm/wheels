package com.mario6.wheel.config.modular.cfg.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.mario6.wheel.config.core.log.LogObjectHolder;
import com.mario6.wheel.config.core.shiro.ShiroKit;
import com.mario6.wheel.config.core.shiro.ShiroUser;
import com.mario6.wheel.config.modular.cfg.service.IEnvService;
import com.mario6.wheel.config.modular.system.model.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 环境控制器
 */
@Controller
@RequestMapping("/env")
public class EnvController extends BaseController {

    private String PREFIX = "/cfg/env/";

    @Autowired
    private IEnvService envService;

    /**
     * 跳转到环境首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "env.html";
    }

    /**
     * 跳转到添加环境
     */
    @RequestMapping("/env_add")
    public String envAdd() {
        return PREFIX + "env_add.html";
    }

    /**
     * 跳转到修改环境
     */
    @RequestMapping("/env_update/{envId}")
    public String envUpdate(@PathVariable Integer envId, Model model) {
        Env env = envService.selectById(envId);
        model.addAttribute("item", env);
        LogObjectHolder.me().set(env);
        return PREFIX + "env_edit.html";
    }

    /**
     * 获取环境列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return envService.selectList(null);
    }

    /**
     * 新增环境
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Env env) {
        ShiroUser user = ShiroKit.getUser();
        env.setCreateUser(user.getName());
        envService.addEnv(env);
        return SUCCESS_TIP;
    }

    /**
     * 修改环境
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Env env) {
        Env envToUpdate = new Env();
        envToUpdate.setId(env.getId());
        envToUpdate.setEnvDesc(env.getEnvDesc());
        envService.updateById(envToUpdate);
        return SUCCESS_TIP;
    }

    /**
     * 删除环境
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer envId) {
        envService.deleteById(envId);
        return SUCCESS_TIP;
    }

    /**
     * 环境详情
     */
    @RequestMapping(value = "/detail/{envId}")
    @ResponseBody
    public Object detail(@PathVariable("envId") Integer envId) {
        return envService.selectById(envId);
    }
}
