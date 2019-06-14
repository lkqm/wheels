package com.mario6.wheel.config.modular.cfg.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.mario6.wheel.config.core.log.LogObjectHolder;
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
 *
 * @author fengshuonan
 * @Date 2019-06-15 03:15:10
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
    public String configAdd() {
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
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return configService.selectList(null);
    }

    /**
     * 新增配置项
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Config config) {
        configService.insert(config);
        return SUCCESS_TIP;
    }

    /**
     * 删除配置项
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer configId) {
        configService.deleteById(configId);
        return SUCCESS_TIP;
    }

    /**
     * 修改配置项
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Config config) {
        configService.updateById(config);
        return SUCCESS_TIP;
    }

    /**
     * 配置项详情
     */
    @RequestMapping(value = "/detail/{configId}")
    @ResponseBody
    public Object detail(@PathVariable("configId") Integer configId) {
        return configService.selectById(configId);
    }
}
