<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='employee.page.title' /></title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript" src="static/js/col/UpmsRoleCol.js"></script>
<script type="text/javascript"
	src="static/js/validator/RoleValidator.js"></script>
<script type="text/javascript" src="static/js/opr/UpmsRole.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',collapsible:false,split:true"
		title="角色" style="width: 350px;">
		<jsp:include page="role.jsp"></jsp:include>
	</div>
	<div data-options="region:'center'," id="cc">
		<div>
			<div id="dlg" class="easyui-dialog"
				style="width: 300px; height: 150px; padding: 10px 20px">
				<form id="fm" method="post" novalidate>
					<div>
						<label>角色名：</label> <input name="name" class="easyui-textbox"
							data-options="required:true,validType:['rolename']" />
					</div>
					<div>
						<input type="hidden" name="id" id="validator_id" />
					</div>
				</form>
			</div>
			<div id="dlg-buttons">
				<a href="javascript:MyOp.save();void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-ok'"><spr:message
						code='crud.btn.sure' /></a> <a href="javascript:void(0);"
					class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
					onclick="javascript:$('#dlg').dialog('close');"><spr:message
						code='crud.btn.cancel' /></a>
			</div>
		</div>
	</div>
</body>
</html>