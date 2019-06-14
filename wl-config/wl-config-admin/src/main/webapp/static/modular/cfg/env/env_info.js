/**
 * 初始化环境详情对话框
 */
var EnvInfoDlg = {
    envInfoData: {}
};

/**
 * 清除数据
 */
EnvInfoDlg.clearData = function () {
    this.envInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EnvInfoDlg.set = function (key, val) {
    this.envInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EnvInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
EnvInfoDlg.close = function () {
    parent.layer.close(window.parent.Env.layerIndex);
}

/**
 * 收集数据
 */
EnvInfoDlg.collectData = function () {
    this
        .set('id')
        .set('envName')
        .set('envDesc')
        .set('createTime')
        .set('createUser');
}

/**
 * 提交添加
 */
EnvInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/env/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Env.table.refresh();
        EnvInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.envInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
EnvInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/env/update", function (data) {
        Feng.success("修改成功!");
        window.parent.Env.table.refresh();
        EnvInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.envInfoData);
    ajax.start();
}

$(function () {

});
