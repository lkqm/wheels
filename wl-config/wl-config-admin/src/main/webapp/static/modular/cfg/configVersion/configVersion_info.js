/**
 * 初始化配置历史详情对话框
 */
var ConfigVersionInfoDlg = {
    configVersionInfoData: {}
};

/**
 * 清除数据
 */
ConfigVersionInfoDlg.clearData = function () {
    this.configVersionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ConfigVersionInfoDlg.set = function (key, val) {
    this.configVersionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ConfigVersionInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ConfigVersionInfoDlg.close = function () {
    parent.layer.close(window.parent.ConfigVersion.layerIndex);
}

/**
 * 收集数据
 */
ConfigVersionInfoDlg.collectData = function () {
    this
        .set('id')
        .set('appId')
        .set('envId')
        .set('version')
        .set('configKey')
        .set('configValue')
        .set('configDesc')
        .set('changeType')
        .set('createTime')
        .set('createUser')
        .set('updateTime')
        .set('updateUser');
}

/**
 * 提交添加
 */
ConfigVersionInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/configVersion/add", function (data) {
        Feng.success("添加成功!");
        window.parent.ConfigVersion.table.refresh();
        ConfigVersionInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.configVersionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ConfigVersionInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/configVersion/update", function (data) {
        Feng.success("修改成功!");
        window.parent.ConfigVersion.table.refresh();
        ConfigVersionInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.configVersionInfoData);
    ajax.start();
}

$(function () {

});
