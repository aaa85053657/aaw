<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='pline.page.title' /></title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript" src="static/js/col/LogExceptionCol.js"></script>
<script type="text/javascript" src="static/js/opr/LogException.js"></script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<table id="tt"></table>
		<div id="toolbar"></div>
	</div>
</body>
</html>