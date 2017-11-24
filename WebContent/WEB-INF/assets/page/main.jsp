<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AAW管理系统</title>
<!-- <link rel="stylesheet" type="text/css" href="static/customize/main.css" /> -->
<link rel="stylesheet" type="text/css" href="static/css/full.css">
<link rel="stylesheet" type="text/css" href="static/css/full2.css">
<jsp:include page="base/head2.jsp" />
<script type="text/javascript" src="static/js/common/loadmenus.js"></script>
<script type="text/javascript" src="static/js/common/main.js"></script>
<script type="text/javascript" src="static/js/common/time.js"></script>
<script type="text/javascript"
	src="static/js/validator/EmployeeValidator.js"></script>
</head>
<body>
	<div class="easyui-layout" style="overflow-y: hidden;"
		data-options="fit:true">
		<noscript>
			<div
				style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
				<img src="" alt='抱歉，请开启脚本支持！' />
			</div>
		</noscript>
		<!-- 进度显示框 -->
		<div id="loading-mask"
			style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; background: #D2E0F2; z-index: 20000">
			<div id="pageloading"
				style="position: absolute; top: 50%; left: 50%; margin: -120px 0px 0px -120px; text-align: center; border: 2px solid #8DB2E3; width: 200px; height: 40px; font-size: 14px; padding: 10px; font-weight: bold; background: #fff; color: #15428B;">
				正在加载中,请稍候...</div>
		</div>
		<!-- 头定义 -->
		<div data-options='region:"north",split:true,border:false'
			style="overflow: hidden; height: 83px; background: url(static/customize/images/index-header-bg.gif) repeat-x top left #198BC9; repeat-x: center 50%; line-height: 20px; color: #fff; font-family: Verdana, 微软雅黑, 黑体;">
			<span class="head"> <c:if test="${LOGIN_USER_TYPE==1 }">
					<span style="float: left;"> <a
						style="text-decoration: underline;" href="${ctx}/aaw/app"
						target="_self">切换至工作台</a>
					</span>
				</c:if> <span style="float: left;">欢迎${LOGIN_USER.name }</span> <a
				href="javascript:showUpdate();" id="editpass"><img
					src="static/images/edt.gif" style="padding-top: 18px;" />修改密码</a> <a
				href="goOut" id="loginOut"><img
					src="static/images/index-logout.png" />安全退出</a>
			</span> <span
				style="font-size: 24px; color: #fff; display: block; width: 400px; margin-top: 15px;">
				<img src="static/images/logo.png" style="width: 165px;" />
			</span>
		</div>
		<div class="clear"></div>
		<div class="subnav">
			<div class="subnavLeft fleft"></div>
			<div class="fleft">
				<img src="static/customize/images/people.png"
					style="float: left; padding-top: 5px;" /> &nbsp;欢迎登陆AAW管理系统 <span
					class="rightSpan"> <img
					src="static/customize/images/clock.png"
					style="float: left; padding-top: 7px;" /><span id="divT"> </span>
				</span>
			</div>
			<div class="subnavRight fright"></div>
		</div>
		<div class="clear"></div>
		<div data-options='region:"south",split:true'
			style="height: 30px; background: #198BC9; overflow: hidden; border: none;">
			<div class="footer"
				style="height: 26px; background: #198BC9; text-align: right; color: #fff; border: none; padding-top: 5px;">
				Copyright © 2002-2011 XXXXX版权所有</div>
		</div>
		<div data-options='region:"west",split:true,title:"导航菜单"'
			style="width: 280px;" id="west">
			<div id="nav">
				<!--  导航内容 -->
			</div>
		</div>
		<div id="mainPanle" data-options='region:"center"'
			style="background: #eee; overflow-y: hidden">
			<div id="tabs" class="easyui-tabs"
				data-options='fit:true,border:false'
				style="background: url('static/images/homeBg.png') no-repeat center;">
			</div>
		</div>
	</div>
	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="refresh">刷新</div>
		<div class="menu-sep"></div>
		<div id="close">关闭</div>
		<div id="closeall">全部关闭</div>
		<div id="closeother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="closeright">当前页右侧全部关闭</div>
		<div id="closeleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="exit">退出</div>
	</div>
	<jsp:include page="sys/employee/updateACC.jsp"></jsp:include>
</body>
</html>