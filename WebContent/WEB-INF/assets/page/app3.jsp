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
<link rel="stylesheet" type="text/css" href="static/css/app.css">
<script type="text/javascript" src="static/layer/jquery.min.js"></script>
<script type="text/javascript" src="static/layer/layer.js"></script>
<script type="text/javascript" src="static/js/common/molos.timer.js"></script>
<script type="text/javascript" src="static/js/app/app3.js"></script>
<script type="text/javascript" src="static/js/app/showtime.js"></script>
</head>
<body>
	<div>
		<input type="hidden" id="msg" value="${msg }">
	</div>
	<div class="top" style="float: left; width: 1000px;">
		<div style="float: left;">
			条形码扫入: <input type="text" id="firstInput" onchange="chang(this)">
		</div>
		<div style="float: right;">
			<div style="float: left; padding-right: 20px;">
				当前时间:<span id="clockbox"></span>
			</div>
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
				<div>
					<button onclick="javascript:openOthers();void(0);">其他订单</button>
				</div>
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
			员工工号:<input type="text" class="inCode" id="secondInput1"
				onblur="validEcode(this)">
		</div>
	</div>

	<!--中间态2  -->
	<div id="test6">
		<input type="hidden" id="eid">
		<div>
			订单号:<input type="text" class="inCode" id="secondInput2"
				onblur="validWcode(this)">
		</div>
		<div>
			员工工号:<span id="ecode"></span>
		</div>
	</div>

	<!--选择操作时间  -->
	<div id="test7">
		<form id="workForm" method="post">
			<input type="hidden" id="eidCom" name="eid"> <input
				type="hidden" id=didCom name="did">
			<div>
				<input name="oprTime" id="ot" type="hidden">
			</div>
		</form>
	</div>

	<!--其他订单  -->
	<div id="test2">
		<div class="other_order"></div>
	</div>

</body>
</html>