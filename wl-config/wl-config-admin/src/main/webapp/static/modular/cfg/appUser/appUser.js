/**
 * 关联用户管理初始化
 */
var AppUser = {
    id: "AppUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
AppUser.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '应用id', field: 'appId', visible: true, align: 'center', valign: 'middle'},
        {title: '用户id', field: 'userId', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
AppUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        AppUser.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加关联用户
 */
AppUser.openAddAppUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加关联用户',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/appUser/appUser_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看关联用户详情
 */
AppUser.openAppUserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '关联用户详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/appUser/appUser_update/' + AppUser.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除关联用户
 */
AppUser.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/appUser/delete", function (data) {
            Feng.success("删除成功!");
            AppUser.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("appUserId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询关联用户列表
 */
AppUser.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    AppUser.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = AppUser.initColumn();
    var table = new BSTable(AppUser.id, "/appUser/list", defaultColunms);
    table.setPaginationType("client");
    AppUser.table = table.init();
});
