/**
 * 环境管理初始化
 */
var Env = {
    id: "EnvTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Env.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '环境名称', field: 'envName', visible: true, align: 'center', valign: 'middle'},
        {title: '环境描述', field: 'envDesc', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '创建人', field: 'createUser', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Env.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Env.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加环境
 */
Env.openAddEnv = function () {
    var index = layer.open({
        type: 2,
        title: '添加环境',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: false,
        content: Feng.ctxPath + '/env/env_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看环境详情
 */
Env.openEnvDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '环境详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: false,
            content: Feng.ctxPath + '/env/env_update/' + Env.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除环境
 */
Env.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/env/delete", function (data) {
            Feng.success("删除成功!");
            Env.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("envId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询环境列表
 */
Env.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Env.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Env.initColumn();
    var table = new BSTable(Env.id, "/env/list", defaultColunms);
    table.setPaginationType("client");
    Env.table = table.init();
});
