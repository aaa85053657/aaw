<%@page import="cn.molos.common.SessionConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript" src="static/layer/jquery.min.js"></script>
<script type="text/javascript" src="static/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="static/css/app.css">
<script type="text/javascript">
	function openExcepiton() {
		layer.open({
			type : 1,
			content : $('#test'),
			area : 'auto',
			title : '异常处理',
			btn : '确定',
			yes : function(index, layero) {
				layer.confirm('您是如何看待前端开发？', {
					btn : [ '是', '否' ]
				//按钮
				}, function() {
					layer.msg('的确很重要', {
						icon : 1
					});
				}, function() {
					layer.msg('奇葩么么哒', {
						shift : 6
					});
				});
			}
		});
	}
	function openOthers() {
		layer.open({
			type : 1,
			content : $('#test2'),
			area : 'auto',
			title : '其他订单'
		});
	}

	function openCell() {
		layer.open({
			type : 1,
			content : $('#test3'),
			area : 'auto',
			title : '呼叫'
		});
	}

	function chang(val) {
		$('.inCode').val('');
		$.post('worksheet?goto=findByCode', {
			id : val.value
		}, function(data) {
			if (data.type == 0) {
				var index = layer.open({
					type : 0,
					content : '无法识别代码,请先扫描订单条码或者工牌,并按后续提示操作',
					area : 'auto',
					title : '无效登录（3秒倒计时）'
				});
				setTimeout("closeLayer(" + index + ")", 3000);
			} else if (data.type == 1) {
				$('#wcode').html(
						data.object.slaveCommande.mainCommande.codeId + ","
								+ data.object.slaveCommande.codeId);
				$('#wid').val(data.object.id);
				var index = layer.open({
					type : 1,
					content : $('#test5'),
					area : 'auto',
					title : '中间态->继续扫描(5秒倒计时)'
				});
				setTimeout("closeLayer(" + index + ")", 10000);
			} else if (data.type == 2) {
				$('#ecode').html(data.object.name);
				$('#eid').val(data.object.id);
				var index = layer.open({
					type : 1,
					content : $('#test6'),
					area : 'auto',
					title : '中间态->继续扫描(5秒倒计时)'
				});
				setTimeout("closeLayer(" + index + ")", 10000);
			} else if (data.type == 3) {
				var index = layer.open({
					type : 0,
					content : '其他工作台正在操作此订单!',
					area : 'auto',
					title : '无效登录（3秒倒计时）'
				});
				setTimeout("closeLayer(" + index + ")", 3000);
			}
		})
	}

	function validEcode(value) {
		var id = $('#wid').val();
		var code = value.value;
		$.post('worksheet?goto=findByCode2', {
			id : id,
			code : code,
			type : 1
		}, function(data) {
			if (data.type == 1) {
				window.location.href = "worksheet?goto=appView&did="
						+ data.detail.id + "&eid=" + data.employee.id;
			} else if (data.type == 2) {
				var index = layer.confirm('操作人员不对，是否需要更换操作人员？', {
					btn : [ '替换,并返回订单', '不替换,并返回订单', '取消' ],
					btn3 : function(i, l) {
						closeLayer(index);
					}
				}, function() {
					var did = data.detail.id;
					var eid = data.employee.id;
					$.post('worksheet?goto=updateEMP', {
						did : did,
						eid : eid
					}, function(data) {
						window.location.href = "worksheet?goto=appView&did="
								+ did + "&eid=" + eid;
					})
				}, function() {
					window.location.href = "worksheet?goto=appView3&did=" + did;
				});
			} else {
				var index = layer.open({
					type : 0,
					content : '无法识别代码,请先扫描订单条码或者工牌,并按后续提示操作',
					area : 'auto',
					title : '无效登录（3秒倒计时）'
				});
				setTimeout("closeLayer(" + index + ")", 3000);
			}
		});
	}

	function validWcode(value) {
		var id = $('#eid').val();
		var code = value.value;
		$.post('worksheet?goto=findByCode2', {
			id : id,
			code : code,
			type : 2
		}, function(data) {
			if (data.type == 1) {
				window.location.href = "worksheet?goto=appView&did="
						+ data.detail.id + "&eid=" + data.employee.id;
			} else if (data.type == 2) {
				var index = layer.confirm('操作人员不对，是否需要更换操作人员？', {
					btn : [ '替换,并返回订单', '不替换,并返回订单', '取消' ],
					btn3 : function(i, l) {
						closeLayer(index);
					}
				//按钮
				}, function() {
					var did = data.detail.id;
					var eid = data.employee.id;
					$.post('worksheet?goto=updateEMP', {
						did : did,
						eid : eid
					}, function(data) {
						window.location.href = "worksheet?goto=appView&did="
								+ did + "&eid=" + eid;
					})
				}, function() {
					window.location.href = "worksheet?goto=appView3&did=" + did;
				});
			} else if (data.type == 3) {
				var index = layer.open({
					type : 0,
					content : '其他工作台正在操作此订单!',
					area : 'auto',
					title : '无效登录（3秒倒计时）'
				});
				setTimeout("closeLayer(" + index + ")", 3000);
			} else {
				var index = layer.open({
					type : 0,
					content : '无效登录',
					area : 'auto',
					title : '无效登录（3秒倒计时）'
				});
				setTimeout("closeLayer(" + index + ")", 3000);
			}
		});
	}

	function closeLayer(index) {
		layer.close(index);
	}
</script>
</head>
<body>
	<div class="top" style="float: left; width: 1000px;">
		<div style="float: left;">
			条形码扫入: <input type="text" onblur="chang(this)">
		</div>
		<div style="float: right;">
			<div style="float: left; padding-right: 20px;">当前时间:2015/07/25&nbsp;22:05</div>
			<div style="float: right;">${employee.name }</div>
		</div>
	</div>
	<div class="main"
		style="float: left; width: 1000px; padding-top: 30px;">
		<!--左大块  -->
		<div class="left" style="width: 550px; float: left;">
			<!--第一块  -->
			<div
				style="float: left; border: 2px solid black; background-color: #DDDDDD; width: 550px; height: 200px;">
				<div>订单信息待显示</div>
			</div>
			<!--第二块  -->
			<div
				style="float: left; border: 2px solid black; background-color: #DDDDDD; width: 550px; height: 200px; margin-top: 20px;">
				<div>订单内容待显区域</div>
			</div>
		</div>
		<!--右大块  -->
		<div class="right"
			style="width: 400px; float: left; margin-left: 50px;">
			<!--第一块  -->
			<div
				style="float: left; border: 2px solid black; background-color: #DDDDDD; width: 400px; height: 200px; overflow-y: scroll;">
				<div>工序内容处理区域</div>
			</div>
			<!--第二块  -->
			<div
				style="float: left; border: 2px solid black; background-color: #DDDDDD; width: 400px; height: 200px; margin-top: 20px;">
				<div>其他/异常处理区域</div>
			</div>
		</div>
	</div>

	<!--中间态1  -->
	<div id="test5">
		<input type="hidden" id="wid">
		<div>
			订单号:<span id="wcode"></span>
		</div>
		<div>
			员工工号:<input type="text" class="inCode" onblur="validEcode(this)">
		</div>
	</div>

	<!--中间态2  -->
	<div id="test6">
		<input type="hidden" id="eid">
		<div>
			订单号:<input type="text" class="inCode" onblur="validWcode(this)">
		</div>
		<div>
			员工工号:<span id="ecode"></span>
		</div>
	</div>

</body>
</html>