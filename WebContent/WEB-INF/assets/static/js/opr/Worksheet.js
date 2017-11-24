$(function() {
	$.BaseCRUD({
		listURL : "worksheet?goto=list",
		addURL : "worksheet?goto=save",
		updateURL : "worksheet?goto=update",
		delURL : "worksheet?goto=del",
		titleAddF : $.i18n.prop('worksheet.title.add'),
		titleUpdateF : $.i18n.prop('worksheet.title.update')
	});
});
var ccLoad = function() {
	$("#mcc").combobox({
		url : 'mainCommande?goto=combobox',
		onSelect : function(r) {
			$("#scc").combobox({
				url : 'slaveCommande?goto=combobox&id=' + r.id,
				onLoadSuccess : function() {
					var data = $(this).combobox("getData");
					$(this).combobox("clear");
					if (data && data.length > 0) {
						$("#scc").combobox('select', data[0].id);
					}
				}
			});
		}
	});
}

var backfill = function() {
	$("#mcc").combobox({
		url : 'mainCommande?goto=combobox'
	}).combobox('select', AAW.selectedRow().slaveCommande.mainCommande.id);
	$("#scc").combobox(
			{
				url : 'slaveCommande?goto=combobox&id='
						+ AAW.selectedRow().slaveCommande.mainCommande.id
			}).combobox('select', AAW.selectedRow().slaveCommande.id);
}

var WorksheetHandling = function() {
	var r = AAW.selectedRow();
	if (r) {
		$("#handling").dialog(
				{
					title : 'My Dialog',
					width : 800,
					height : 400,
					closed : false,
					cache : false,
					href : 'worksheet?goto=handling&id=' + r.slaveCommande.id
							+ '&wid=' + r.id,
					buttons : [ {
						text : '关闭',
						iconCls : 'icon-cancel',
						handler : function() {
							$("#handling").dialog("close");
						}
					} ]
				});
	} else {
		$.messager.alert($.i18n.prop('crud.dialog.tip.title'), $.i18n
				.prop('worksheet.opr.handling.noChoice'), 'warning');
	}
}

var endWorking = function() {
	var row = $('#tt').datagrid('getSelected');
	if (row) {
		var url = "worksheetdefatil?goto=endworking&wid=" + row.id;
		$.post(url, function() {
			$.messager.alert($.i18n.prop('wt.save.tip'), $.i18n
					.prop('wt.save.tip.ok'), 'info');
			window.location.reload();
		});
	} else {
		$.messager.alert('提示', '请选择需要终止的订单!', 'warning')
	}
}

var test = function() {
	$(this).datagrid("autoMergeCells", [ 'componentName' ]);
}