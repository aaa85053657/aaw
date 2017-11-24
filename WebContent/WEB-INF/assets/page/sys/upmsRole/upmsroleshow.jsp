<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='employee.page.title' /></title>
<jsp:include page="../../base/head2.jsp" />
</head>
<body>
	<div class="easyui-panel" data-options="title:'权限',tools:'#mytools'"
		style="height: 100%;">
		<ul id="treeg2" class="easyui-tree"
			data-options="url:'upmsRole?goto=assetsList',checkbox:true,animate : true,idField : 'id',textField : 'moduleName',lines : true"></ul>
	</div>
	<div id="mytools">
		<a href="#" class="icon-save" onclick="javascript:MyOp.bind()"></a>
	</div>

</body>
</html>