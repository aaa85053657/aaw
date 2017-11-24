var Workstation = {
	init : function() {
		this.initGrid();
	},
	initGrid : function() {
		var $this = this;
		$.BaseCRUD({
			listURL : "Workstation?act=list",
			addURL : "Workstation?act=save",
			updateURL : "Workstation?act=update",
			delURL : "Workstation?act=del",
			titleAddF : $.i18n.prop('Workstation.title.add'),
			titleUpdateF : $.i18n.prop('Workstation.title.update')
		});

		$("#tt")
				.datagrid(
						{
							view : detailview,
							detailFormatter : function(index, row) {
								return '<div style="padding:2px"><table class="ddv"></table></div>';
							},
							onExpandRow : function(index, row) {
								var ddv = $(this).datagrid('getRowDetail',
										index).find('table.ddv');
								ddv
										.datagrid({
											url : 'Workstation?act=listCfg&id='
													+ row.id,
											pagination : false,
											fit : false,
											height : 'auto',
											columns : cfgArr,
											onResize : function() {
												$("#tt").datagrid(
														'fixDetailRowHeight',
														index);
											},
											onLoadSuccess : function() {
												setTimeout(
														function() {
															$("#tt")
																	.datagrid(
																			'fixDetailRowHeight',
																			index);
														}, 0);
												$(this)
														.datagrid(
																"autoMergeCells",
																[
																		'componentName',
																		'attributeName' ]);
											}
										});
								$("#tt").datagrid('fixDetailRowHeight', index);
							}
						});

	},
	cfg : function() {
		var r = $('#tt').datagrid('getSelected');
		if (r) {
			var _url = "Workstation?act=temp&id=" + r.id;
			$('#win').dialog('refresh', _url).dialog('open');
		} else {
			$.messager.alert($.i18n.prop('crud.dialog.tip.title'), $.i18n
					.prop('wt.cfg.tip.nochoice'), 'warning');
		}
	}
}

$(function() {
	cfgArr = [ [ {
		field : 'codeId',
		title : $.i18n.prop('wk.cfg.codeId'),
		width : 30
	}, {
		field : 'name',
		title : $.i18n.prop('wk.cfg.name'),
		width : 30
	}, {
		field : 'comments',
		title : $.i18n.prop('wk.cfg.comments'),
		width : 30
	} ,{
		field : 'id',
		title : $.i18n.prop('wk.cfg.opr'),
		width : 30,
		formatter : function(v, r) {
			return "<a href='javascript:delCfg(" + v + ")'>删除配置</a>";
		}
	}] ];
	dataArr = [ [ {
		field : 'id',
		title : $.i18n.prop('Workstation.obj.id'),
		width : 30,
		hidden : true
	}, {
		field : 'codeId',
		title : $.i18n.prop('Workstation.obj.codeID'),
		width : 30
	}, {
		field : 'name',
		title : $.i18n.prop('Workstation.obj.name'),
		width : 30
	}, {
		field : 'parameter',
		title : $.i18n.prop('Workstation.obj.parameter'),
		width : 30
	}, {
		field : 'comments',
		title : $.i18n.prop('Workstation.obj.comments'),
		width : 30
	}, {
		field : 'timeCreation',
		title : $.i18n.prop('mCommande.obj.timeCreation'),
		width : 30
	}, {
		field : 'timeModification',
		title : $.i18n.prop('mCommande.obj.timeModification'),
		width : 30
	}, {
		field : 'timeDelete',
		title : $.i18n.prop('mCommande.obj.timeDelete'),
		width : 30
	} ] ];
	valRules = {
		codeID : {
			validator : function(value) {
				return AAW.async("Workstation?act=unique", "codeId", value) == "1";
			},
			message : $.i18n.prop('Workstation.vail.codeID')
		},
		name : {
			validator : function(value) {
				return AAW.async("Workstation?act=unique", "name", value) == "1";
			},
			message : $.i18n.prop('Workstation.vail.name')
		},
		parameter : {
			validator : function(value) {
				return AAW.async("Workstation?act=unique", "parameter", value) == "1";
			},
			message : $.i18n.prop('Workstation.vail.parameter')
		}
	}
	Workstation.init();
	cfgSubmit();
	$.extend($.fn.validatebox.defaults.rules, valRules);
});

var BtnCfg = function() {
	var row = AAW.selectedRow();
	if (row) {
		$("#fm-cfg").form("clear");
		cfgProfile(row);
	} else {
		$.messager.alert($.i18n.prop('crud.dialog.tip.title'), $.i18n
				.prop('wt.cfg.tip.nochoice'), 'warning');
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
		$.messager.alert($.i18n.prop('pline.cfg.tip.title'), $.i18n
				.prop('Workstation.cfg.tip.maxLn'), 'warning');
		return;
	}
	var _htm = "<tr id='_tr"
			+ index
			+ "'><td><label>"
			+ $.i18n.prop('Workstation.cfg.line')
			+ ':</label> <input class="easyui-combobox" data-options="required:true,onSelect:selLine,url:\'productionLine?goto=combobox\'" atid="'
			+ index
			+ '" name="sequenceNum[]" /></td><td><label>'
			+ $.i18n.prop('Workstation.cfg.procedure')
			+ ":</label> <input id='ck_"
			+ index
			+ "' class=\"easyui-combobox pSel\" data-options=\"required:true,onSelect:selCheck\" name='procedure[]' /></td><td><a href=\"javascript:BtnDelRow('_tr"
			+ index
			+ "');\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-remove'\">"
			+ $.i18n.prop('pline.cfg.delRow') + "</a></td></tr>";
	index++;
	var node = $(_htm);
	$("#cfgBody").append(node);
	$.parser.parse(node);
}

var cfgProfile = function(row) {
	cfgURL = "Workstation?goto=saveCfg&id=" + row.id;
	// cfgOpts(row.id);
	wkId = row.id;
	console.log(wkId)
	$('#dlg-cfg').dialog('center').dialog('open').dialog('setTitle',
			$.i18n.prop('wk.title.cfg'));
	var trs = $("#cfgBody").find("tr");
	var len = trs.length;
	if (len > 1) {
		for (var i = 1; i < len; i++) {
			trs.eq(i).remove();
		}
	}
	index = 1;
}
var wkId = 0;
function selLine(rc) {
	// console.log(rc.id);//生产线ID
	// console.log($(this).attr('atid'));//顺序
	var _url = "productionLineConfig?act=combobox&id=" + rc.id + "&wkID="
			+ wkId;
	$("#ck_" + $(this).attr('atid')).combobox('reload', _url);
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
		$.messager.alert($.i18n.prop('model.cfg.tip.title'), $.i18n
				.prop('model.cfg.tip.repeatSel'), 'warning');
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
			$.post("WorkstationProcedure?goto=delCfg", {
				id : id
			}, function(m) {
				AAW.tip(m);
			}, 'json');
		}
	});
}
function cfgOpts(id) {
	$.ajax({
		async : false,
		cache : false,
		type : 'post',
		url : "metaProcedure?goto=combobox&id=" + id,
		success : function(data) {
			pArr = data;
		}
	});
	$("#cP").combobox({
		data : pArr
	});
}