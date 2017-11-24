$(function() {
			$.BaseCRUD({
						listURL : "metaProcedure?goto=list",
						addURL : "metaProcedure?goto=save",
						updateURL : "metaProcedure?goto=update",
						delURL : "metaProcedure?goto=del",
						titleAddF : $.i18n.prop('procedure.title.add'),
						titleUpdateF : $.i18n.prop('procedure.title.update')
					});
			init();
		});

var init = function() {
	$("#tt").datagrid({
		view : detailview,
		detailFormatter : function(index, row) {
			return '<div style="padding:2px"><table class="ddv"></table></div>';
		},
		onExpandRow : function(index, row) {
			var ddv = $(this).datagrid('getRowDetail', index).find('table.ddv');
			ddv.datagrid({
						url : 'metaProcedure?goto=profileList&id=' + row.id,
						pagination : false,
						fit : false,
						height : 'auto',
						columns : profileArr,
						onResize : function() {
							$("#tt").datagrid('fixDetailRowHeight', index);
						},
						onLoadSuccess : function() {
							setTimeout(function() {
										$("#tt").datagrid('fixDetailRowHeight',
												index);
									}, 0);
//							$.parser.parse();
						}
					});
			$("#tt").datagrid('fixDetailRowHeight', index);
		}
	});
	cfgSubmit();
}

function cfgOpts(id) {
	$.ajax({
				async : false,
				cache : false,
				type : 'post',
				url : "profile?goto=combobox&id=" + id,
				success : function(data) {
					pArr = data;
				}
			});
	$("#cPrf").combobox({
				data : pArr
			});

}
var BtnCfg = function() {
	var row = AAW.selectedRow();
	if (row) {
		cfgProfile(row);
	} else {
		$.messager.alert($.i18n.prop('procedure.cfg.tip.title'), $.i18n
						.prop('procedure.cfg.tip.content'), 'warning');
	}
}
var BtnDelRow = function(id) {
	$("#" + id).remove();
}
var index = 1;
var cfgURL;
var pArr;
var BtnAddRow = function() {
	if ($("#cfgBody").find("tr").length > 5) {
		$.messager.alert($.i18n.prop('procedure.cfg.tip.title'), $.i18n
						.prop('procedure.cfg.tip.maxLn'), 'warning');
		return;
	}
	var _htm = "<tr id='_tr"
			+ index
			+ "'><td><label>"
			+ $.i18n.prop('procedure.cfg.priority')
			+ ':</label> <input class="easyui-numberbox" data-options="required:true" name="priority[]" /></td><td><label>'
			+ $.i18n.prop('procedure.cfg.profile')
			+ ":</label> <input class=\"easyui-combobox pSel\" data-options=\"required:true,data:pArr,onSelect:selCheck\" name='profile[]'/></td><td><a href=\"javascript:BtnDelRow('_tr"
			+ index
			+ "');\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-remove'\">"
			+ $.i18n.prop('procedure.cfg.delRow') + "</a></td></tr>";
	index++;
	var node = $(_htm);
	$("#cfgBody").append(node);
	$.parser.parse(node);
}
var cfgProfile = function(row) {
	$('#dlg-cfg').dialog('center').dialog('open').dialog('setTitle',
			$.i18n.prop('procedure.title.cfg'));
	var trs = $("#cfgBody").find("tr");
	var len = trs.length;
	if (len > 1) {
		for (var i = 1; i < len; i++) {
			trs.eq(i).remove();
		}
	}
	index = 1;
	cfgURL = "metaProcedure?goto=saveCfg&id=" + row.id;
	cfgOpts(row.id);
}

var selCheck = function(rc) {
	var selArr = new Array();
	var _this = $(this);
	$(".pSel").each(function() {
				var v = $(this).combobox('getValue');
				if (!AAW.empty(v)) {
					selArr.push(v);
				}
			});
	if (inArray(selArr, rc.id)) {
		$.messager.alert($.i18n.prop('procedure.cfg.tip.title'), $.i18n
						.prop('procedure.cfg.tip.repeatSel'), 'warning');
		_this.combobox('clear');
	} else {
		selArr.push(rc);
	}
}

function inArray(arr, val) {
	var i = 0, len = arr.length;
	if (len < 2) {
		return false;
	}
	var rp = 0;
	for (; i < len; i++) {
		if (parseInt(arr[i]) == parseInt(val)) {
			rp++;
		}
		if (rp == 2) {
			return true;
		}
	}
	return false;
}

var cfgSubmit = function() {
	$("#btnCfg").click(function() {
				$('#fm-cfg').form('submit', {
							url : cfgURL,
							onSubmit : function() {
								return $(this).form('validate');
							},
							success : function(r) {
								AAW.saveTip(r, "dlg-cfg", "tt");
							}
						});
			});
}

function delCfg(id) {
	$.messager.confirm($.i18n.prop('crud.dialog.tip.title'), $.i18n
					.prop('crud.dialog.tip.del'), function(r1) {
				if (r1) {
					$.post("metaProcedure?goto=delCfg", {
								id : id
							}, function(m) {
								AAW.tip(m);
							}, 'json');
				}
			});
}