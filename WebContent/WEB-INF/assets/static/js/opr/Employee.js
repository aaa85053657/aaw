$(function() {
	$.BaseCRUD({
		listURL : "employee?goto=list2",
		addURL : "employee?goto=save",
		updateURL : "employee?goto=update",
		delURL : "employee?goto=del",
		titleAddF : $.i18n.prop('employee.title.add'),
		titleUpdateF : $.i18n.prop('employee.title.update')
	});
	Emp.init();
});
var Emp = {
	cAccUrl : "",
	barcodeUrl : "employee?act=badgeCode&code=",
	init : function() {
		$("#tt").datagrid({
			rowStyler : function(index, row) {
				if (row.name == "SuperAdministrator") {
					return 'display:none;';
				}
			}
		});
		$('#csex').combobox({
			data : sexCombobox
		});
		$('#cincumbent').combobox({
			data : incumbentCombobox
		});
		$('#cstaff').combobox({
			data : staffCombobox,
			onSelect : function(r) {
				switch (parseInt(r.id)) {
				case 1:
					AAW.textBoxChange("company", true);
					AAW.textBoxChange("cAddr", true);
					break;
				case 2:
					AAW.textBoxChange("company", false);
					AAW.textBoxChange("cAddr", false);
					break;
				break;
			}
		}
		});
	},
	backfill : function() {
		var row = AAW.selectedRow();
		$("#bcde").attr("src", "employee?act=badgeCode&code=" + row.badgeCode);
		$('#csex').combobox('select', row.sex);
		$('#cstaff').combobox('select', row.isStaff);
		$('#cincumbent').combobox('select', row.isIncumbent);
		$("#ccountry").combobox('select', row.country.id);
		$('input[name="isStaff"]').val(row.isStaff);
	},
	ccLoad : function() {
		$("#ccountry").combobox('reload', 'country?goto=combobox');
		var d = new Date().getTime();
		$("input[name='badgeCode']").val(d);
		$("#bcde").attr("src", "employee?act=badgeCode&code=" + d);

	},
	updateAcc : function() {
		var row = $("#tt").datagrid('getSelected');
		if (row) {
			if (row.account != null) {
				$("#fmAcc").form('clear').form('load', row.account);
				$("#dlg_cAcc").dialog('open').dialog('setTitle',
						$.i18n.prop('employee.acc.add'));
				$("#validator_id").val(row.account.id);
				$("#role").combobox('select', row.account.role.id);
				this.cAccUrl = "UpmsAccount?act=update&eid=" + row.id;
			} else {
				$.messager.alert($.i18n.prop('crud.dialog.tip.title'),
						"不可能的弹窗！请联系技术", 'warning');
			}
		} else {
			$.messager.alert($.i18n.prop('crud.dialog.tip.title'), $.i18n
					.prop('employee.acc.tip.nc'), 'warning');
		}
	},
	createAcc : function() {
		var row = $("#tt").datagrid('getSelected');
		if (row) {
			if (row.account == null) {
				$("#fmAcc").form('clear').form('load', row);
				$("#dlg_cAcc").dialog('open').dialog('setTitle',
						$.i18n.prop('employee.acc.add'));
				$("#validator_id").val(0);
				this.cAccUrl = "UpmsAccount?act=save&eid=" + row.id;
			} else {
				$.messager.alert($.i18n.prop('crud.dialog.tip.title'), $.i18n
						.prop('employee.acc.tip.repeat'), 'warning');
			}
		} else {
			$.messager.alert($.i18n.prop('crud.dialog.tip.title'), $.i18n
					.prop('employee.acc.tip.nc'), 'warning');
		}
	},
	saveAcc : function() {
		if ($('#fmAcc').form('validate')) {
			$.post(this.cAccUrl, $("#fmAcc").serialize(), this.accRs);
		}
	},
	accRs : function(data) {
		$('#dlg_cAcc').dialog('close');
		var msg = null;
		if (data.suc == 1) {
			$('#tt').datagrid('reload');
			msg = "<span class='tip_suc'>" + data.msg + "</span>";
		} else {
			msg = "<span class='tip_error'>" + data.msg + "</span>";
		}
		$.messager.show({
			title : $.i18n.prop('crud.dialog.tip.oprrs'),
			msg : msg
		});
	}
}
