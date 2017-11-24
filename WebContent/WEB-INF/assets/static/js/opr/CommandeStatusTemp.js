$(function() {
	CST.initTg();
});

CST = {
	url : "",
	initTg : function() {
		var groupId = $("#groupIdTemp").val();
		$('.cc').combobox({
			data : isSMCombobox
		});
		$('#tt').treegrid({
			url : 'commandeStatusTemp?goto=list&gid=' + groupId,
			idField : 'id',
			treeField : 'name',
			columns : dataArr,
			toolbar : '#toolbar',
			onDblClickRow : this.dbcChoice,
			rowStyler : function(row) {
				if ($.i18n.prop('cst.save.specND') == row.name) {
					return "background-color:red;";
				}
			},
			onLoadSuccess : function(r, data) {
				if (data && data.length > 0) {
					$('#addStart').linkbutton({
						disabled : true
					});
					$.each(data, function(k, v) {
						if ($.i18n.prop('cst.save.specND') == v.name) {
							$('#addSpecical').linkbutton({
								disabled : true
							});
							return false;
						}
					});
				} else {
					$('#addStart').linkbutton({
						disabled : false
					});
				}
			}
		});
	},
	addF : function() {
		$('#fm').form('clear');
		var groupId = $("#groupIdTemp").val();
		$("#groupId").val(groupId);
		if ($("#validator_id")) {
			$("#validator_id").val(0);
		}
		this.url = "commandeStatusTemp?goto=save";
		$('#dlg').dialog('center').dialog('open').dialog('setTitle',
				$.i18n.prop('cst.title.add'));
	},
	updateF : function() {
		var r = $('#tt').treegrid('getSelected');
		if (r) {
			$('#fm').form('clear').form('load', r);
			var groupId = $("#groupIdTemp").val();
			$("#groupId").val(groupId);
			if ($("#validator_id")) {
				$("#validator_id").val(r.id);
			}
			this.url = "commandeStatusTemp?goto=update&id=" + r.id;
			$('#dlg').dialog('center').dialog('open').dialog('setTitle',
					$.i18n.prop('cst.title.update'));
		} else {
			$.messager.alert($.i18n.prop('crud.dialog.tip.title'), $.i18n
					.prop('crud.dialog.tip.update'), 'warning');
		}
	},
	dbcChoice : function(index, row) {
		var r = $('#tt').treegrid('getSelected');
		var dcArr = [ {
			text : $.i18n.prop('cst.dcc.modify'),
			iconCls : 'icon-edit',
			handler : function() {
				CST.updateF();
				$('#dbcChoice').dialog("close");
			}
		}, "-", {
			text : $.i18n.prop('cst.dcc.addNext'),
			iconCls : 'icon-help',
			handler : function() {
				CST.addNode(1);
				$('#dbcChoice').dialog("close");
			}
		}, "-", {
			text : $.i18n.prop('cst.dcc.del'),
			iconCls : 'icon-help',
			handler : function() {
				CST.del();
				$('#dbcChoice').dialog("close");
			}
		}, "-", {
			text : $.i18n.prop('cst.dcc.child'),
			iconCls : 'icon-help',
			handler : function() {
				CST.addNode(2);
				$('#dbcChoice').dialog("close");
			}
		} ];
		if (r.hasChildren > 0) {
			dcArr = [ {
				text : $.i18n.prop('cst.dcc.modify'),
				iconCls : 'icon-edit',
				handler : function() {
					CST.updateF();
					$('#dbcChoice').dialog("close");
				}
			}, "-", {
				text : $.i18n.prop('cst.dcc.addNext'),
				iconCls : 'icon-help',
				handler : function() {
					CST.addNode(1);
					$('#dbcChoice').dialog("close");
				}
			}, "-", {
				text : $.i18n.prop('cst.dcc.del'),
				iconCls : 'icon-help',
				handler : function() {
					CST.del();
					$('#dbcChoice').dialog("close");
				}
			} ];
		}
		if ($.i18n.prop('cst.save.specND') == r.name) {
			dcArr = [ {
				text : $.i18n.prop('cst.dcc.addNext'),
				iconCls : 'icon-help',
				handler : function() {
					CST.addNode(1);
					$('#dbcChoice').dialog("close");
				}
			}, "-", {
				text : $.i18n.prop('cst.dcc.del'),
				iconCls : 'icon-help',
				handler : function() {
					CST.del();
					$('#dbcChoice').dialog("close");
				}
			} ];
		}
		$('#dbcChoice').dialog({
			title : $.i18n.prop('cst.dcc.title'),
			// content : $.i18n.prop('slave.opr.content'),
			cache : false,
			toolbar : dcArr
		}).dialog('center').dialog('open');
	},
	save : function() {
		$('#fm').form('submit', {
			url : this.url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(r) {
				AAW.saveTip(r, "dlg", "tt", true);
			}
		});
	},
	saveNode : function() {
		$('#fm-node').form('submit', {
			url : this.url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(r) {
				AAW.saveTip(r, "dlg-node", "tt", true);
			}
		});
	},
	addNode : function(t) {
		$('#fm-node').form('clear');
		var r = $('#tt').treegrid('getSelected');
		var groupId = $("#groupIdTemp").val();
		$("#groupId2").val(groupId);
		switch (t) {
		case 1:
			$('input[name="parentId"]').val(r.parentId);
			$('input[name="previousId"]').val(r.id);
			break;
		case 2:
			$('input[name="previousId"]').val(0);
			$('input[name="parentId"]').val(r.id);
			break;
		}
		if ($("#validator_id")) {
			$("#validator_id").val(0);
		}
		this.url = "commandeStatusTemp?goto=save";
		$('#dlg-node').dialog('center').dialog('open').dialog('setTitle',
				$.i18n.prop('cst.title.add'));
	},
	del : function() {
		var r = $('#tt').treegrid('getSelected');
		var tip = "";
		if (r.hasChildren > 0) {
			tip = $.i18n.prop('cst.del.tip.hasC').replace("{1}", r.hasChildren);
		} else {
			tip = $.i18n.prop('cst.del.tip.noC');
		}
		$.messager.confirm($.i18n.prop('crud.dialog.tip.title'), tip, function(
				sur) {
			if (sur) {
				$.post("commandeStatusTemp?goto=del", {
					id : r.id,
					cNum : r.hasChildren
				},
						function(data) {
							var msg = null;
							if (data.suc == 1) {
								$('#tt').treegrid('reload');
								msg = "<span class='tip_suc'>" + data.msg
										+ "</span>";
							} else {
								msg = "<span class='tip_error'>" + data.msg
										+ "</span>";
							}
							$.messager.show({
								title : $.i18n.prop('crud.dialog.tip.oprrs'),
								msg : msg
							});
						}, 'json');
			}
		});
	},
	createSpec : function() {
		var r = $('#tt').treegrid('getSelected');
		var groupId = $("#groupIdTemp").val();
		if (r && r.parentId == 0) {
			var preID = r.id;
			$.messager.confirm($.i18n.prop('crud.dialog.tip.title'), $.i18n
					.prop('cst.spec.sureNd'), function(r1) {
				if (r1) {
					$.post("commandeStatusTemp?goto=saveSpec", {
						previousID : preID,
						gid : groupId
					}, function(data) {
						var msg = null;
						if (data.suc == 1) {
							$('#tt').treegrid('reload');
							msg = "<span class='tip_suc'>" + data.msg
									+ "</span>";
						} else {
							msg = "<span class='tip_error'>" + data.msg
									+ "</span>";
						}
						$.messager.show({
							title : $.i18n.prop('crud.dialog.tip.oprrs'),
							msg : msg
						});

					});
				}
			});
		} else {
			$.messager.alert($.i18n.prop('crud.dialog.tip.title'), $.i18n
					.prop('cst.spec.wrongNd'), 'warning');
		}
	}
}

var goBack = function() {
	window.location.href = "commandeStatusGroup?goto=view";
}