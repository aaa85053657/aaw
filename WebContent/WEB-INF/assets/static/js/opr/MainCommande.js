$(function() {
	$.BaseCRUD({
		listURL : "mainCommande?goto=listMDC",
		addURL : "mainCommande?goto=save",
		updateURL : "mainCommande?goto=update",
		delURL : "mainCommande?goto=del",
		titleAddF : $.i18n.prop('mCommande.title.add'),
		titleUpdateF : $.i18n.prop('mCommande.title.update')
	});
	init();
	ccLoad();

	// 打开新增窗体
	$("#btnAdd2").click(function() {
		$('#dlg').dialog('center').dialog('open').dialog('setTitle', "新增订单信息");
		$('#fm').form('clear');
		if ($("#validator_id")) {
			$("#validator_id").val(0);
		}
		$("#c2").combobox({
			disabled : false
		});
	});

	// 打开修改窗体
	$("#btnUpdate2")
			.click(
					// $("#c2")
					function() {
						var r = $('#tt').datagrid('getSelected');
						if (r) {
							if (r.seller != null) {
								$('#dlg').dialog('open').dialog('setTitle',
										"修改订单");
								$('#fm').form('clear').form('load', r);
								$("#c2").combobox({
									disabled : true
								});
								if ($("#validator_id")) {
									$("#validator_id").val(r.id);
								}
							} else {
								$.messager
										.confirm(
												'Confirm',
												"该订单为加盟商订单，确认修改？",
												function(rd) {
													if (rd) {
														$('#dlg')
																.dialog('open')
																.dialog(
																		'setTitle',
																		"修改订单");
														$('#fm')
																.form('clear')
																.form('load', r);
														$("#c1").combobox(
																'select',
																r.priority.id);
														$("#c2").combobox(
																'select',
																r.type.id);
														$("#c3").combobox(
																'select',
																r.customer.id);
														$("#c4").combobox(
																'select',
																r.address.id);
														$("#c5")
																.combobox(
																		{
																			onLoadSuccess : function() {
																				if (r != null
																						&& r.fid != null
																						&& r.seller == null) {
																					$(
																							"#c5")
																							.combobox(
																									'select',
																									r.fid.id);
																				}
																			}
																		});
														if ($("#validator_id")) {
															$("#validator_id")
																	.val(r.id);
														}
													}
												});
							}
						} else {
							$.messager.alert($.i18n
									.prop('crud.dialog.tip.title'), $.i18n
									.prop('crud.dialog.tip.update'), 'warning');
						}
					});

	$("#btnSave2").click(
			function() {
				var vid = $("#validator_id").val();
				if (vid == 0) {
					if ($("#fm").form('validate')) {
						$.post("mainCommande?goto=save", $("#fm").serialize(),
								function(data) {
									$('#dlg').dialog('close');
									if (data.suc == 1) {
										$('#tt').datagrid('reload');
										msg = "<span class='tip_suc'>"
												+ data.msg + "</span>";
									} else {
										msg = "<span class='tip_error'>"
												+ data.msg + "</span>";
									}
									$.messager.show({
										title : $.i18n
												.prop('crud.dialog.tip.oprrs'),
										msg : msg
									});
								});
					}
				} else {
					if ($("#fm").form('validate')) {
						var id = $("#validator_id").val();
						$.post("mainCommande?goto=update&id=" + id, $("#fm")
								.serialize(), function(data) {
							$('#dlg').dialog('close');
							if (data.suc == 1) {
								$('#tt').datagrid('reload');
								msg = "<span class='tip_suc'>" + data.msg
										+ "</span>";
							} else {
								msg = "<span class='tip_error'>" + data.msg
										+ "</span>";
							}
							$.messager.show({
								title : $.i18n.prop('crud.dialog.tip.oprrs'),
								msg : msg
							});
						});
					}

				}

			});

});

var MC = {
	cusID : "",
	save : function(id, _url, callback) {
		var $this = this;
		var fm = $('#' + id);
		if (fm.form('validate')) {
			$.post(_url, fm.serialize(), function(data) {
				if (data.suc == 1) {
					msg = "<span class='tip_suc'>" + data.msg + "</span>";
				} else {
					msg = "<span class='tip_error'>" + data.msg + "</span>";
				}
				$.messager.show({
					title : $.i18n.prop('crud.dialog.tip.oprrs'),
					msg : msg
				});
				callback();
			});
		}
	},
	openFmCus : function() {
		$("#fmCus").form('clear');
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			url : "mainCommande?goto=scode2",
			success : function(rs) {
				$("#codeId2").textbox('setValue', rs);
			}
		});

		$("#dlg-cus").dialog('open').dialog('setTitle',
				$.i18n.prop('employee.acc.add'));
	},
	openSrcCus : function() {
		$("#dlg-customer").dialog('open').dialog('setTitle', '用户筛选');
		$("#custt").datagrid({
			url : 'customerInfo?goto=list2',
			fitColumns : true,
			columns : custArr,
			onDblClickRow : function() {
				var row = $('#custt').datagrid('getSelected');
				var sid = row.id;
				$("#c3").combobox('select', sid);
				$('#dlg-customer').dialog('close');
			}
		});
		$("#custt").datagrid('enableFilter', [ {
			field : 'id',
			type : 'numberbox',
			op : [ 'equal' ]
		}, {
			field : 'sex',
			type : 'numberbox',
			op : [ 'equal' ]
		} ]);
	},
	getReset : function() {
		$("#custt").datagrid('reload');
		$('#custt').datagrid('removeFilterRule');
	},
	openFmAddr : function() {
		var selectCusId = $("#c3").combobox("getValue");
		if (selectCusId && selectCusId > 0) {
			MC.cusID = selectCusId;
			$("#fmAddr").form('clear');
			$("#dlg-addr").dialog('open').dialog('setTitle',
					$.i18n.prop('employee.acc.add'));
		} else {
			$.messager.alert("提示", "请先选择客户，再添加新的邮寄信息", "warning");
		}
	},
	saveFmCus : function() {
		MC.save("fmCus", "customerInfo?goto=save", function(data) {
			$('#dlg-cus').dialog('close');
			$("#c3").combobox('reload', 'customerInfo?goto=combobox');
			$.post("customerInfo?goto=findAddId", function(d) {
				console.log(d);
				if (d != -1) {
					$("#c3").combobox('select', d);
				}
			});
		});
	},
	saveFmAddr : function() {
		MC.save("fmAddr", "deliveryAddress?goto=save&customer.id=" + MC.cusID,
				function(data) {
					$("#dlg-addr").dialog('close');
					$("#c4").combobox({
						disabled : false
					}).combobox('reload');
					$.post("deliveryAddress?goto=findAddId", function(d) {
						console.log(d);
						if (d != -1) {
							$("#c4").combobox('select', d);
						}
					});
				});
	},
	getSelectSurt : function() {
		var row = $('#custt').datagrid('getSelected');
		var sid = row.id;
		$("#c3").combobox('select', sid);
		$('#dlg-customer').dialog('close');
	}
}

var delMainCommande = function() {

}

var deleSingleSide = function(rowId) {
	$.post('slaveCommande?goto=delCheck&id=' + rowId, function(data) {
		if (data == 1) {
			$.post('slaveCommande?goto=getMainId&id=' + rowId, function(data) {
				var mid = data;
				$.ajax({
					async : false,
					cache : false,
					type : 'post',
					url : "mainCommande?goto=initSt",
					data : {
						'id' : mid
					},
					success : function(rs) {
						$.messager.alert($.i18n.prop('crud.dialog.tip.title'),
								rs.msg, 'warning');
					}
				});
			});
			$.post('slaveCommande?goto=del&id=' + rowId, function(data) {
				var msg = null;
				$('#tt').datagrid('reload');
				msg = "<span class='tip_suc'>" + data.msg + "</span>";
				$.messager.show({
					title : '操作结果',
					msg : msg
				});
			});
		} else {
			$.messager.show({
				title : '操作结果',
				msg : "请先确定该副单的加工单已经删除，再做副单删除操作！"
			});
		}
	});
}

var disableCC = function() {
	$.ajax({
		async : false,
		cache : false,
		type : 'post',
		url : "mainCommande?goto=scode",
		success : function(rs) {
			$("#showCodeId").val(rs);
		}
	});
	$("#c4").combobox({
		disabled : true
	});
	var data = $('#c2').combobox('getData');
	$("#c2").combobox("select", data[0].id);
}

var typeSelect = function(r) {
	var row = AAW.selectedRow();
	if (r == 1) {
		$("#sell1").show();
		$("#sell2").hide();
		$("#franLevel").hide();
		$("#c5").combobox('reload', 'employee?goto=oubobox').combobox({
			onLoadSuccess : function() {
				if (row != null && row.seller != null && row.fid == null) {
					$("#c5").combobox('select', row.seller.id);
				} else {
					var data2 = $('#c5').combobox('getData');
					$("#c5").combobox("select", data2[0].id);
				}
			}
		});
	} else {
		$("#sell1").hide();
		$("#sell2").show();
		$("#franLevel").show();
		$("#c5").combobox('reload', 'franchises?goto=combobox').combobox({
			onLoadSuccess : function() {
				var data2 = $('#c5').combobox('getData');
				$("#c5").combobox("select", data2[0].id);
			}
		});
	}
}

var levelSelect = function(r) {
	$("#c5").combobox('reload', 'franchises?goto=combobox&level=' + r)
			.combobox({
				onLoadSuccess : function() {
					var data2 = $('#c5').combobox('getData');
					$("#c5").combobox("select", data2[0].id);
				}
			});
}

var unSel = function(r, old) {
	if (!r) {
		$('#addAddrBtn').linkbutton({
			'disabled' : true
		});
		$("#c4").combobox({
			disabled : true
		});
	}
}

var addrVals = function(r) {
	$("#addAddrBtn").linkbutton({
		'disabled' : false
	});
	if (r == "undefined") {
		$("#c4").combobox({
			disabled : false
		}).combobox('reload', 'deliveryAddress?goto=combobox&id=' + r.id);
	} else {
		var v = $("#c3").combobox('getValue');
		$("#c4").combobox({
			disabled : false
		}).combobox('reload', 'deliveryAddress?goto=combobox&id=' + v);
	}
}
var ccLoad = function() {
	$('#csex').combobox({
		data : sexCombobox
	});
	$("#ccountry").combobox('reload', 'country?goto=combobox');
	$("#c1").combobox('reload', 'commandePriority?goto=combobox');
	$("#c2").combobox('reload', 'commandeType?goto=combobox2');
	$("#c3").combobox('reload', 'customerInfo?goto=combobox');

}
var backfill = function() {
	var r = AAW.selectedRow();
	$("#c2").combobox({
		disabled : true
	});
	$("#c1").combobox('select', r.priority.id);
	$("#c2").combobox('select', r.type.id);
	$("#c3").combobox('select', r.customer.id);
	$("#c4").combobox('select', r.address.id);
}
var init = function() {
	$("#tt")
			.datagrid(
					{
						view : detailview,
						detailFormatter : function(index, row) {
							return '<div style="padding:2px"><table class="ddv"></table></div>';
						},
						onClickRow : function(idx, r) {
							$.post("mainCommande?goto=hasSalve", {
								id : r.id
							}, function(r) {
								if (r > 0) {
									$("#cfgSd").linkbutton({
										'disabled' : false
									});
								} else {
									$("#cfgSd").linkbutton({
										'disabled' : true
									});
								}
							});
						},
						onExpandRow : function(index, row) {
							var ddv = $(this).datagrid('getRowDetail', index)
									.find('table.ddv');
							ddv.datagrid({
								url : 'mainCommande?goto=cfgList&id=' + row.id,
								pagination : false,
								fit : false,
								height : 'auto',
								columns : cfgArr,
								onDblClickRow : slaveDetail,
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
}
var saveChange = function() {
	$('#fm-slave-modify').form('submit', {
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(r) {
			AAW.saveTip(r, "slave-modify", "tt");
		}
	});
}
// 属性值微调
var minitrim = function(index, row) {
	$("#eL").empty();
	$("#eR").empty();
	var _aid;
	if (row.elementLeft) {
		_aid = row.elementLeft.attribute.id;
	} else if (row.elementRight) {
		_aid = row.elementRight.attribute.id;
	}
	$
			.ajax({
				async : false,
				cache : false,
				type : 'post',
				url : "slaveCommandeConfig?goto=modelElems",
				data : {
					id : row.slaveCommande.model.id,
					aid : _aid,
					cid : row.component.id
				},
				success : function(rs) {
					if (rs.left.length > 0) {
						$("#eL")
								.append(
										'<label>'
												+ $.i18n
														.prop('slave.cfg.eLeft')
												+ ':</label>')
								.append(
										"<input id='eLc' name=\"elementLeft.id\" class=\"easyui-combobox\" data-options='required:true,width:200,data:"
												+ JSON.stringify(rs.left)
												+ "'/>");
						$.parser.parse('#eL');

					}
					if (rs.right.length > 0) {
						$("#eR")
								.append(
										'<label>'
												+ $.i18n
														.prop('slave.cfg.eRight')
												+ ':</label>')
								.append(
										"<input id='eRc' name=\"elementRight.id\" class=\"easyui-combobox\" data-options='required:true,width:200,data:"
												+ JSON.stringify(rs.right)
												+ "'/>");
						$.parser.parse('#eR');

					}
				}
			});
	$('#fm-eleValChange').form('clear').form('load', row);
	if (row.elementLeft) {
		$("#eLc").combobox('select', row.elementLeft.id);
	}
	if (row.elementRight) {
		$("#eRc").combobox('select', row.elementRight.id);
	}
	$('#eleValChange').dialog('center').dialog('open').dialog('setTitle',
			$.i18n.prop('slave.cfg.eleReChoice'));

}
var slaveDetail = function(index, row) {
	$('#slaveChoiceTip').dialog({
		title : $.i18n.prop('slave.opr.title'),
		// content : $.i18n.prop('slave.opr.content'),
		cache : false,
		buttons : [ {
			text : $.i18n.prop('slave.opr.detail'),
			handler : function() {
				slaveD(row);
			}
		}, {
			text : $.i18n.prop('slave.opr.modify'),
			handler : function() {
				slaveModify(row);
			}
		} ]
	}).dialog('center').dialog('open');
}
var slaveD = function(row) {
	$('#slaveChoiceTip').dialog("close");
	$("#dgSd").datagrid({
		url : "slaveCommandeConfig?goto=slaveDetail&id=" + row.id,
		pagination : false,
		fit : false,
		height : 'auto',
		columns : slaveDArr,
		onDblClickRow : minitrim,
		onLoadSuccess : function() {
			// $(this).datagrid("autoMergeCells",
			// ['slaveCommande', 'component', 'attrName']);
		}
	});
	$("#dgSd2").datagrid({
		url : "slaveCommandeConfig?goto=slaveDetail2&id=" + row.id,
		pagination : false,
		fit : false,
		height : 'auto',
		columns : slaveDArr2,
		onDblClickRow : minitrim,
		onLoadSuccess : function() {
			// $(this).datagrid("autoMergeCells",
			// ['slaveCommande', 'component', 'attrName']);
		}
	});

	$('#slave-detail').dialog('center').dialog('open').dialog('setTitle',
			$.i18n.prop('slave.cfg.slaveDetail'));
}
var slaveModify = function(row) {
	$('#slaveChoiceTip').dialog("close");
	$('#slave-modify').dialog('center').dialog('open').dialog('setTitle',
			$.i18n.prop('slave.frm.modify'));
	$('#fm-slave-modify').form('clear').form('load', row);

}
var BtnCfg = function() {
	var row = AAW.selectedRow();
	if (row) {
		if (row.seller != null) {
			cfgProfile(row);
		} else {
			$.messager.confirm('Confirm', "该订单为加盟商订单，确认配置副单？", function(rd) {
				if (rd) {
					cfgProfile(row);
				}
			})
		}
	} else {
		$.messager.alert($.i18n.prop('mCommande.cfg.tip.title'), $.i18n
				.prop('mCommande.cfg.tip.content'), 'warning');
	}
}

var modelSel = function(r) {
	$("#dg").datagrid({
		url : "productModelConfig?goto=combobox&id=" + r.id,
		onLoadSuccess : function() {
			$('.eleSel').combobox({
				valueField : 'id',
				textField : 'text',
				groupField : 'group'
			});
			$(this).datagrid("autoMergeCells", [ 'componentName' ]);
		}
	});
	$("#dg2").datagrid({
		url : "productModelConfig?goto=combobox2&id=" + r.id,
		onLoadSuccess : function() {
			$('.eleSel').combobox({
				valueField : 'id',
				textField : 'text',
				groupField : 'group'
			});
			$(this).datagrid("autoMergeCells", [ 'componentName' ]);
		}
	});

}
var cfgProfile = function(row) {
	$("#dg").datagrid({
		pagination : false,
		fit : false,
		height : 'auto',
		columns : detailArr
	}).datagrid("loadData", {
		total : 0,
		rows : []
	});
	$("#dg2").datagrid({
		pagination : false,
		fit : false,
		height : 'auto',
		columns : detailArr2
	}).datagrid("loadData", {
		total : 0,
		rows : []
	});
	$("#c6").combobox('reload', 'productModel?goto=combobox');
	$('#fm-cfg').form('clear');
	$.post("paramtres?clickControlExternalCode", function(data) {
		if (data == 1) {
			$("#slCodeID2").textbox('setValue', row.externalCode);
		}
	});
	$('#dlg-cfg').dialog('center').dialog('open').dialog('setTitle',
			$.i18n.prop('mCommande.title.cfg'));
	cfgURL = "mainCommande?goto=saveCfg&id=" + row.id;
}
var cfgURL;
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
var saveEleValChange = function() {
	$('#fm-eleValChange').form('submit', {
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(r) {
			AAW.saveTip(r, "eleValChange", "dgSd");
		}
	});
}
function statusOpr() {
	var row = AAW.selectedRow();
	var status = row.orderStatus;
	if (status != 1) {
		if (row) {
			if (row.seller != null) {
				changeStatusDig(row);
			} else {
				$.messager.confirm('Confirm', "该订单为加盟商订单，确认变更状态？",
						function(rd) {
							if (rd) {
								changeStatusDig(row);
							}
						})
			}
		} else {
			$.messager.alert($.i18n.prop('mCommande.cfg.tip.title'), $.i18n
					.prop('mCommande.cfg.tip.content.sopr'), 'warning');
		}
	} else {
		$.messager.alert($.i18n.prop('mCommande.cfg.tip.title'), $.i18n
				.prop('mCommande.cfg.tip.content.sopr2'), 'warning');
	}
}
function changeStatusDig(r) {
	$("#status-tg").treegrid({
		columns : tmpArr,
		url : 'mainCommande?goto=stList&id=' + r.id,
		idField : 'id',
		treeField : 'name',
		fit : false,
		fitColumns : true,
		height : 'auto',
		onDblClickRow : beginEdit
	});
	$('#status-dlg').dialog('center').dialog({
		buttons : [ {
			iconCls : 'icon-save',
			text : $.i18n.prop('mcst.btn.save'),
			handler : save
		}, {
			text : $.i18n.prop('crud.btn.cancel'),
			iconCls : 'icon-cancel',
			handler : function() {
				save(false);
				$('#status-dlg').dialog('close');
			}
		} ]
	}).dialog('setTitle', $.i18n.prop('mCommande.title.sopr')).dialog('open');
}

var editingId;
function beginEdit() {
	var row = $('#status-tg').treegrid('getSelected');
	if (row.status == 1) {
		$.messager.alert($.i18n.prop('crud.dialog.tip.title'), $.i18n
				.prop('mCommande.st.mtip'), 'warning');
	} else {
		if (editingId != undefined) {
			$('#status-tg').treegrid('select', editingId);
			return;
		}
		if (row) {
			editingId = row.id;
			$('#status-tg').treegrid('beginEdit', editingId);
		}
	}
}
function save() {
	if (editingId != undefined) {
		var t = $('#status-tg');
		var row = t.treegrid('getSelected');
		t.treegrid('endEdit', editingId);
		var row = t.treegrid('getSelected');
		if (!arguments[0]) {
		} else {
			$.ajax({
				async : false,
				cache : false,
				type : 'post',
				url : "mainCommande?goto=saveStChange",
				data : {
					'mid' : row.mid,
					'id' : row.id,
					'st' : row.status
				},
				success : function(rs) {
					if (rs.suc == 1) {
						$('#status-tg').treegrid('reload');
					}
				}
			});
		}
		editingId = undefined;
	}
}
var initSt = function() {
	var row = AAW.selectedRow();
	if (row) {
		if (row.seller != null) {
			$.messager.confirm($.i18n.prop('mCommande.st.init.title'), $.i18n
					.prop('mCommande.st.init.tip'), function(r1) {
				if (r1) {
					$.ajax({
						async : false,
						cache : false,
						type : 'post',
						url : "mainCommande?goto=initSt",
						data : {
							'id' : row.id
						},
						success : function(rs) {
							$.messager.alert($.i18n
									.prop('crud.dialog.tip.title'), rs.msg,
									'warning');
						}
					});
				}
			});
		} else {
			$.messager
					.confirm(
							'Confirm',
							"改订单为加盟商订单，确认重置状态？",
							function(rd) {
								if (rd) {
									$.messager
											.confirm(
													$.i18n
															.prop('mCommande.st.init.title'),
													$.i18n
															.prop('mCommande.st.init.tip'),
													function(r1) {
														if (r1) {
															$
																	.ajax({
																		async : false,
																		cache : false,
																		type : 'post',
																		url : "mainCommande?goto=initSt",
																		data : {
																			'id' : row.id
																		},
																		success : function(
																				rs) {
																			$.messager
																					.alert(
																							$.i18n
																									.prop('crud.dialog.tip.title'),
																							rs.msg,
																							'warning');
																		}
																	});
														}
													});
								}
							});
		}
	} else {
		$.messager.alert($.i18n.prop('mCommande.cfg.tip.title'), $.i18n
				.prop('mCommande.st.selectTip'), 'warning');
	}
}

function printDialog(slave, main, id) {
	var _url = "employee?act=badgeCode&code=" + main;
	$("#barcode").attr("src", _url);
	$("#sla").text(slave);
	$('#dlg-print').dialog('center').dialog({
		buttons : [ {
			iconCls : 'icon-save',
			text : '确认打印',
			handler : function() {
				$("#print_zone").print();
			}
		}, {
			text : $.i18n.prop('crud.btn.cancel'),
			iconCls : 'icon-cancel',
			handler : function() {
				$('#dlg-print').dialog('close');
			}
		} ]
	}).dialog('setTitle', '打印数据查看').dialog('open');
}

function printLable(slave, main, id) {
	var _url = "employee?act=badgeCode2&code=" + main;
	$("#barcode2").attr("src", _url);
	$.post("mainCommande?act=labelById&id=" + id, function(data) {
		if (data != null) {
			$("#lcodeId").html(data.c.mainCommande.externalCode);
			var dateStr = data.c.mainCommande.timeCreation;
			$("#ltime").html(dateStr.substring(0, 11));
			$("#lpro").html(data.c.model.name);
			$("#lcommit").html(data.c.comments);
			$.each(data.clist, function(index, row) {
				if (row.elementLeft != null
						&& row.elementLeft.attribute.name == "外壳颜色") {
					$("#scl").html(row.elementLeft.name);
				}
				if (row.elementRight != null
						&& row.elementRight.attribute.name == "外壳颜色") {
					$("#scr").html(row.elementRight.name);
				}
				if (row.elementLeft != null
						&& row.elementLeft.attribute.name == "耳道颜色") {
					$("#acl").html(row.elementLeft.name);
				}
				if (row.elementRight != null
						&& row.elementRight.attribute.name == "耳道颜色") {
					$("#acr").html(row.elementRight.name);
				}
				if (row.elementLeft != null
						&& row.elementLeft.attribute.name == "面板颜色") {
					$("#pcl").html(row.elementLeft.name);
				}
				if (row.elementRight != null
						&& row.elementRight.attribute.name == "面板颜色") {
					$("#pcr").html(row.elementRight.name);
				}
				if (row.elementLeft != null
						&& row.elementLeft.attribute.name == "面板嵌入") {
					$("#pql").html(row.elementLeft.name);
				}
				if (row.elementRight != null
						&& row.elementRight.attribute.name == "面板嵌入") {
					$("#pqr").html(row.elementRight.name);
				}
				if (row.elementLeft != null
						&& row.elementLeft.attribute.name == "嵌入位置") {
					$("#qpl").html(row.elementLeft.name);
				}
				if (row.elementRight != null
						&& row.elementRight.attribute.name == "嵌入位置") {
					$("#qpr").html(row.elementRight.name);
				}
				if (row.elementLeft != null
						&& row.elementLeft.attribute.name == "插座类型") {
					$("#ptl").html(row.elementLeft.name);
				}
				if (row.elementRight != null
						&& row.elementRight.attribute.name == "插座类型") {
					$("#ptr").html(row.elementRight.name);
				}
			});
			if (data.cflist != null) {
				$("#c2a").html('');
				$.each(data.cflist, function(index, row) {
					if (row.attribute.name == "INITIAL") {
						$("#c1l").html(row.elementLeft);
					}
					if (row.attribute.name == "INITIAL") {
						$("#c1r").html(row.elementRight);
					}
					if (row.attribute.name == "N.O.C") {
						$("#c2a").append(row.elementLeft);
					}
					if (row.attribute.name == "N.O.C") {
						$("#c2a").append(row.elementRight);
					}
				});
			}
		}
	});
	$('#dlg-print2').dialog('center').dialog({
		buttons : [ {
			iconCls : 'icon-save',
			text : '确认打印',
			handler : function() {
				$("#print_zone2").print();
			}
		}, {
			text : $.i18n.prop('crud.btn.cancel'),
			iconCls : 'icon-cancel',
			handler : function() {
				$('#dlg-print2').dialog('close');
			}
		} ]
	}).dialog('setTitle', '打印数据查看').dialog('open');
}

function formatterStatus(status) {
	switch (status) {
	case 1:
		return "信息不全";
	case 2:
		return "默认状态";
	case 3:
		return "状态变更";
	case 4:
		return "发往工厂";
	case 5:
		return "创建加工单";
	case 6:
		return "开始加工";
	case 7:
		return "终止加工";
	case 8:
		return "删除加工";
	case 9:
		return "重置";
	case 10:
		return "未开始";
	case 11:
		return "进行中";
	case 12:
		return "完成";
	default:
		return "";
	}
}