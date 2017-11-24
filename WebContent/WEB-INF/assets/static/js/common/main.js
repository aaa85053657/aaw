var type;
$(function() {
	// var _url = "static/menu.json";
	var _url = "menus";
	$.post(_url, function(data) {
		initMenu(data);
	}, "json");

});

var showUpdate = function() {
	$("#dlg").dialog("center").dialog("open").dialog('setTitle', '员工账户修改');
	$("#fm").form('clear');
	type = 1;
}

var save = function() {
	if ($('#fm').form('validate')) {
		$.post("upmsAccount?goto=update", $("#fm").serialize(), function(data) {
			var msg = null;
			if (data.suc == 1) {
				msg = "<span class='tip_suc'>" + data.msg + "</span>";
				$('#dlg').dialog('close');
				$.messager.show({
					title : '操作结果',
					msg : msg
				});
				$("#loginOut").click();
			} else {
				msg = "<span class='tip_error'>" + data.msg + "</span>";
				$.messager.alert('提示', msg, 'warning');
			}
		});
	}
}
