var RelationRoleAssets = {
	url : "",
	arr : {
		dataArr : [ [ {
			field : 'id',
			field : 'id',
			hidden : true
		}
	, {
			field : 'upmsAssetsId',
			title : '资源ID',
			width : 1
		}
	, {
			field : 'upmsRoleId',
			title : '角色ID',
			width : 1
		}
		 ] ]
	},
	fmDgButtons : [ {
		text : '保存',
		iconCls : 'icon-ok',
		handler : function() {
			G.save("fm", RelationRoleAssets.url, function() {
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
					RelationRoleAssets.showAddForm();
				}
			},
			'-',
			{
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					RelationRoleAssets.showUpdateForm();
				}
			},
			'-',
			{
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					G.delByID(false, false, "确认删除该记录信息？", "RelationRoleAssets?act=del",
							function() {
								$('#tt').datagrid('reload');
							});
				}
			} ],
	initGrid : function() {
		var $this = this;
		$("#tt").datagrid({
			url : "RelationRoleAssets?act=list",
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
		G.dw("新增角色与资源关联信息信息", "dlg");
		this.url = "RelationRoleAssets?act=save";
	},
	showUpdateForm : function() {
		var r = G.selectRow();
		if (r) {
			$("#fm").form("clear").form("load", r);
			G.dw("修改角色与资源关联信息信息", "dlg");
			this.url = "RelationRoleAssets?act=update&id=" + r.id;
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
			return parseInt(G.uniqueReq("RelationRoleAssets?act=unique", "name", value, $("#vid").val()));
		},
		message : '输入账户已存在'
	}
	,upmsAssetsId : {
		validator : function(value) {
			return parseInt(G.uniqueReq("RelationRoleAssets?act=unique", "upmsAssetsId", value, $("#vid").val()));
		},
		message : '输入资源ID已存在'
	}
	,upmsRoleId : {
		validator : function(value) {
			return parseInt(G.uniqueReq("RelationRoleAssets?act=unique", "upmsRoleId", value, $("#vid").val()));
		},
		message : '输入角色ID已存在'
	}
});

$(function() {
	RelationRoleAssets.init();
});