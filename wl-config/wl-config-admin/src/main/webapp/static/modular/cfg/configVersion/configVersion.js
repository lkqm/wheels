/**
 * 配置历史管理初始化
 */
var ConfigVersion = {
    id: "ConfigVersionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ConfigVersion.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '应用id', field: 'appId', visible: true, align: 'center', valign: 'middle'},
        {title: '环境id', field: 'envId', visible: true, align: 'center', valign: 'middle'},
        {title: '版本', field: 'version', visible: true, align: 'center', valign: 'middle'},
        {title: '配置key', field: 'configKey', visible: true, align: 'center', valign: 'middle'},
        {title: '配置value', field: 'configValue', visible: true, align: 'center', valign: 'middle'},
        {title: '描述', field: 'configDesc', visible: true, align: 'center', valign: 'middle'},
        {title: '变更类型', field: 'changeType', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '创建人', field: 'createUser', visible: true, align: 'center', valign: 'middle'},
        {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'},
        {title: '更新人', field: 'updateUser', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ConfigVersion.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        ConfigVersion.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加配置历史
 */
ConfigVersion.openAddConfigVersion = function () {
    var index = layer.open({
        type: 2,
        title: '添加配置历史',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/configVersion/configVersion_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看配置历史详情
 */
ConfigVersion.openConfigVersionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '配置历史详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/configVersion/configVersion_update/' + ConfigVersion.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除配置历史
 */
ConfigVersion.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/configVersion/delete", function (data) {
            Feng.success("删除成功!");
            ConfigVersion.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("configVersionId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询配置历史列表
 */
ConfigVersion.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ConfigVersion.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ConfigVersion.initColumn();
    var table = new BSTable(ConfigVersion.id, "/configVersion/list", defaultColunms);
    table.setPaginationType("client");
    ConfigVersion.table = table.init();
});
