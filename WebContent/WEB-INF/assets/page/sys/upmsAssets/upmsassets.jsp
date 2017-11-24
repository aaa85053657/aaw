<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='employee.page.title' /></title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript"
	src="static/js/validator/EmployeeValidator.js"></script>
<script type="text/javascript" src="static/js/opr/UpmsAssets.js"></script>
</head>
<body>
	<div>
		<ul id="treeg" class="easyui-tree"></ul>
	</div>
</body>
</html>