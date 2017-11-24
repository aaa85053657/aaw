$.extend($.fn.dialog.defaults, {
	closed : true,
	modal : true,
	onShowPanel : function() {
		$(this).dialog("center");
	},
	buttons : '#dlg-buttons'
});
$.extend($.fn.datagrid.defaults, {
	plain : true,
	fit : true,
	fitColumns : true,
	hideColumn : 'id',
	striped : true,
	singleSelect : true,
	rownumbers : true,
	pagination : true,
	pageSize : 20,
	pageList : [ 20, 50, 100 ]
});
$.extend($.fn.treegrid.defaults, {
	plain : true,
	fit : true,
	fitColumns : true,
	striped : true,
	singleSelect : true,
	rownumbers : true,
	lines : true
});
$.extend($.fn.combobox.defaults, {
	editable : false,
	valueField : 'id',
	textField : 'text',
	panelHeight : 'auto',
	panelMaxHeight : 200,
	// onLoadSuccess : function(r) {
	// if (r && r.length > 0) {
	// $(this).combobox("select", r[0].id);
	// }
	// },
	onShowPanel : function() {
		// var s = $(this).combobox('getData')
		// if (s.length == 0) {
		// $(this).combobox('options').url =
		// "/game/gameList.action?operateStatusId=1";
		$(this).combobox('reload');
		// }
	}
});
$.extend($.fn.pagination.defaults, {
	pageSize : 20,
	pageList : [ 20, 50, 100 ]
});
$.extend($.fn.validatebox.methods, {
	remove : function(jq, newposition) {
		return jq.each(function() {
			$(this).removeClass("validatebox-text validatebox-invalid").unbind(
					'focus').unbind('blur');
		});
	},
	reduce : function(jq, newposition) {
		return jq.each(function() {
			var opt = $(this).data().validatebox.options;
			$(this).addClass("validatebox-text").validatebox(opt);
		});
	}
});

var createGridHeaderContextMenu = function(e, field) { // 为datagrid、treegrid增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中
	e.preventDefault();
	var grid = $(this);
	var headerContextMenu = this.headerContextMenu;
	if (!headerContextMenu) {
		var fields = grid.datagrid('getColumnFields');
		var tmenu = null;
		if (fields.length > 15) {
			tmenu = $('<div style="width:100px;"></div>').appendTo('body');
			var t1 = $('<div></div>').appendTo(tmenu);
			var t2 = $('<div></div>').appendTo(tmenu);
			t1.append($("<span>" + $.i18n.prop('grid.title.tip.col1')
					+ "</span>"));
			t2.append($("<span>" + $.i18n.prop('grid.title.tip.col2')
					+ "</span>"));

			var c1 = $('<div></div>');
			var c2 = $('<div></div>');

			for (var i = 0; i < fields.length; i++) {
				var fildOption = grid.datagrid('getColumnOption', fields[i]);
				if (i > 15) {
					if (!fildOption.hidden) {
						$('<div iconCls="icon-ok" field="' + fields[i] + '"/>')
								.html(fildOption.title).appendTo(c1);
					} else {
						$(
								'<div iconCls="icon-empty" field="' + fields[i]
										+ '"/>').html(fildOption.title)
								.appendTo(c1);
					}
				} else {
					if (!fildOption.hidden) {
						$('<div iconCls="icon-ok" field="' + fields[i] + '"/>')
								.html(fildOption.title).appendTo(c2);
					} else {
						$(
								'<div iconCls="icon-empty" field="' + fields[i]
										+ '"/>').html(fildOption.title)
								.appendTo(c2);
					}
				}
			}
			t1.append(c1);
			t2.append(c2);
		} else {
			tmenu = $('<div style="width:100px;"></div>').appendTo('body');
			for (var i = 0; i < fields.length; i++) {
				var fildOption = grid.datagrid('getColumnOption', fields[i]);
				if (!fildOption.hidden) {
					$('<div iconCls="icon-ok" field="' + fields[i] + '"/>')
							.html(fildOption.title).appendTo(tmenu);
				} else {
					$('<div iconCls="icon-empty" field="' + fields[i] + '"/>')
							.html(fildOption.title).appendTo(tmenu);
				}
			}
		}

		headerContextMenu = this.headerContextMenu = tmenu.menu({
			onClick : function(item) {
				var field = $(item.target).attr('field');
				if (item.iconCls == 'icon-ok') {
					grid.datagrid('hideColumn', field);
					$(this).menu('setIcon', {
						target : item.target,
						iconCls : 'icon-empty'
					});
				} else {
					grid.datagrid('showColumn', field);
					$(this).menu('setIcon', {
						target : item.target,
						iconCls : 'icon-ok'
					});
				}
			}
		});
	}

	headerContextMenu.menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
$
		.extend(
				$.fn.datagrid.methods,
				{
					doCellTip : function(jq, params) { // 开打提示功能（基于1.3.3版本）
						function showTip(showParams, td, e, dg) {
							if ($(td).text() == "")
								return;
							params = params || {};
							var options = dg.data('datagrid');
							showParams.content = '<div class="tipcontent">'
									+ showParams.content + '</div>';
							$(td)
									.tooltip(
											{
												content : showParams.content,
												trackMouse : true,
												position : params.position,
												onHide : function() {
													$(this).tooltip('destroy');
												},
												onShow : function() {
													var tip = $(this).tooltip(
															'tip');
													if (showParams.tipStyler) {
														tip
																.css(showParams.tipStyler);
													}
													if (showParams.contentStyler) {
														tip
																.find(
																		'div.tipcontent')
																.css(
																		showParams.contentStyler);
													}
												}
											}).tooltip('show');
						}
						;

						return jq
								.each(function() {
									var grid = $(this);
									var options = $(this).data('datagrid');
									if (!options.tooltip) {
										var panel = grid.datagrid('getPanel')
												.panel('panel');
										panel
												.find('.datagrid-body')
												.each(
														function() {
															var delegateEle = $(
																	this)
																	.find(
																			'> div.datagrid-body-inner').length ? $(
																	this)
																	.find(
																			'> div.datagrid-body-inner')[0]
																	: this;
															$(delegateEle)
																	.undelegate(
																			'td',
																			'mouseover')
																	.undelegate(
																			'td',
																			'mouseout')
																	.undelegate(
																			'td',
																			'mousemove')
																	.delegate(
																			'td[field]',
																			{
																				'mouseover' : function(
																						e) {
																					var that = this;
																					var setField = null;
																					if (params.specialShowFields
																							&& params.specialShowFields.sort) {
																						for (var i = 0; i < params.specialShowFields.length; i++) {
																							if (params.specialShowFields[i].field == $(
																									this)
																									.attr(
																											'field')) {
																								setField = params.specialShowFields[i];
																							}
																						}
																					}
																					if (setField == null) {
																						options.factContent = $(
																								this)
																								.find(
																										'>div')
																								.clone()
																								.css(
																										{
																											'margin-left' : '-5000px',
																											'width' : 'auto',
																											'display' : 'inline',
																											'position' : 'absolute'
																										})
																								.appendTo(
																										'body');
																						var factContentWidth = options.factContent
																								.width();
																						params.content = $(
																								this)
																								.text();
																						if (params.onlyShowInterrupt) {
																							if (factContentWidth > $(
																									this)
																									.width()) {
																								showTip(
																										params,
																										this,
																										e,
																										grid);
																							}
																						} else {
																							showTip(
																									params,
																									this,
																									e,
																									grid);
																						}
																					} else {
																						panel
																								.find(
																										'.datagrid-body')
																								.each(
																										function() {
																											var trs = $(
																													this)
																													.find(
																															'tr[datagrid-row-index="'
																																	+ $(
																																			that)
																																			.parent()
																																			.attr(
																																					'datagrid-row-index')
																																	+ '"]');
																											trs
																													.each(function() {
																														var td = $(
																																this)
																																.find(
																																		'> td[field="'
																																				+ setField.showField
																																				+ '"]');
																														if (td.length) {
																															params.content = td
																																	.text();
																														}
																													});
																										});
																						showTip(
																								params,
																								this,
																								e,
																								grid);
																					}
																				},
																				'mouseout' : function(
																						e) {
																					if (options.factContent) {
																						options.factContent
																								.remove();
																						options.factContent = null;
																					}
																				}
																			});
														});
									}
								});
					},
					cancelCellTip : function(jq) { // 关闭消息提示功能（基于1.3.3版本）
						return jq.each(function() {
							var data = $(this).data('datagrid');
							if (data.factContent) {
								data.factContent.remove();
								data.factContent = null;
							}
							var panel = $(this).datagrid('getPanel').panel(
									'panel');
							panel.find('.datagrid-body').undelegate('td',
									'mouseover').undelegate('td', 'mouseout')
									.undelegate('td', 'mousemove')
						});
					}
				});
$.extend($.fn.layout.paneldefaults, { // 添加layout折叠标题显示
	onCollapse : function() {
		var layout = $(this).parents("div.layout");
		var opts = $(this).panel("options");
		var expandKey = "expand" + opts.region.substring(0, 1).toUpperCase()
				+ opts.region.substring(1);
		var expandPanel = layout.data("layout").panels[expandKey];
		if (expandPanel) {
			if (opts.region == "west" || opts.region == "east") {
				var split = [];
				for (var i = 0; i < opts.title.length; i++) {
					split.push(opts.title.substring(i, i + 1));
				}
				expandPanel.panel("body").addClass("panel-title").css(
						"text-align", "center").html(split.join("<br>"));
			} else {
				expandPanel.panel("setTitle", opts.title);
			}
		}
	}
});
$
		.extend(
				$.fn.datagrid.methods,
				{
					addToolbarItem : function(jq, items) {
						return jq
								.each(function() {
									var toolbar = $(this).parent().prev(
											"div.datagrid-toolbar");
									for (var i = 0; i < items.length; i++) {
										var item = items[i];
										if (item === "-") {
											toolbar
													.append('<div class="datagrid-btn-separator"></div>');
										} else {
											var btn = $("<a href=\"javascript:void(0)\"></a>");
											btn[0].onclick = eval(item.handler
													|| function() {
													});
											btn.css("float", "left").appendTo(
													toolbar).linkbutton(
													$.extend({}, item, {
														plain : true
													}));
										}
									}
									toolbar = null;
								});
					},
					removeToolbarItem : function(jq, param) {
						return jq
								.each(function() {
									var btns = $(this).parent().prev(
											"div.datagrid-toolbar").children(
											"a");
									var cbtn = null;
									if (typeof param == "number") {
										cbtn = btns.eq(param);
									} else if (typeof param == "string") {
										var text = null;
										btns
												.each(function() {
													text = $(this).data().linkbutton.options.text;
													if (text == param) {
														cbtn = $(this);
														text = null;
														return;
													}
												});
									}
									if (cbtn) {
										var prev = cbtn.prev()[0];
										var next = cbtn.next()[0];
										if (prev
												&& next
												&& prev.nodeName == "DIV"
												&& prev.nodeName == next.nodeName) {
											$(prev).remove();
										} else if (next
												&& next.nodeName == "DIV") {
											$(next).remove();
										} else if (prev
												&& prev.nodeName == "DIV") {
											$(prev).remove();
										}
										cbtn.remove();
										cbtn = null;
									}
								});
					}
				});
// 用法：

// $('#tt').datagrid("addToolbarItem",[{"text":"xxx"},"-",{"text":"xxxsss","iconCls":"icon-ok"}])
//
// $('#tt').datagrid("removeToolbarItem","GetChanges")//根据btn的text删除
//
// $('#tt').datagrid("removeToolbarItem",0)//根据下标删除

$.extend($.fn.dialog.methods, {
	addButtonsItem : function(jq, items) {
		return jq.each(function() {
			var buttonbar = $(this).children("div.dialog-button");
			for (var i = 0; i < items.length; i++) {
				var item = items[i];
				var btn = $("<a href=\"javascript:void(0)\"></a>");
				btn[0].onclick = eval(item.handler || function() {
				});
				btn.css("float", "left").appendTo(buttonbar).linkbutton(
						$.extend({}, item, {
							plain : false
						}));
			}
			buttonbar = null;
		});
	},
	removeButtonsItem : function(jq, param) {
		return jq.each(function() {
			var btns = $(this).children("div.dialog-button").children("a");
			var cbtn = null;
			if (typeof param == "number") {
				cbtn = btns.eq(param);
			} else if (typeof param == "string") {
				var text = null;
				btns.each(function() {
					text = $(this).data().linkbutton.options.text;
					if (text == param) {
						cbtn = $(this);
						text = null;
						return;
					}
				});
			}
			if (cbtn) {
				var prev = cbtn.prev()[0];
				var next = cbtn.next()[0];
				if (prev && next && prev.nodeName == "DIV"
						&& prev.nodeName == next.nodeName) {
					$(prev).remove();
				} else if (next && next.nodeName == "DIV") {
					$(next).remove();
				} else if (prev && prev.nodeName == "DIV") {
					$(prev).remove();
				}
				cbtn.remove();
				cbtn = null;
			}
		});
	}
});
$
		.extend(
				$.fn.dialog.methods,
				{
					addToolbarItem : function(jq, items) {
						return jq
								.each(function() {
									var dpanel = $(this);
									var toolbar = dpanel
											.children("div.dialog-toolbar");
									if (!toolbar.length) {
										toolbar = $(
												"<div class=\"dialog-toolbar\"><table cellspacing=\"0\" cellpadding=\"0\"><tr></tr></table></div>")
												.prependTo(dpanel);
										$(this).dialog('resize');
									}
									var tr = toolbar.find("tr");
									for (var i = 0; i < items.length; i++) {
										var btn = items[i];
										if (btn == "-") {
											$(
													"<td><div class=\"dialog-tool-separator\"></div></td>")
													.appendTo(tr);
										} else {
											var td = $("<td></td>")
													.appendTo(tr);
											var b = $(
													"<a href=\"javascript:void(0)\"></a>")
													.appendTo(td);
											b[0].onclick = eval(btn.handler
													|| function() {
													});
											b.linkbutton($.extend({}, btn, {
												plain : true
											}));
										}
									}
								});
					},
					removeToolbarItem : function(jq, param) {
						return jq
								.each(function() {
									var dpanel = $(this);
									var toolbar = dpanel
											.children("div.dialog-toolbar");
									var cbtn = null;
									if (typeof param == "number") {
										cbtn = toolbar.find("td").eq(param)
												.find('span.l-btn-text');
									} else if (typeof param == "string") {
										cbtn = toolbar
												.find("span.l-btn-text:contains('"
														+ param + "')");
									}
									if (cbtn && cbtn.length > 0) {
										cbtn.closest('td').remove();
										cbtn = null;
									}
								});
					}
				});