var UpmsAssets = {
	url : "",
	arr : {
		dataArr : [ [ {
			field : 'id',
			field : 'id',
			hidden : true
		}
	, {
			field : 'moduleName',
			title : '模块(菜单)显示名称',
			width : 1
		}
	, {
			field : 'modulePath',
			title : '模块实际访问路径',
			width : 1
		}
	, {
			field : 'type',
			title : '类别区分,1为菜单',
			width : 1
		}
	, {
			field : 'parent',
			title : '父级菜单ID号,0为顶级菜单',
			width : 1
		}
	, {
			field : 'icon',
			title : '显示的ICON，需要使用easyui的css中添加',
			width : 1
		}
	, {
			field : 'orderValue',
			title : '菜单排序号,数字越大越靠前',
			width : 1
		}
		 ] ]
	},
	fmDgButtons : [ {
		text : '保存',
		iconCls : 'icon-ok',
		handler : function() {
			G.save("fm", UpmsAssets.url, function() {
				$('#dlg').dialog('close');
				$('#tt').datagrid('reload');
			});
		}
	}, {
		text : '关闭',
		iconCls : 'icon-cancel',
		handler : function() {
			$('#dlg').dialog('close');
		}
	} ],
	gridToolBar : [
			{
				text : '新增',
				iconCls : 'icon-add',
				handler : function() {
					UpmsAssets.showAddForm();
				}
			},
			'-',
			{
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					UpmsAssets.showUpdateForm();
				}
			},
			'-',
			{
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					G.delByID(false, false, "确认删除该记录信息？", "UpmsAssets?act=del",
							function() {
								$('#tt').datagrid('reload');
							});
				}
			} ],
	initGrid : function() {
		var $this = this;
		$("#tt").datagrid({
			url : "UpmsAssets?act=list",
			columns : $this.arr.dataArr,
			toolbar : $this.gridToolBar
		});
	},
	initFm : function() {
		$this = this;
		$('#dlg').dialog({
			buttons : $this.fmDgButtons
		});
	},
	showAddForm : function() {
		$("#fm").form("clear");
		G.dw("新增系统资源信息", "dlg");
		this.url = "UpmsAssets?act=save";
	},
	showUpdateForm : function() {
		var r = G.selectRow();
		if (r) {
			$("#fm").form("clear").form("load", r);
			G.dw("修改系统资源信息", "dlg");
			this.url = "UpmsAssets?act=update&id=" + r.id;
		} else {
			G.alert("请选择需要修改的数据");
		}
	},
	init : function() {
		this.initGrid();
		this.initFm();
	}
}

$.extend($.fn.validatebox.defaults.rules, {

	unique : {
		validator : function(value) {
			return parseInt(G.uniqueReq("UpmsAssets?act=unique", "name", value, $("#vid").val()));
		},
		message : '输入账户已存在'
	}
	,moduleName : {
		validator : function(value) {
			return parseInt(G.uniqueReq("UpmsAssets?act=unique", "moduleName", value, $("#vid").val()));
		},
		message : '输入模块(菜单)显示名称已存在'
	}
	,modulePath : {
		validator : function(value) {
			return parseInt(G.uniqueReq("UpmsAssets?act=unique", "modulePath", value, $("#vid").val()));
		},
		message : '输入模块实际访问路径已存在'
	}
	,type : {
		validator : function(value) {
			return parseInt(G.uniqueReq("UpmsAssets?act=unique", "type", value, $("#vid").val()));
		},
		message : '输入类别区分,1为菜单已存在'
	}
	,parent : {
		validator : function(value) {
			return parseInt(G.uniqueReq("UpmsAssets?act=unique", "parent", value, $("#vid").val()));
		},
		message : '输入父级菜单ID号,0为顶级菜单已存在'
	}
	,icon : {
		validator : function(value) {
			return parseInt(G.uniqueReq("UpmsAssets?act=unique", "icon", value, $("#vid").val()));
		},
		message : '输入显示的ICON，需要使用easyui的css中添加已存在'
	}
	,orderValue : {
		validator : function(value) {
			return parseInt(G.uniqueReq("UpmsAssets?act=unique", "orderValue", value, $("#vid").val()));
		},
		message : '输入菜单排序号,数字越大越靠前已存在'
	}
});

$(function() {
	UpmsAssets.init();
});