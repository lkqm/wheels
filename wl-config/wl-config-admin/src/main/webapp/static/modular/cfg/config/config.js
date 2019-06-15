/**
 * 配置项管理初始化
 */
var Config = {
    id: "ConfigTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Config.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        // {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: 'Key', field: 'configKey', visible: true, align: 'center', valign: 'left'},
        {title: 'Value', field: 'configValue', visible: true, align: 'center', valign: 'middle'},
        {title: '描述', field: 'configDesc', visible: true, align: 'center', valign: 'middle'},
        {
            title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle', formatter(val, row) {
                var obj = {
                    '-1': '<span style="color: orange">删除</span>',
                    '0': '<span style="color: gray">已发布</span>',
                    '1': '<span style="color: green">新增</span>',
                    '2': '<span style="color: red">修改</span>',
                };
                return obj['' + val];
            }
        }
    ];
};

/**
 * 检查是否选中
 */
Config.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Config.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加配置项
 */
Config.openAddConfig = function () {
    var appId = $('#appId').val(), envId = $('#envId').val();
    var index = layer.open({
        type: 2,
        title: '添加配置项',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: false,
        content: Feng.ctxPath + '/config/config_add?appId=' + appId + '&envId=' + envId
    });
    this.layerIndex = index;
};

/**
 * 打开查看配置项详情
 */
Config.openConfigDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '配置项详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: false,
            content: Feng.ctxPath + '/config/config_update/' + Config.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除配置项
 */
Config.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/config/delete", function (data) {
            Feng.success("删除成功!");
            Config.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("configId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询配置项列表
 */
Config.search = function () {
    Config.table.refresh({query: Config.queryParams()});
};

Config.appList = function () {
    var ajax = new $ax(Feng.ctxPath + "/app/getByUser", function (data) {
        var html = '';
        for (var i in data) {
            var app = data[i];
            html += '<option value="' + app.id + '">' + app.appName + '</option>'
        }
        $('#appId').html(html);
    }, function (data) {
        Feng.error("应用列表加载错误!" + data.responseJSON.message + "!");
    });
    ajax.start();
};

Config.envList = function () {
    var ajax = new $ax(Feng.ctxPath + "/env/list", function (envs) {
        var html = '';
        for (var i in envs) {
            var env = envs[i];
            html += '<option value="' + env.id + '">' + env.envName + '</option>'
        }
        $('#envId').html(html);
    }, function (data) {
        Feng.error("应用列表加载错误!" + data.responseJSON.message + "!");
    });
    ajax.start();
};

Config.queryParams = function () {
    var data = {};
    data['appId'] = $("#appId").val();
    data['envId'] = $("#envId").val();
    data['key'] = $("#key").val();
    return data;
};

Config.publish = function () {
    var appId = $('#appId').val(), envId = $('#envId').val();
    if (appId && envId) {
        var ajax = new $ax(Feng.ctxPath + "/config/publish", function (data) {
            Feng.success("发布成功");
            Config.table.refresh();
        }, function (data) {
            Feng.error("发布失败!" + data.responseJSON.message + "!");
        });
        ajax.set('appId', appId);
        ajax.set('envId', envId);
        ajax.start();
    }
};

/**
 * 重置配置
 */
Config.reset = function () {
    var appId = $('#appId').val(), envId = $('#envId').val();
    if (appId && envId) {
        var ajax = new $ax(Feng.ctxPath + "/config/reset", function (data) {
            Feng.success("重置成功");
            Config.table.refresh();
        }, function (data) {
            Feng.error("重置失败!" + data.responseJSON.message + "!");
        });
        ajax.set('appId', appId);
        ajax.set('envId', envId);
        ajax.start();
    }
};

$(function () {
    Config.appList();
    Config.envList();

    var defaultColunms = Config.initColumn();
    var table = new BSTable(Config.id, "/config/list", defaultColunms);
    table.setPaginationType("client");
    Config.table = table.init();

    $('#appId').change(function () {
        Config.table.refresh({query: Config.queryParams()});
    });
    $('#envId').change(function () {
        Config.table.refresh({query: Config.queryParams()});
    });
    if ($('#appId').val() == null || $('#envId').val() == null) {
        $(":input").attr({"disabled": "disabled"});
    }

});
