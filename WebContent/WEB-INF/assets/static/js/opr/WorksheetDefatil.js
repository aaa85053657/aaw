$(function() {
	$.BaseCRUD({
		// listURL : "worksheetdefatil?goto=list",
		addURL : "worksheet?goto=save",
		updateURL : "worksheet?goto=update",
		delURL : "worksheet?goto=del",
		titleAddF : $.i18n.prop('worksheet.title.add'),
		titleUpdateF : $.i18n.prop('worksheet.title.update')
	});
	$("#tt").datagrid({
		url : "worksheetdefatil?goto=map4EUI",
		queryParams : {
			pid : $("#ccP").combobox("getValue")
		}
	});
	window.setInterval("getMessage()", REFRESH_TASK_TIME);
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
		$.post("worksheet?goto=beginWorking&wid=" + r.id, function(data) {
			var msg = null;
			if (data.suc == 1) {
				$("#handling").dialog(
						{
							title : 'My Dialog',
							width : 800,
							height : 400,
							closed : false,
							cache : false,
							href : 'worksheet?goto=handling&id='
									+ r.slaveCommande.id + '&wid=' + r.id,
							buttons : [ {
								text : '关闭',
								iconCls : 'icon-cancel',
								handler : function() {
									$("#handling").dialog("close");
								}
							} ]
						}).dialog('setTitle', "加工单详情");
			} else {
				msg = "<span class='tip_error'>" + data.msg + "</span>";
				$('#tt').datagrid('reload');
				$.messager.show({
					title : '操作结果',
					msg : msg
				});
			}
		});
	} else {
		$.messager.alert($.i18n.prop('crud.dialog.tip.title'), $.i18n
				.prop('worksheet.opr.handling.noChoice'), 'warning');
	}
}

var saveWorking = function() {
	saveworkClick();
	// $("#save").linkbutton({
	// disabled : true
	// });

}

var saveworkClick = function() {
	var url = "worksheetdefatil?goto=working";
	if ($('#fm1').form('validate')) {
		$.post(url, $("#fm1").serialize(), function() {
			$.messager.alert($.i18n.prop('wt.save.tip'), $.i18n
					.prop('wt.save.tip.ok'), 'info');
		});
	}
	$("#save").linkbutton({
		disabled : true
	});
	$("#oprId2").combobox({
		disabled : true
	});
	$("#fm1 .easyui-textbox").textbox({
		disabled : true
	});
	search4More();
}

var endWorking = function() {
	var url = "worksheetdefatil?goto=endworking";
	if ($('#fm1').form('validate')) {
		$.post(url, $("#fm1").serialize(), function() {
			$.messager.alert($.i18n.prop('wt.save.tip'), $.i18n
					.prop('wt.save.tip.ok'), 'info');
			window.location.reload();
		});
	}
}

var exceptionWorking = function() {
	exceptionClick();
}

var exceptionClick = function() {
	var wid = $("#worksheetIsWorking").val();
	$('#dlg').dialog('center').dialog('open').dialog('setTitle', "异常处理");
	$('#fm').form('clear');
	$
			.post(
					'worksheetdefatil?goto=getWorksheet&wid=' + wid,
					function(data) {
						$("#worksheetDetailId").html("");
						$("#rollBackId").html("");
						$
								.each(
										data,
										function(index, item) {
											var name = data[index].name;
											var id = data[index].id;
											$("#worksheetDetailId")
													.html(
															$(
																	"#worksheetDetailId")
																	.html()
																	+ "<input type='checkbox' name='worksheetDetailId'value="
																	+ id
																	+ " checked='checked'/>"
																	+ name);
											$("#rollBackId")
													.html(
															$("#rollBackId")
																	.html()
																	+ "<input type='checkbox' name='rollBackId'value="
																	+ id
																	+ " checked='checked'/>"
																	+ name);
										})
					});

}

var saveException = function() {
	$('#exceptionfm').form('submit', {
		url : 'worksheetdefatil?goto=saveExcption',
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(r) {
			var data = eval('(' + r + ')');
			$('#dlg').dialog('close');
			$("#handling").dialog('close')
			var msg = null;
			if (data.suc == 1) {
				msg = "<span class='tip_suc'>" + data.msg + "</span>";
				search4More();
			} else {
				msg = "<span class='tip_error'>" + data.msg + "</span>";
			}
			$.messager.show({
				title : '操作结果',
				msg : msg
			});
		}
	});
}

var beginClick = function() {
	var wid = $("#worksheetIsWorking").val();
	$("#timeoutfm").form('clear');
	$("#timeout").combobox('select', 10);
	$("#wdid").val(wid);
	$("#dlg2").dialog('center').dialog('open').dialog('setTitle', "加工时限");
}

var beginWorking = function() {
	$("#timeoutfm").form('submit', {
		url : "worksheetdefatil?goto=beginwork",
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(r) {
			var data = eval('(' + r + ')');
			var msg = null;
			if (data.suc == "1") {
				msg = "<span>" + data.msg + "</span>";
				$.messager.alert("提示", msg);
				$("#dlg2").dialog('close');
				$("#oprId").combobox('readonly', false);
				$("#fm1 .easyui-textbox").textbox('readonly', false);
				$("#div1").hide();
				$("#div2").show();
				$("#divMain").show();
			} else {
				msg = "<span>" + data.msg + "</span>";
				$.messager.alert("提示", msg);
				window.location.reload();
			}

		}
	});
}

var getMessage = function() {
	$('#tt').datagrid('reload');
}

var search4More = function(row) {
	$("#tt").datagrid({
		url : "worksheetdefatil?goto=map4EUI",
		queryParams : {
			pid : $("#ccP").combobox("getValue")
		}
	});
}
