$(function() {
	init();
});

var init = function() {
	var ttOptions = {
		url : "employee?goto=list",
		fitColumns : true,
		columns : dataArr,
		toolbar : "#toolbar",
		view : detailview,
		detailFormatter : function(index, row) {
			return '<div style="padding:2px"><table class="ddv"></table></div>';
		},
		onExpandRow : function(index, row) {
			er(index, row, $(this));
		}
	};
	$("#tt").datagrid(ttOptions);
}
function er(index, row, $this) {
	var ddv = $this.datagrid('getRowDetail', index).find('table.ddv');
	ddv.datagrid({
		url : 'employee?goto=procedure&id=' + row.id,
		pagination : false,
		fit : false,
		height : 'auto',
		columns : arr,
		onResize : function() {
			$("#tt").datagrid('fixDetailRowHeight', index);
		},
		onLoadSuccess : function() {
			setTimeout(function() {
				$("#tt").datagrid('fixDetailRowHeight', index);
			}, 0);
		}
	});
	$("#tt").datagrid('fixDetailRowHeight', index);
}
var ccLoad = function() {
	var row = $("#tt").datagrid('getSelected');
	if (row) {
		$("#fm").form('clear');
		var url = 'metaProcedure?goto=all&id=' + row.id;
		$.post(url, function(data) {
			if (data && data.length > 0) {
				$("#pId").empty();
				callBack(data);
				$("#employeeId").val(row.id)
				$("#dlg").dialog('open').dialog('setTitle',
						$.i18n.prop('employee.skill.opr.bind'));
			} else {
				$.messager.alert($.i18n.prop('crud.dialog.tip.title'), $.i18n
						.prop('employee.skill.tip.noNeed'), 'warning');
			}
		});

	} else {
		$.messager.alert($.i18n.prop('crud.dialog.tip.title'), $.i18n
				.prop('employee.skill.tip.noEmp'), 'warning');
	}
}

function callBack(data) {
	$.each(data, function(index, item) {
		var name = data[index].name;
		var id = data[index].id;
		$("#pId").append(
				"<input type='checkbox' name='procedures' value='" + id
						+ "' checked='checked'/>" + name);
	});
}

var save = function() {
	if ($('#fm').form('validate')) {
		$.post("employee?goto=saveProcedure", $("#fm").serialize(),
				function(r) {
					AAW.tip(r);
				});
	}
}

var del = function(id) {
	$.messager.confirm($.i18n.prop('crud.dialog.tip.title'), $.i18n
			.prop('employee.skill.tip.unbind'), function(r1) {
		if (r1) {
			$.post("employee?goto=delProcedure", {
				id : id
			}, function(r) {
				AAW.tip(r);
			});
		}
	});
}
