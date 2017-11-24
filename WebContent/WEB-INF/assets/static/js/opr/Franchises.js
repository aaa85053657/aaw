$(function() {
	$.BaseCRUD({
		listURL : "franchises?goto=map4EUI",
		addURL : "franchises?goto=save",
		updateURL : "franchises?goto=update",
		delURL : "franchises?goto=del",
		titleAddF : $.i18n.prop('franchises.title.add'),
		titleUpdateF : $.i18n.prop('franchises.title.update')
	});

	$.post("franchises?goto=clickInput", function(data) {
		if (data == "1") {
			$("#showCodeId").textbox('enable');
		}
	});

});
var clickAddAssets = function() {
	$.post("franchises?goto=clickAssets", {
		"as" : 1
	}, function(data) {
		if (data.suc == 1) {
			$('#dlg').dialog('center').dialog('open').dialog('setTitle',
					$.i18n.prop('attribute.title.add'));
			$('#fm').form('clear');
			$.post("franchises?goto=scode", function(data) {
				$("#showCodeId").textbox('setValue', data);
			});
		} else {
			$.messager.alert($.i18n.prop('crud.dialog.tip.title'), $.i18n
					.prop('franchises.add.err'), 'warning');
		}
	})
};

var clickUpdateAssets = function() {
	var row = $('#tt').datagrid('getSelected');
	if (row) {
		$.post("franchises?goto=clickAssets", {
			"as" : 2
		}, function(data) {
			if (data.suc == 1) {
				$('#dlg').dialog('center').dialog('open').dialog('setTitle',
						$.i18n.prop('attribute.title.update'));
				$('#fm').form('clear');
				$('#fm').form('load', row);
			} else {
				$.messager.alert($.i18n.prop('model.cfg.tip.title'), $.i18n
						.prop('franchises.update.err'), 'warning');
			}
		})
	} else {
		$.messager.alert($.i18n.prop('model.cfg.tip.title'), $.i18n
				.prop('crud.dialog.tip.update'), 'warning');
	}
};

var btnSave = function() {
	var id = $("#validator_id").val();
	if (id == "" || id == null) {
		if ($('#fm').form('validate')) {
			$.post("franchises?goto=save", $("#fm").serialize(),
					function(data) {
						$('#dlg').dialog('close');
						var msg = null;
						if (data.suc == 1) {
							$('#tt').datagrid('reload');
							msg = "<span class='tip_suc'>" + data.msg
									+ "</span>";
						} else {
							msg = "<span class='tip_error'>" + data.msg
									+ "</span>";
						}
						$.messager.show({
							title : '操作结果',
							msg : msg
						});
					});
		}
	} else {
		if ($('#fm').form('validate')) {
			$.post("franchises?goto=update", $("#fm").serialize(), function(
					data) {
				$('#dlg').dialog('close');
				var msg = null;
				if (data.suc == 1) {
					$('#tt').datagrid('reload');
					msg = "<span class='tip_suc'>" + data.msg + "</span>";
				} else {
					msg = "<span class='tip_error'>" + data.msg + "</span>";
				}
				$.messager.show({
					title : '操作结果',
					msg : msg
				});
			});
		}
	}
}

var BtnCfg = function(i) {
	var row = AAW.selectedRow();
	if (row) {
		if (i == 1) {
			cfgProfile(row);
		} else {
			cfgProfile2(row);
		}
	} else {
		$.messager.alert($.i18n.prop('model.cfg.tip.title'), $.i18n
				.prop('model.cfg.tip.content'), 'warning');
	}
}

var cfgProfile = function(row) {
	$("#fid").val(row.id);
	$("#facc").textbox('setValue', "");
	$("#fpwd").textbox('setValue', "");
	$("#aid").val("");
	$("#assets").html("");
	$("#facc").textbox('setValue', row.contactEmail);
	$.post("franchises?goto=assets", {
		fid : row.id
	}, function(data) {
		var str = "";
		data.forEach(function(e) {
			str += "<input name='assets' type='checkbox' value='" + e.id;
			if (e.isclick != 1) {
				str += " checked='true'";
			}
			str += "' />" + e.name;
		});
		$("#assets").html(str);
	})
	$('#dlg-cfg').dialog('center').dialog('open').dialog('setTitle',
			$.i18n.prop('franchises.acc.manage'));
}

var cfgProfile2 = function(row) {
	$("#fid").val(row.id);
	$("#assets").html("");
	$("#facc").textbox('setValue', "");
	$("#fpwd").textbox('setValue', "");
	$("#aid").val("");
	$.post("franchises?goto=findAcc", {
		fid : row.id
	}, function(data2) {
		$("#facc").textbox('setValue', data2.account);
		$("#fpwd").textbox('setValue', data2.pazzword);
		$("#aid").val(data2.id);
		if (data2.status == 1) {
			$("input[name=status]:eq(0)").attr("checked", 'checked');
		} else {
			$("input[name=status]:eq(1)").attr("checked", 'checked');
		}

	});

	$.post("franchises?goto=assets", {
		fid : row.id
	}, function(data) {
		var str = "";
		data.forEach(function(e) {
			str += "<input name='assets' type='checkbox' value='" + e.id + "'";
			if (e.isclick != 1) {
				str += " checked='true'";
			}
			str += " />" + e.name;
		});
		$("#assets").html(str);
	})
	$('#dlg-cfg').dialog('center').dialog('open').dialog('setTitle',
			$.i18n.prop('franchises.acc.manage'));
}

var saveAcc = function() {
	var id = $("#aid").val();
	if (id == "" || id == null) {
		if ($('#fm-cfg').form('validate')) {
			$.post("franchises?goto=saveAcc", $("#fm-cfg").serialize(),
					function(data) {
						$('#dlg-cfg').dialog('close');
						var msg = null;
						if (data.suc == 1) {
							$('#tt').datagrid('reload');
							msg = "<span class='tip_suc'>" + data.msg
									+ "</span>";
						} else {
							msg = "<span class='tip_error'>" + data.msg
									+ "</span>";
						}
						$.messager.show({
							title : '操作结果',
							msg : msg
						});
					});
		}
	} else {
		if ($('#fm-cfg').form('validate')) {
			$.post("franchises?goto=updateAcc", $("#fm-cfg").serialize(),
					function(data) {
						$('#dlg-cfg').dialog('close');
						var msg = null;
						if (data.suc == 1) {
							$('#tt').datagrid('reload');
							msg = "<span class='tip_suc'>" + data.msg
									+ "</span>";
						} else {
							msg = "<span class='tip_error'>" + data.msg
									+ "</span>";
						}
						$.messager.show({
							title : '操作结果',
							msg : msg
						});
					});
		}
	}
}

var deleteRow = function() {
	var row = AAW.selectedRow();
	if (row) {
		$.post("franchises?goto=clickAssets", {
			"as" : 3
		}, function(data) {
			if (data.suc == 1) {
				$.post("franchises?goto=delete&id=" + row.id, function(data) {
					$('#tt').datagrid('reload');
				});
			} else {
				$.messager.alert($.i18n.prop('model.cfg.tip.title'), $.i18n
						.prop('franchises.delete.err'), 'warning');
			}
		});
	} else {
		$.messager.alert($.i18n.prop('model.cfg.tip.title'), $.i18n
				.prop('crud.dialog.tip.update'), 'warning');
	}
}

var intoInfo = function() {
	$.post("franchises?act=clickExport", function(data) {
		if (data == 1) {
			$('#dlg-into').dialog('center').dialog('open').dialog('setTitle',
					$.i18n.prop('franchises.title.import'));
		} else {
			$.messager.alert($.i18n.prop('model.cfg.tip.title'),
					$.i18n.prop('franchises.import.no'), 'warning');
		}
	})
}

var exportInfo = function() {
	$.post("franchises?act=clickExport", function(data) {
		if (data == 1) {
			$.download('franchises?act=franchisesInfo', 'post');
		} else {
			$.messager.alert($.i18n.prop('model.cfg.tip.title'),
					$.i18n.prop('franchises.export.no'), 'warning');
		}
	})
}

var saveInfo = function() {
	var cc = $("#appFile").val();
	if (cc == "") {
		$.messager.alert($.i18n.prop('model.cfg.tip.title'), "请选择导入内容！",
				'warning');
	} else {
		$.ajaxFileUpload({
			url : 'franchises?act=intoFranchisesInfo',
			secureuri : false, // 是否启用安全提交,默认为false
			fileElementId : 'appFile', // 文件选择框的id属性
			dataType : 'text', // 服务器返回的格式,可以是json或xml等
			success : function(data, status) { // 服务器响应成功时的处理函数
				if (data == "2") {
					$('#dlg-into').dialog('close');
					$.messager.confirm('Confirm', "上传内容错误，是否下载错误信息？", function(
							rd) {
						$.download('franchises?act=downloadERR', 'post');
					})
				} else {
					$.messager.alert($.i18n.prop('model.cfg.tip.title'),
							"导入成功！", 'warning');
					$('#dlg-into').dialog('close');
				}
			}
		});
	}

}

var createTree = function() {
	$('#dlg-tree').dialog('center').dialog('open').dialog('setTitle', '加盟树');
	$('#franTree').tree({
		url : 'franchises?goto=getTree',
		lines : true
	});
}

var printTree = function() {
	$("#print_zone").print();
}

jQuery.download = function(url, data, method) {
	if (url && data) {
		data = typeof data == 'string' ? data : jQuery.param(data);
		var inputs = '';
		jQuery.each(data.split('&'), function() {
			var pair = this.split('=');
			inputs += '<input type="hidden" name="' + pair[0] + '" value="'
					+ pair[1] + '" />';
		});
		jQuery(
				'<form action="' + url + '" method="' + (method || 'post')
						+ '">' + inputs + '</form>').appendTo('body').submit()
				.remove();
	}
};