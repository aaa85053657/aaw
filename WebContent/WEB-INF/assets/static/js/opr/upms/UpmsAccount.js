var UpmsAccount = {
	url : "",
	arr : {
		dataArr : [ [ {
			field : 'id',
			field : 'id',
			hidden : true
		}, {
			field : 'name',
			title : '用户名',
			width : 1
		}, {
			field : 'pazzwd',
			title : '密码',
			width : 1
		}, {
			field : 'role',
			title : '关联的角色',
			width : 1,
			formatter : function(v) {
				if (v && v.name) {
					return v.name;
				}
				return "无绑定角色";
			}
		}, {
			field : 'empName',
			title : '指派给',
			width : 1,
			formatter : function(v) {
				if (v) {
					return v;
				}
				return "未指定用户";
			}
		} ] ]
	},
	fmDgButtons : [ {
		text : '保存',
		iconCls : 'icon-ok',
		handler : function() {
			G.save("fm", UpmsAccount.url, function() {
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
			// {
			// text : '新增',
			// iconCls : 'icon-add',
			// handler : function() {
			// UpmsAccount.showAddForm();
			// }
			// },
			// '-',
			{
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					UpmsAccount.showUpdateForm();
				}
			},
			'-',
			{
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					G.delByID(false, false, "确认删除该记录信息？",
							"UpmsAccount?act=del", function() {
								$('#tt').datagrid('reload');
							});
				}
			} ],
	initGrid : function() {
		var $this = this;
		$("#tt").datagrid({
			url : "UpmsAccount?act=list",
			columns : $this.arr.dataArr,
			toolbar : $this.gridToolBar,
			onDblClickRow : function(index, row) {
				$this.showUpdateForm();
			}
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
		G.dw("新增账户信息信息", "dlg");
		this.url = "UpmsAccount?act=save";
	},
	showUpdateForm : function() {
		var r = G.selectRow();
		if (r) {
			$("#fm").form("clear").form("load", r);
			G.ccs("role", r.role.id);
			G.dw("修改账户信息信息", "dlg");
			$("#empName").text(r.empName);
			$("#acctName").text(r.name);
			this.url = "UpmsAccount?act=update&id=" + r.id;
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
			return parseInt(G.uniqueReq("UpmsAccount?act=unique", "name",
					value, $("#vid").val())) === 1;
		},
		message : '输入账户已存在'
	}
});

$(function() {
	UpmsAccount.init();
});