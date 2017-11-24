$(function() {
	$.BaseCRUD({
		listURL : "productModel?goto=list",
		addURL : "productModel?goto=save",
		updateURL : "productModel?goto=update",
		delURL : "productModel?goto=del",
		titleAddF : $.i18n.prop('model.title.add'),
		titleUpdateF : $.i18n.prop('model.title.update')
	});
	init();
});

var comId = -1;
var modelId = -1;
var checkId=-1;

var ccLoad = function() {
	$("#cc").combobox('reload', 'productionLine?goto=combobox');
}
var backfill = function() {
	$("#cc").combobox('select', AAW.selectedRow().line.id);
}
var init = function() {
	$("#tt")
			.datagrid(
					{
						view : detailview,
						detailFormatter : function(index, row) {
							return '<div style="padding:2px"><table class="ddv"></table></div>';
						},
						onClickRow : function(rowIndex, rowData) {
							var id = rowData.id;
							if(checkId==id||id==modelId){
								return
							}else{
								$("#comtree").tree({
									url : "productModel?goto=componentTree4"
								})
								$("#comtree2").tree({
									url : "productModel?goto=componentTree4"
								})
								$("#comtree3").tree({
									url : "productModel?goto=componentTree4"
								})
								$('#layout1').layout('collapse', 'east');
							}
						},
						onDblClickRow : function(rowIndex, rowData) {
							$("#comtree2").tree({
								url : "productModel?goto=componentTree4"
							})
							$("#comtree3").tree({
								url : "productModel?goto=componentTree4"
							})

							modelId = rowData.id;
							comId = -1
							$("#comtree")
									.tree(
											{
												url : "productModel?goto=componentTree&id="
														+ rowData.id,
												onClick : function(node) {
													if (comId != node.id) {
														if (comId != -1) {
															saveConfig();
// var nodes = $(
// '#comtree2')
// .tree(
// 'getChecked');
// var eid = "";
// for (var i = 0; i < nodes.length; i++) {
// if (i < (nodes.length - 1)) {
// eid += nodes[i].id
// + ",";
// } else {
// eid += nodes[i].id;
// }
// }
//
// var nodes2 = $(
// '#comtree3')
// .tree(
// 'getChecked');
// var pid = nodes2[0].id;
//
// $
// .post(
// "productModel?goto=saveElement",
// {
// "comId" : comId,
// "eid" : eid,
// "modelId" : modelId,
// "pid" : pid
// })
														}

														comId = node.id;
														$("#comtree2")
																.tree(
																		{
																			url : "productModel?goto=componentTree2&id="
																					+ rowData.id
																					+ "&cid="
																					+ node.id
																		})
														$("#comtree3")
																.tree(
																		{
																			url : "productModel?goto=componentTree3&id="
																					+ rowData.id
																					+ "&cid="
																					+ node.id,
																			onClick : function(
																					node) {
																				var cknodes = $(
																						'#comtree3')
																						.tree(
																								"getChecked");
																				for (var i = 0; i < cknodes.length; i++) {
																					if (cknodes[i].id != node.id) {
																						$(
																								'#comtree3')
																								.tree(
																										"uncheck",
																										cknodes[i].target);
																					}
																				}
																				if (node.checked) {
																					$(
																							'#comtree3')
																							.tree(
																									'uncheck',
																									node.target);

																				} else {
																					$(
																							'#comtree3')
																							.tree(
																									'check',
																									node.target);

																				}
																			}
																		})
													}
												}
											});
							$('#layout1').layout('expand', 'east');
						},
						onExpandRow : function(index, row) {
							var ddv = $(this).datagrid('getRowDetail', index)
									.find('table.ddv');
							ddv.datagrid({
								url : 'productModel?goto=cfgList&id=' + row.id,
								pagination : false,
								fit : false,
								height : 'auto',
								columns : cfgArr,
								onResize : function() {
									$("#tt").datagrid('fixDetailRowHeight',
											index);
								},
								onLoadSuccess : function() {
									setTimeout(function() {
										$("#tt").datagrid('fixDetailRowHeight',
												index);
									}, 0);
									$(this)
											.datagrid(
													"autoMergeCells",
													[ 'componentName',
															'attributeName' ]);
								}
							});
							$("#tt").datagrid('fixDetailRowHeight', index);
						}
					});
	cfgSubmit();

	$('#layout1').layout('collapse', 'east');

	$("#btn_save").click(function() {
		saveConfig();
		$.messager.show({
			title : $.i18n.prop('crud.dialog.tip.oprrs'),
			msg :"配置保存成功"
		});
	})

	$("#btn_allSelect").click(function() {
		var cknodes = $('#comtree2').tree("getChildren");
		for (var i = 0; i < cknodes.length; i++) {
			$('#comtree2').tree("check", cknodes[i].target);
		}
	})

	$("#btn_cancel").click(function() {
		var cknodes = $('#comtree2').tree("getChecked");
		for (var i = 0; i < cknodes.length; i++) {
			$('#comtree2').tree("uncheck", cknodes[i].target);
		}
	})
}
function cfgOpts(id) {
	$.ajax({
		async : false,
		cache : false,
		type : 'post',
		url : "productComponent?goto=combobox&id=" + id,
		success : function(data) {
			ccArr = data;
		}
	});
	$.ajax({
		async : false,
		cache : false,
		type : 'post',
		url : "productElement?goto=combobox",
		success : function(data) {
			eleArr = data;
		}
	});
	$("#cEle").combobox({
		data : eleArr
	});
	$("#cCom").combobox({
		data : ccArr
	});
	$("#cPos").combobox({
		data : posCombobox
	});

}
var BtnCfg = function() {
	var row = AAW.selectedRow();
	if (row) {
		cfgProfile(row);
	} else {
		$.messager.alert($.i18n.prop('model.cfg.tip.title'), $.i18n
				.prop('model.cfg.tip.content'), 'warning');
	}
}
var BtnDelRow = function(id) {
	$("#" + id).remove();
}
var index = 1;
var cfgURL;
var ccArr, eleArr;
var BtnAddRow = function() {
	if ($("#cfgBody").find("tr").length > 5) {
		$.messager.alert($.i18n.prop('model.cfg.tip.title'), $.i18n
				.prop('model.cfg.tip.maxLn'), 'warning');
		return;
	}

	var _htm = '<tr id="_tr'
			+ index
			+ '">'
			+ '<td><input name="component[]" class="easyui-combobox pSel" data-options="required:true,onSelect:selCheck,data:ccArr"/></td><td><input name="position[]" class="easyui-combobox" data-options="required:true,data:posCombobox"/></td><td><input name="element[1].id" class="easyui-combobox" data-options="required:true,groupField:\'group\',data : eleArr,multiple:true"/></td>'
			+ '<td><a href="javascript:BtnDelRow(\'_tr' + index
			+ '\');" class="easyui-linkbutton" >'
			+ $.i18n.prop('model.cfg.delRow') + '</a></td></tr>';
	index++;
	var node = $(_htm);
	$("#cfgBody").append(node);
	$.parser.parse(node);
}
var cfgProfile = function(row) {
	$('#dlg-cfg').dialog('center').dialog('open').dialog('setTitle',
			$.i18n.prop('model.title.cfg'));
	var trs = $("#cfgBody").find("tr");
	var len = trs.length;
	if (len > 1) {
		for (var i = 1; i < len; i++) {
			trs.eq(i).remove();
		}
	}
	index = 1;
	cfgURL = "productModel?goto=saveCfg&id=" + row.id;
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
			$.post("productModel?goto=delCfg", {
				id : id
			}, function(m) {
				AAW.tip(m);
			}, 'json');
		}
	});
}
function delCfg2(id, cid) {
	$.messager.confirm($.i18n.prop('crud.dialog.tip.title'), $.i18n
			.prop('crud.dialog.tip.del'), function(r1) {
		if (r1) {
			$.post("productModel?goto=delCfg2", {
				id : id,
				cid : cid
			}, function(m) {
				AAW.tip(m);
			}, 'json');
		}
	});
}
var setV = function(r) {
	var c = $(this).next("input[type='hidden']");
	console.log(c.html())
}

var saveConfig=function(){
	if (modelId != -1 && comId != -1) {
		var nodes = $('#comtree2').tree('getChecked');
		var eid = "";
		for (var i = 0; i < nodes.length; i++) {
			if (i < (nodes.length - 1)) {
				eid += nodes[i].id + ",";
			} else {
				eid += nodes[i].id;
			}
		}

		var nodes2 = $('#comtree3').tree('getChecked');
		var pid = nodes2[0].id;

		$.post("productModel?goto=saveElement", {
			"comId" : comId,
			"eid" : eid,
			"modelId" : modelId,
			"pid" : pid
		})
	}
}

var showPic=function(id) {
	$('#showPic').attr('src', "productElement/showById?id=" + id);
}

