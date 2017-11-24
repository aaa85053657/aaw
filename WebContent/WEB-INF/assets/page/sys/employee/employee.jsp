<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='employee.page.title' /></title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript" src="static/js/col/EmployeeCol.js"></script>
<script type="text/javascript"
	src="static/js/validator/EmployeeValidator.js"></script>
<script type="text/javascript" src="static/js/opr/Employee.js"></script>
<style type="text/css">
.none {
	visibility: hidden;
}
</style>
</head>
<body>
	<table id="tt"></table>
	<div id="toolbar">
		<a href="javascript:Emp.ccLoad();void(0);" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" id="btnAdd"><spr:message
				code='crud.btn.add' /></a> |<a
			href="javascript:Emp.ccLoad();Emp.backfill();void(0);"
			class="easyui-linkbutton" id="btnUpdate"
			data-options="iconCls:'icon-edit',plain:true"><spr:message
				code='crud.btn.update' /></a>| <a href="javascript:void(0);"
			class="easyui-linkbutton" id="btnDel"
			data-options="iconCls:'icon-remove',plain:true"><spr:message
				code='crud.btn.del' /></a> | <a
			href="javascript:Emp.createAcc();void(0);" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true"><spr:message
				code='employee.btn.createAcc' /></a>
	</div>
	<div>
		<div id="dlg" class="easyui-dialog"
			style="width: 600px; height: 320px; padding: 10px 20px">
			<form id="fm" method="post" novalidate>
				<div class="leftDIv">
					<div>
						<label><spr:message code='employee.obj.name' />:</label> <input
							name="name" class="easyui-textbox" data-options="required:true" />
					</div>
					<div>
						<label><spr:message code='employee.obj.sex' />:</label> <input
							id="csex" name="sex" class="easyui-combobox"
							data-options="required:true" />
					</div>
					<div>
						<label><spr:message code='employee.obj.age' />:</label> <input
							name="age" class="easyui-numberbox" />
					</div>
					<div>
						<label><spr:message code='employee.obj.email' />:</label> <input
							name="email" class="easyui-textbox"
							data-options="required:true,validType:['email']" />
					</div>
					<div>
						<label><spr:message code='employee.obj.fax' />:</label> <input
							name="fax" class="easyui-textbox" />
					</div>
					<div>
						<label><spr:message code='employee.obj.badgeCode' />:</label> <img
							alt="" src="employee?act=badgeCode&code=1439690111293" id="bcde"><a
							href="javascript:void();" onclick="$('#bcde').print();">打印条码</a>
						<input name="badgeCode" type="hidden">
					</div>
				</div>
				<div class="leftDIv">
					<div>
						<label><spr:message code='employee.obj.codeID' />:</label> <input
							name="codeId" class="easyui-textbox"
							data-options="required:true,validType:['codeID']" />
					</div>
					<div>
						<label><spr:message code='employee.obj.country' />:</label> <input
							name="country.id" class="easyui-combobox" id="ccountry"
							data-options="required:true" />
					</div>
					<div>
						<label><spr:message code='employee.obj.isIncumbent' />:</label> <input
							name="isIncumbent" class="easyui-combobox" id="cincumbent"
							data-options="required:true" />
					</div>
					<div>
						<label><spr:message code='employee.obj.isStaff' />:</label> <input
							name="isStaff" class="easyui-combobox" id="cstaff"
							data-options="required:true" />
					</div>
					<div>
						<label><spr:message code='employee.obj.address' />:</label> <input
							name="address" class="easyui-textbox" />
					</div>
					<div>
						<label><spr:message code='employee.obj.companyAddress' />:</label>
						<input name="companyAddress" class="easyui-textbox" id="cAddr" />
					</div>
					<div>
						<label><spr:message code='employee.obj.company' />:</label> <input
							name="company" class="easyui-textbox" id="company" />
					</div>
				</div>
				<div>
					<input type="hidden" id="validator_id" /> <input type="hidden"
						name="timeCreation" /> <input type="hidden"
						name="timeModification" /> <input type="hidden" name="timeDelete" />
					<input type="hidden" name="account.id" />
				</div>
			</form>
		</div>
		<div id="dlg-buttons">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'" id="btnSave"><spr:message
					code='crud.btn.sure' /></a> <a href="javascript:void(0);"
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				onclick="javascript:$('#dlg').dialog('close');"><spr:message
					code='crud.btn.cancel' /></a>
		</div>
	</div>
	<jsp:include page="createACC.jsp"></jsp:include>
</body>
</html>