package com.mario6.wheel.config.modular.cfg.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.mario6.wheel.config.core.log.LogObjectHolder;
import com.mario6.wheel.config.core.shiro.ShiroKit;
import com.mario6.wheel.config.modular.cfg.service.IConfigService;
import com.mario6.wheel.config.modular.system.model.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 配置项控制器
 */
@Controller
@RequestMapping("/config")
public class ConfigController extends BaseController {

    private String PREFIX = "/cfg/config/";

    @Autowired
    private IConfigService configService;

    /**
     * 跳转到配置项首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "config.html";
    }

    /**
     * 跳转到添加配置项
     */
    @RequestMapping("/config_add")
    public String configAdd(String appId, String envId, Model model) {
        model.addAttribute("appId", appId);
        model.addAttribute("envId", envId);
        return PREFIX + "config_add.html";
    }

    /**
     * 跳转到修改配置项
     */
    @RequestMapping("/config_update/{configId}")
    public String configUpdate(@PathVariable Integer configId, Model model) {
        Config config = configService.selectById(configId);
        model.addAttribute("item", config);
        LogObjectHolder.me().set(config);
        return PREFIX + "config_edit.html";
    }

    /**
     * 获取配置项列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(Integer appId, Integer envId, String key) {
        Config config = new Config();
        config.setAppId(appId);
        config.setEnvId(envId);
        config.setConfigKey(key);
        Wrapper<Config> wrapper = new EntityWrapper<>(config);
        return configService.selectList(wrapper);
    }

    /**
     * 新增配置项
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(Config config) {
        config.setCreateUser(ShiroKit.getUser().getName());
        configService.addConfig(config);
        return SUCCESS_TIP;
    }

    /**
     * 删除配置项
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer configId) {
        configService.deleteConfigById(configId);
        return SUCCESS_TIP;
    }

    /**
     * 修改配置项
     */
    @RequestMapping("/update")
    @ResponseBody
    public Object update(Config config) {
        configService.updateConfigById(config);
        return SUCCESS_TIP;
    }

    /**
     * 配置项详情
     */
    @RequestMapping("/detail/{configId}")
    @ResponseBody
    public Object detail(@PathVariable("configId") Integer configId) {
        return configService.selectById(configId);
    }


    @RequestMapping("/publish")
    @ResponseBody
    public Object publish(@RequestParam Integer appId, @RequestParam Integer envId) {
        configService.publish(appId, envId);
        return SUCCESS_TIP;
    }

    @RequestMapping("/reset")
    @ResponseBody
    public Object reset(@RequestParam Integer appId, @RequestParam Integer envId) {
        configService.reset(appId, envId);
        return SUCCESS_TIP;
    }
}
