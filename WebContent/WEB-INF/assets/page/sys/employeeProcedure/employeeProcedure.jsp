<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='employee.page.cfgSkill.title' /></title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript"
	src="static/js/col/EmployeeProcedureCol.js"></script>
<script type="text/javascript"
	src="static/js/validator/EmployeeValidator.js"></script>
<script type="text/javascript" src="static/js/opr/EmployeeProcedure.js"></script>
<style type="text/css">
.none {
	visibility: hidden;
}
</style>
</head>
<body>
	<table id="tt"></table>
	<div id="toolbar">
		<a href="javascript:ccLoad();void(0);" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true"><spr:message
				code='employee.skill.btn.bind' /></a>
	</div>
	<div>
		<div id="dlg" class="easyui-dialog"
			style="width: 600px; height: 200px; padding: 10px 20px">
			<form id="fm" method="post" novalidate>
				<input type="hidden" id="employeeId" name="employee"> <label><spr:message
						code='employee.skills.title' />:</label>
				<div id="pId"></div>
			</form>
		</div>
		<div id="dlg-buttons">
			<a href="javascript:save();void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'"><spr:message
					code='crud.btn.sure' /></a> <a href="javascript:void(0);"
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				onclick="javascript:$('#dlg').dialog('close');"><spr:message
					code='crud.btn.cancel' /></a>
		</div>
	</div>

</body>
</html>