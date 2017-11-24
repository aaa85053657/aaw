var UpmsRole = {
	url : "",
	arr : {
		dataArr : [ [ {
			field : 'id',
			field : 'id',
			hidden : true
		}, {
			field : 'name',
			title : '角色名称',
			width : 1
		} ] ]
	},
	fmDgButtons : [ {
		text : '保存',
		iconCls : 'icon-ok',
		handler : function() {
			G.save("fm", UpmsRole.url, function() {
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
	gridToolBar : [ {
		text : '新增',
		iconCls : 'icon-add',
		handler : function() {
			UpmsRole.showAddForm();
		}
	}, '-', {
		text : '修改',
		iconCls : 'icon-edit',
		handler : function() {
			UpmsRole.showUpdateForm();
		}
	}, '-', {
		text : '删除',
		iconCls : 'icon-remove',
		handler : function() {
			var nodes = $("#assets").tree('getChecked', 'indeterminate');
			var tip = "";
			var _url = "";
			var r = G.selectRow();
			if (r) {
				G.sync("UpmsRole?act=refEmp", {
					id : r.id
				}, function(data) {
					if (nodes && nodes.length > 0 && data === 1) {
						tip = "存在资源和账户引用，确认删除？";
						_url = "UpmsRole?act=del&b=3";
					} else if (nodes && nodes.length > 0) {
						tip = "存在资源引用，确认删除？";
						_url = "UpmsRole?act=del&b=2";
					} else if (data === 1) {
						tip = "存在账户引用，确认删除？";
						_url = "UpmsRole?act=del&b=1";
					} else {
						tip = "确认删除？";
						_url = "UpmsRole?act=del";
					}
					G.delByID(false, false, tip, _url, function() {
						$('#tt').datagrid('reload');
					});
				});
			} else {
				G.alert("请先选择需要删除的数据！");
			}
		}
	} ],
	initGrid : function() {
		var $this = this;
		$("#tt").datagrid({
			url : "UpmsRole?act=list",
			columns : $this.arr.dataArr,
			toolbar : $this.gridToolBar,
			onSelect : function(index, r) {
				$this.permission.showAssertsPage(r.id);
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
		G.dw("新增角色信息信息", "dlg");
		this.url = "UpmsRole?act=save";
	},
	showUpdateForm : function() {
		var r = G.selectRow();
		if (r) {
			$("#fm").form("disableValidation").form("clear").form("load", r);
			$("#vid").val(r.id);
			G.dw("修改角色信息信息", "dlg");
			this.url = "UpmsRole?act=update&id=" + r.id;
			$("#fm").form("enableValidation");
		} else {
			G.alert("请选择需要修改的数据");
		}
	},
	permission : {
		roleID : null,
		showAssertsPage : function(id) {
			var $this = this;
			this.roleID = id;
			$.post('UpmsAssets?act=list4BindIDs&roleID=' + id, function(data,
					textStatus, xhr) {
				$this.uncheckAll();
				$this.checked(data);
			});
		},
		checked : function(data) {
			if (data && data.length > 0) {
				$.each(data, function(index, v1) {
					$.each($("#assets").tree("getRoots"), function(index, v) {
						if (v.children) {
							$.each(v.children, function(index, v2) {
								if (v1[0] === v2.id) {
									$("#assets")
											.tree(
													'check',
													$("#assets").tree('find',
															v2.id).target);
								}
							});
						}
					});
				});
			}
		},
		uncheckAll : function() {
			var nodes = this.nodes();
			if (nodes) {
				$.each(nodes, function(i, v) {
					$("#assets").tree('uncheck', v.target);
				});
			}
		},
		fm : function(node) {
			if (node.children) {
				return "<span style='font-weight:bold;'>" + node.text
						+ "</span>";
			}
			return node.text;
		},
		nodes : function() {
			return $("#assets").tree("getChecked",
					[ 'checked', 'indeterminate' ]);
		},
		bind : function() {
			var $this = this;
			var r = G.selectRow();
			if ($this.roleID > 0 && r) {
				var nodes = $this.nodes();
				var ar = new Array();
				if (nodes && nodes.length > 0) {
					for (var i = 0; i < nodes.length; i++) {
						ar.push(nodes[i].id);
					}
				} else {
					ar.push("");
				}
				$.post("UpmsRole?act=bind&id=" + $this.roleID, {
					rids : ar
				}, function(data) {
					G.tip(data);
				}, "json");
			} else {
				G.alert("请先选择需要绑定的角色！");
			}
		}
	},
	init : function() {
		this.initGrid();
		this.initFm();
	}
}

$.extend($.fn.validatebox.defaults.rules, {
	name : {
		validator : function(value) {
			return parseInt(G.uniqueReq("UpmsRole?act=unique", "name", value,
					$("#vid").val())) === 1;
		},
		message : '输入角色名称已存在'
	}
});

$(function() {
	UpmsRole.init();
});