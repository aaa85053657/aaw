$(function() {
	$.BaseCRUD({
		listURL : "productAttribute?goto=list",
		addURL : "productAttribute?goto=save",
		updateURL : "productAttribute?goto=update",
		delURL : "productAttribute?goto=del",
		titleAddF : $.i18n.prop('attribute.title.add'),
		titleUpdateF : $.i18n.prop('attribute.title.update')
	});
	init();
});

var init = function() {
	$("#tt")
			.datagrid(
					{
						view : detailview,
						detailFormatter : function(index, row) {
							return '<div style="padding:2px"><table class="ddv"></table></div>';
						},
						onExpandRow : function(index, row) {
							var ddv = $(this).datagrid('getRowDetail', index)
									.find('table.ddv');
							ddv.datagrid({
								url : 'productAttribute?goto=ElementList&id='
										+ row.id,
								pagination : false,
								fit : false,
								height : 'auto',
								columns : AttributeValueArr,
								onResize : function() {
									$("#tt").datagrid('fixDetailRowHeight',
											index);
								},
								onLoadSuccess : function() {
									setTimeout(function() {
										$("#tt").datagrid('fixDetailRowHeight',
												index);
									}, 0);
									// $.parser.parse();
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
		url : "productElement?goto=combobox&id=" + id,
		success : function(data) {
			pArr = data;
		}
	});
	$("#cPrf").combobox({
		data : pArr
	});
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

	$("#btnUpdate").click(function() {
		var row = AAW.selectedRow();
		if (row && row.component) {
			$("#cid").combobox('select', row.component.id);
		}
	});
}

function delElement(id) {
	$.messager.confirm($.i18n.prop('crud.dialog.tip.title'), $.i18n
			.prop('crud.dialog.tip.del'), function(r1) {
		if (r1) {
			$.post("productAttribute?goto=delElement", {
				id : id
			}, function(m) {
				AAW.tip(m);
			}, 'json');
		}
	});
}
