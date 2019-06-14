package com.mario6.wheel.config.modular.cfg.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.mario6.wheel.config.core.log.LogObjectHolder;
import com.mario6.wheel.config.modular.cfg.service.IConfigVersionService;
import com.mario6.wheel.config.modular.system.model.ConfigVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 配置历史控制器
 *
 * @author fengshuonan
 * @Date 2019-06-15 03:19:28
 */
@Controller
@RequestMapping("/configVersion")
public class ConfigVersionController extends BaseController {

    private String PREFIX = "/cfg/configVersion/";

    @Autowired
    private IConfigVersionService configVersionService;

    /**
     * 跳转到配置历史首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "configVersion.html";
    }

    /**
     * 跳转到添加配置历史
     */
    @RequestMapping("/configVersion_add")
    public String configVersionAdd() {
        return PREFIX + "configVersion_add.html";
    }

    /**
     * 跳转到修改配置历史
     */
    @RequestMapping("/configVersion_update/{configVersionId}")
    public String configVersionUpdate(@PathVariable Integer configVersionId, Model model) {
        ConfigVersion configVersion = configVersionService.selectById(configVersionId);
        model.addAttribute("item", configVersion);
        LogObjectHolder.me().set(configVersion);
        return PREFIX + "configVersion_edit.html";
    }

    /**
     * 获取配置历史列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return configVersionService.selectList(null);
    }

    /**
     * 新增配置历史
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ConfigVersion configVersion) {
        configVersionService.insert(configVersion);
        return SUCCESS_TIP;
    }

    /**
     * 删除配置历史
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer configVersionId) {
        configVersionService.deleteById(configVersionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改配置历史
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ConfigVersion configVersion) {
        configVersionService.updateById(configVersion);
        return SUCCESS_TIP;
    }

    /**
     * 配置历史详情
     */
    @RequestMapping(value = "/detail/{configVersionId}")
    @ResponseBody
    public Object detail(@PathVariable("configVersionId") Integer configVersionId) {
        return configVersionService.selectById(configVersionId);
    }
}
