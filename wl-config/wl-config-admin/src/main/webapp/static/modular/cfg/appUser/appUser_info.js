/**
 * 初始化关联用户详情对话框
 */
var AppUserInfoDlg = {
    appUserInfoData: {}
};

/**
 * 清除数据
 */
AppUserInfoDlg.clearData = function () {
    this.appUserInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AppUserInfoDlg.set = function (key, val) {
    this.appUserInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AppUserInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AppUserInfoDlg.close = function () {
    parent.layer.close(window.parent.AppUser.layerIndex);
}

/**
 * 收集数据
 */
AppUserInfoDlg.collectData = function () {
    this
        .set('id')
        .set('appId')
        .set('userId');
}

/**
 * 提交添加
 */
AppUserInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/appUser/add", function (data) {
        Feng.success("添加成功!");
        window.parent.AppUser.table.refresh();
        AppUserInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.appUserInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AppUserInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/appUser/update", function (data) {
        Feng.success("修改成功!");
        window.parent.AppUser.table.refresh();
        AppUserInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.appUserInfoData);
    ajax.start();
}

$(function () {

});
