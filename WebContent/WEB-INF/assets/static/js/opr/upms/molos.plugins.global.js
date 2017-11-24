$.extend($.fn.datagrid.defaults, {
	plain : true,
	fit : true,
	hideColumn : 'id',
	striped : true,
	fitColumns : true,
	singleSelect : true,
	rownumbers : true,
	loadMsg : "数据装载中......",
	pagination : true,
	pageSize : 20,
	pageList : [ 20, 50, 100 ],
	beforePageText : '第',
	afterPageText : '页    共 {pages} 页',
	displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
	onLoadSuccess : function(data) {
		$(this).datagrid("fixRownumber");
		if (data && data.total > 0) {
			$(this).datagrid("selectRow", 0);
		}
	}
});
$.extend($.fn.combobox.defaults, {
	editable : false,
	valueField : 'id',
	textField : 'text',
	panelHeight : 'auto',
	panelMaxHeight : 200,
	onShowPanel : function() {
		$(this).combobox('reload');
		var r = $(this).combobox('getData')
		if (r && r.length > 0) {
			$(this).combobox("select", r[0].id);
		}
	}
});
$.extend($.fn.combotree.defaults, {
	editable : false,
	panelHeight : 'auto',
	panelMaxHeight : 200,
	onShowPanel : function() {
		$(this).combotree('reload');
		var r = $(this).combobox('getData')
		if (r && r.length > 0) {
			$(this).combobox("select", r[0].id);
		}
	}
});
$.extend($.fn.dialog.defaults, {
	closed : true,
	modal : true,
	onShowPanel : function() {
		$(this).dialog("center");
	}
});

$.extend($.fn.combotree.defaults, {
	editable : false,
	panelHeight : 'auto',
	panelMaxHeight : 200,
	onShowPanel : function() {
		$(this).combotree('reload');
	}
});
// 添加layout折叠标题显示
$.extend($.fn.layout.paneldefaults, {
	onCollapse : function() {
		// 获取layout容器
		var layout = $(this).parents("div.layout");
		// 获取当前region的配置属性
		var opts = $(this).panel("options");
		// 获取key
		var expandKey = "expand" + opts.region.substring(0, 1).toUpperCase()
				+ opts.region.substring(1);
		// 从layout的缓存对象中取得对应的收缩对象
		var expandPanel = layout.data("layout").panels[expandKey];
		// 针对横向和竖向的不同处理方式
		if (opts.region == "west" || opts.region == "east") {
			// 竖向的文字打竖,其实就是切割文字加br
			var split = [];
			for (var i = 0; i < opts.title.length; i++) {
				split.push(opts.title.substring(i, i + 1));
			}
			expandPanel.panel("body").addClass("panel-title").css("text-align",
					"center").html(split.join("<br>"));
		} else {
			expandPanel.panel("setTitle", opts.title);
		}
	}
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

/**
 * 
 * @requires jQuery,EasyUI
 * 
 * 为datagrid、treegrid增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中
 */
var createGridHeaderContextMenu = function(e, field) {
	e.preventDefault();
	var grid = $(this);/* grid本身 */
	var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */
	if (!headerContextMenu) {
		var fields = grid.datagrid('getColumnFields');
		var tmenu = null;
		if (fields.length > 15) {
			tmenu = $('<div style="width:100px;"></div>').appendTo('body');
			var t1 = $('<div></div>').appendTo(tmenu);
			var t2 = $('<div></div>').appendTo(tmenu);
			t1.append($("<span>列一</span>"));
			t2.append($("<span>列二</span>"));

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
					/**
					 * 开打提示功能（基于1.3.3版本）
					 * 
					 * @param {}
					 *            jq
					 * @param {}
					 *            params 提示消息框的样式
					 * @return {}
					 */
					doCellTip : function(jq, params) {
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
																					// if($(this).attr('field')===undefined)
																					// return;
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
					/**
					 * 关闭消息提示功能（基于1.3.3版本）
					 * 
					 * @param {}
					 *            jq
					 * @return {}
					 */
					cancelCellTip : function(jq) {
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
					},
					fixRownumber : function(jq) {
						return jq
								.each(function() {
									var panel = $(this).datagrid("getPanel");
									// 获取最后一行的number容器,并拷贝一份
									var clone = $(".datagrid-cell-rownumber",
											panel).last().clone();
									// 由于在某些浏览器里面,是不支持获取隐藏元素的宽度,所以取巧一下
									clone.css({
										"position" : "absolute",
										left : -1000
									}).appendTo("body");
									var width = clone.width("auto").width();
									// 默认宽度是25,所以只有大于25的时候才进行fix
									if (width > 25) {
										// 多加5个像素,保持一点边距
										$(
												".datagrid-header-rownumber,.datagrid-cell-rownumber",
												panel).width(width + 5);
										// 修改了宽度之后,需要对容器进行重新计算,所以调用resize
										$(this).datagrid("resize");
										// 一些清理工作
										clone.remove();
										clone = null;
									} else {
										// 还原成默认状态
										$(
												".datagrid-header-rownumber,.datagrid-cell-rownumber",
												panel).removeAttr("style");
									}
								});
					}
				});
// -------------------
/**
 * 全局常用函数封装
 */
var G = {
	trim : function(str) {// 删除左右两端的空格
		return str.replace(/(^\s*)|(\s*$)/g, "");
	},
	ltrim : function(str) { // 删除左边的空格
		return str.replace(/(^\s*)/g, "");
	},
	rtrim : function(str) { // 删除右边的空格
		return str.replace(/(\s*$)/g, "");
	},
	/**
	 * 判断变量是否空值
	 * 
	 * @param v
	 *            需要判断的变量
	 * @returns undefined, null, '', false, 0, [], {} 均返回true，否则返回false
	 */
	empty : function(v) {
		switch (typeof v) {
		case 'undefined':
			return true;
		case 'string':
			return this.trim(v).length == 0;
		case 'boolean':
			return !v;
		case 'number':
			return 0 === v;
		case 'object':
			if (null === v || (undefined != v.length && v.length == 0)) {
				return true;
			}
			for ( var k in v) {
				return false;
			}
			return true;
		}
		return false;
	},
	/**
	 * 条件查询条件封装，主要用于datagrid的条件查询
	 */
	queryParams : function(obj) {
		var arr = $(obj).serializeArray();
		var str = "";
		for ( var i in arr) {
			var s1 = "";
			if (arr[i].value == null || arr[i].value == "") {
				s1 = '""';
			} else {
				s1 = '"' + arr[i].value + '"';
			}
			var s = '"' + arr[i].name + '"' + ":" + s1;
			str += s;
			if (i != arr.length - 1) {
				str += ",";
			}
		}
		str = "{" + str + "}";
		return $.parseJSON(str);
	},
	/**
	 * 用于验证唯一字段值的同步AJAX请求方法，结合底层的unique方法使用
	 * 
	 * @param _url
	 *            请求地址
	 * @param propName
	 *            需要去重复的字段名
	 * @param propVal
	 *            需要去重复的字段值
	 * @param idVal
	 *            ID的值，0为新增判定，否则为已有数据的情况判定，ID值确认是否验证的为本条数据
	 * @returns
	 */
	uniqueReq : function(_url, propName, propVal, idVal) {
		return $.ajax({
			async : false,
			cache : false,
			type : 'post',
			url : _url,
			data : {
				'propName' : propName,
				'propVal' : propVal,
				'id' : idVal
			}
		}).responseText;
	},
	/**
	 * 获取datagrid/treegird选中的一行； 第一个参数表示数据所在的ID，默认为tt,
	 * 第二个参数如果为true表示获取的为treegrid的一行，或者为datagrid
	 * 
	 */
	selectRow : function() {
		var id = "tt";
		var row;
		if (arguments[0]) {
			id = arguments[0];
		}
		if (arguments[1]) {
			row = $('#' + id).treegrid('getSelected');
		} else {
			row = $('#' + id).datagrid('getSelected');
		}
		return row;
	},
	/**
	 * 用于直接alert提示
	 */
	alert : function(msg) {
		$.messager.alert('提示', msg, 'warning');
	},
	/**
	 * 表单保存及数据删除通用提示方法,，需要结合后台的统一返回提示信息标准
	 */
	tip : function(rs, callback) {
		var msg = null;
		if (rs.suc == 1) {
			if (typeof callback == 'function') {
				callback();
			}
			msg = "<span class='tip_suc'>" + rs.msg + "</span>";
		} else {
			msg = "<span class='tip_error'>" + rs.msg + "</span>";
		}
		$.messager.show({
			title : '操作结果',
			msg : msg
		});
	},
	/**
	 * @description 通过ID删除数据的通用方法，需要结合后台的统一返回提示信息标准
	 * @param p1
	 *            数据所在的ID,默认为tt
	 * @param p2
	 *            如果为true表示获取的为treegrid的一行，或者为datagrid
	 * @param p3
	 *            删除提示语句
	 * @param _url
	 *            删除提交地址
	 * @param callback
	 *            回调函数，删除成功时触发
	 */
	delByID : function(p1, p2, p3, _url, callback) {
		var row = this.selectRow(p1, p2);
		var $this = this;
		if (row) {
			$.messager.confirm('删除提示', p3, function(r) {
				if (r) {
					$.post(_url, {
						id : row.id
					}, function(data) {
						$this.tip(data, callback);
					}, 'json');
				}
			});
		} else {
			this.alert('请选择需要删除的数据!');
		}
	},
	/**
	 * 通用dialog打开方法
	 * 
	 * @param title
	 *            窗体的标题
	 * @param id
	 *            需要打开窗体的ID
	 */
	dw : function(title, id) {
		$('#' + id).dialog("center").dialog({
			title : title
		}).dialog("open");
	},
	/**
	 * 通用保存表单方法，适合无需额外处理的表单的保存方式
	 * 
	 * @param id
	 *            需要保存的表单ID
	 * @param _url
	 *            提交的保存地址
	 * @param callback
	 *            回调函数，保存成功后的处理
	 */
	save : function(id, _url, callback) {
		var $this = this;
		var fm = $('#' + id);
		if (fm.form('validate')) {
			$.post(_url, fm.serialize(), function(data) {
				$this.tip(data, callback);
			});
		}
	},
	sync : function(_url, dd, cb) {
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			url : _url,
			data : dd,
			success : cb
		})
	},
	/**
	 * 选中动态的下拉列表
	 * 
	 * @param {[字符串]}
	 *            comID 组件ID
	 * @param {[]}
	 *            val 被选中的值
	 */
	ccs : function(comID, val) {
		$("#" + comID).combobox("select", val);
	}
}
