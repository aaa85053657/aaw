$(function() {
	init();
});

var init = function() {
	window.editorFrame = $('#reportEditorWindow')[0];
	window.editorDlg = $('#reportEditorDlg').dialog({
		buttons : [ {
			text : 'Analyse',
			handler : function() {
				window.editorFrame.contentWindow.editor.analyse();
			},
		}, {
			text : 'Save',
			handler : function() {
				window.editorFrame.contentWindow.editor.save();
			},
		} ],
		onClose : function() {
			editorFrame.src = "about:blank";
			$('#tt').datagrid('reload');
		},
	});
	window.editorFrame.onload = function() {
		window.editorFrame.contentWindow.showButtons = false;
		window.editorFrame.contentWindow.showMessage = function(msg) {
			$.messager.show({
				title : $.i18n.prop('crud.dialog.tip.oprrs'),
				msg : msg
			});
		}
	}
	$("#tt").datagrid({
		url : "reportCtl?goto=list",
		fitColumns : true,
		columns : dataArr,
		toolbar : "#toolbar",
		onDblClickRow : function(rowIndex, rowData) {
			var rid = rowData.id;
			$('#dlg-detail').dialog({
				href : 'reportCtl?goto=reportMain&rid=' + rid,
			}).dialog('open').dialog('setTitle', "报表").dialog('center');

			// window
			// .open(
			// "reportCtl?goto=reportMain&rid="
			// + rid,
			// "报表",
			// "width="
			// + (screen.availWidth - 10)
			// + ",height="
			// + (screen.availHeight - 50)
			// +
			// ",dependent=yes,resizable=yes,width=768px,toolbar=no,menubar=no,scrollbars=yes,location=no,stssatus=no");
		}
	});
}

var addReport = function() {
	// $('#dlg').dialog({
	// href : '/aaw/report/static/reportEditor.html',
	// }).dialog('open').dialog('setTitle', "创建报表").dialog('center');

	// var windowFeatures =
	// "toolbar=no,scrollbars=yes,resizable=yes,menubar=no,location=no,status=no,top=100,left=100,width=800,height=600";
	// window.open("/aaw/report/static/reportEditor.html", "_blank",
	// windowFeatures, true);

	editorFrame.src = "/aaw/report/static/reportEditor.html";
	editorDlg.dialog('open').dialog('setTitle', "创建报表").dialog('center');
}

var updateReoprt = function() {
	var row = $("#tt").datagrid('getSelected');
	if (!row) {
		alert("请选中需要修改的数据！");
		return;
	}
	// var windowFeatures =
	// "toolbar=no,scrollbars=yes,resizable=yes,menubar=no,location=no,status=no,top=100,left=100,width=800,height=600";
	// window.open("/aaw/report/static/reportEditor.html?reportId=" + row.id,
	// "_blank", windowFeatures, true);

	editorFrame.src = "/aaw/report/static/reportEditor.html?reportId=" + row.id;
	editorDlg.dialog('open').dialog('setTitle', "编辑报表").dialog('center');
}

var deleteReport = function(reportId) {
	var deferred = $.ajax({
		url : window.config.root + "/report/report/" + reportId,
		method : "DELETE",
		async : true,
	}).done(function(data) {
		// no-op
	}).fail(function(jqXHR) {
		alert(jqXHR.responseText);
	});
	return deferred;
}

var delClick = function() {
	var row = $("#tt").datagrid('getSelected');
	if (row) {
		$.messager.confirm("yes", "are you sure to delete this report?",
				function(r) {
					if (r) {
						deleteReport(row.id).done(function() {
							$('#tt').datagrid('reload');
							$.messager.show({
								title : $.i18n.prop('crud.dialog.tip.oprrs'),
								msg : "delete success"
							});
						});
					}
				});
	} else {
		alert("请选中需要修改的数据！");
	}
}