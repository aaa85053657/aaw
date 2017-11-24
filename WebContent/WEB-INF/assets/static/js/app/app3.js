function chang(val) {
	$('.inCode').val('');
	$
			.post(
					'worksheet?goto=findByCode',
					{
						id : val.value
					},
					function(data) {
						var x = 3;
						if (data.type == 0) {
							var index = layer
									.open({
										type : 0,
										content : '无法识别代码,请先扫描订单条码或者工牌,并按后续提示操作',
										area : 'auto',
										title : '无效登录（<span style="color:red;" id="no_lg2"></span>秒倒计时）'
									});
							dyncTimer(timer_json["INVAILD_LOGIN"], function() {
								closeLayer(index);
							}, function(sec) {
								$("#no_lg2").text(sec);
								$("#firstInput").val('');
								document.getElementById("firstInput").focus();
							});
						} else if (data.type == 1) {
							$('#wcode')
									.html(
											data.object.slaveCommande.mainCommande.codeId
													+ ","
													+ data.object.slaveCommande.codeId);
							$('#wid').val(data.object.id);
							var index = layer
									.open({
										type : 1,
										content : $('#test5'),
										area : 'auto',
										title : '中间态->继续扫描(<span style="color:red;" id="mid1"></span>秒倒计时)'
									});
							dyncTimer(timer_json["CONTINUE_SCAN"], function() {
								closeLayer(index);
							},
									function(sec) {
										$("#mid1").text(sec);
										$("#firstInput").val('');
										document.getElementById("secondInput1")
												.focus();
									});
						} else if (data.type == 2) {
							$('#ecode').html(data.object.name);
							$('#eid').val(data.object.id);
							var index = layer
									.open({
										type : 1,
										content : $('#test6'),
										area : 'auto',
										title : '中间态->继续扫描(<span style="color:red;" id="mid2"></span>秒倒计时)'
									});

							dyncTimer(timer_json["CONTINUE_SCAN"], function() {
								closeLayer(index);
							},
									function(sec) {
										$("#mid2").text(sec);
										$("#firstInput").val('');
										document.getElementById("secondInput2")
												.focus();
									});
						} else if (data.type == 3) {
							var index = layer
									.open({
										type : 0,
										content : '其他工作台正在操作此订单!',
										area : 'auto',
										title : '无效登录（<span style="color:red;" id="ngl3"></span>秒倒计时）'
									});
							dyncTimer(timer_json["INVAILD_LOGIN"], function() {
								closeLayer(index);
							}, function(sec) {
								$("#ngl3").text(sec);
								$("#firstInput").val('');
								document.getElementById("firstInput").focus();
							});
						}
					})
}

function validEcode(value) {
	var id = $('#wid').val();
	var code = value.value;
	if (code != '') {
		$
				.post(
						'worksheet?goto=findByCode2',
						{
							id : id,
							code : code,
							type : 1
						},
						function(data) {
							if (data.type == 1) {
								$("#eidCom").val('');
								$("#didCom").val('');
								var did = data.detail.id;
								var eid = data.employee.id;
								$("#eidCom").val(eid);
								$("#didCom").val(did);
								$("#ot").val(timer_json["LOCK_TIME"]);
								$("#workForm").attr('action',
										'worksheet?goto=appView');
								$("#workForm").submit();
							} else if (data.type == 2) {
								var index = layer
										.confirm(
												'操作人员不对，是否需要更换操作人员？',
												{
													btn : [ '替换,并返回订单',
															'不替换,并返回订单', '取消' ],
													btn3 : function(i, l) {
														closeLayer(index);
														$("#firstInput")
																.val('');
													}
												},
												function() {
													$("#eidCom").val('');
													$("#didCom").val('');
													var did = data.detail.id;
													var eid = data.employee.id;
													$("#eidCom").val(eid);
													$("#didCom").val(did);
													$("#ot").val('');
													$("#workForm")
															.attr('action',
																	'worksheet?goto=appView');
													$
															.post(
																	'worksheet?goto=updateEMP',
																	{
																		did : did,
																		eid : eid
																	},
																	function(
																			data) {
																		$(
																				"#workForm")
																				.submit();
																	})
												},
												function() {
													$("#eidCom").val('');
													$("#didCom").val('');
													var did = data.detail.id;
													$("#didCom").val(did);
													$("#ot").val('');
													$("#workForm")
															.attr('action',
																	'worksheet?goto=appView3');
													$("#workForm").submit();
												});
							} else {
								var index = layer
										.open({
											type : 0,
											content : '无法识别代码,请先扫描订单条码或者工牌,并按后续提示操作',
											area : 'auto',
											title : '无效登录（<span style="color:red;" id="ngl4"></span>秒倒计时）'
										});
								dyncTimer(timer_json["INVAILD_LOGIN"],
										function() {
											closeLayer(index);
										}, function(sec) {
											$("#ngl4").text(sec);
											$("#firstInput").val('');
										});
							}
						});
	}
}

function validWcode(value) {
	var id = $('#eid').val();
	var code = value.value;
	if (code != '') {
		$
				.post(
						'worksheet?goto=findByCode2',
						{
							id : id,
							code : code,
							type : 2
						},
						function(data) {
							if (data.type == 1) {
								$("#eidCom").val('');
								$("#didCom").val('');
								var did = data.detail.id;
								var eid = data.employee.id;
								$("#eidCom").val(eid);
								$("#didCom").val(did);
								$("#ot").val(timer_json["LOCK_TIME"]);
								$("#workForm").attr('action',
										'worksheet?goto=appView');
								$("#workForm").submit();
							} else if (data.type == 2) {
								var index = layer
										.confirm(
												'操作人员不对，是否需要更换操作人员？',
												{
													btn : [ '替换,并返回订单',
															'不替换,并返回订单', '取消' ],
													btn3 : function(i, l) {
														closeLayer(index);
														$("#firstInput")
																.val('');
													}
												// 按钮
												},
												function() {
													$("#eidCom").val('');
													$("#didCom").val('');
													var did = data.detail.id;
													var eid = data.employee.id;
													$("#eidCom").val(eid);
													$("#didCom").val(did);
													$("#ot").val('');
													$("#workForm")
															.attr('action',
																	'worksheet?goto=appView');
													$
															.post(
																	'worksheet?goto=updateEMP',
																	{
																		did : did,
																		eid : eid
																	},
																	function(
																			data) {
																		$(
																				"#workForm")
																				.submit();
																	})
												},
												function() {
													$("#eidCom").val('');
													$("#didCom").val('');
													var did = data.detail.id;
													$("#didCom").val(did);
													$("#ot").val('');
													$("#workForm")
															.attr('action',
																	'worksheet?goto=appView3');
													$("#workForm").submit();
												});
							} else if (data.type == 3) {
								var index = layer
										.open({
											type : 0,
											content : '其他工作台正在操作此订单!',
											area : 'auto',
											title : '无效登录（<span style="color:red;" id="ngl5"></span>秒倒计时）'
										});
								dyncTimer(timer_json["INVAILD_LOGIN"],
										function() {
											closeLayer(index);
										}, function(sec) {
											$("#ngl5").text(sec);
											$("#firstInput").val('');
											document.getElementById(
													"firstInput").focus();
										});
							} else {
								var index = layer
										.open({
											type : 0,
											content : '无效登录',
											area : 'auto',
											title : '无效登录（<span style="color:red;" id="ngl6"></span>秒倒计时）'
										});
								dyncTimer(timer_json["INVAILD_LOGIN"],
										function() {
											closeLayer(index);
										}, function(sec) {
											$("#ngl6").text(sec);
											$("#firstInput").val('');
											document.getElementById(
													"firstInput").focus();
										});
							}
						});
	}
}

function closeLayer(index) {
	layer.close(index);
	document.getElementById("firstInput").focus();
}

function submitForm() {
	var did = $("#didCom").val();
	var eid = $("#eidCom").val();
	if (eid != null && eid != "") {
		$.post('worksheet?goto=updateEMP', {
			did : did,
			eid : eid
		}, function(data) {
			$("#workForm").submit();
		})
	} else {
		$("#workForm").submit();
	}
}

var timer_json = {
	"INVAILD_LOGIN" : 3,
	"CONTINUE_SCAN" : 10,
	"SELECT_TIME" : 10,
	"PROCESS_DONE" : 3,
	"ORDER_BEGIN" : 5,
	"LOCK_TIME" : 120
};

function openOthers() {
	$
			.post(
					"worksheet?goto=findWorking",
					function(data) {
						var tableHTML = "<table class='gridtable'>";
						tableHTML += "<tr><th class='col1'>订单号</th><th  class='col2'>型号</th><th  class='col2'>工艺师</th><th>操作</th></tr>";
						if (data != null) {
							var clickId = $("#worksheetId").val();
							$
									.each(
											data,
											function(index, e) {
												var code = e.slaveCommande.mainCommande.codeId;
												var type = e.slaveCommande.model.name;
												var opr = e.opr.name;
												if (clickId != e.id) {
													tableHTML += "<tr><td  class='col1'>"
															+ code
															+ "</td><td class='col2'>"
															+ type
															+ "</td><td class='col2'>"
															+ opr
															+ "</td><td><a href='javascript:lookMain("
															+ e.id
															+ ")'>查看</a></td></tr>";
												} else {
													tableHTML += "<tr><td  class='col1'>"
															+ code
															+ "</td><td class='col2'>"
															+ type
															+ "</td><td class='col2'>"
															+ opr
															+ "</td><td></td></tr>";
												}
											});
						}
						tableHTML += "</table>";
						$(".other_order").html(tableHTML);
						layer.open({
							type : 1,
							content : $('#test2'),
							area : [ '600px', '300px' ],
							title : '<span>其他订单</span>'
						});
					});
}

$(function() {
	document.getElementById("firstInput").focus();

	$.get("static/app_timeout_cfg.json", function(rs) {
		if (rs) {
			timer_json = rs;
		}
	}, "json");
	var msg = $("#msg").val();
	if (msg != null && msg != "") {
		var index = layer.open({
			type : 0,
			content : 'msg',
			area : 'auto',
			title : '无效登录（<span style="color:red;" id="ngl_5"></span>秒倒计时）'
		});
		dyncTimer(timer_json["INVAILD_LOGIN"], function() {
			closeLayer(index);
		}, function(sec) {
			$("#ngl_5").text(sec);
		});
	}

})