<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../base/head2.jsp" /><script
	type="text/javascript" src="static/js/opr/upms/molos.plugins.global.js"></script>
<script type="text/javascript" src="static/js/opr/upms/UpmsAccount.js"></script>
</head>
<body>
	<div id="tt"></div>
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: auto; padding: 10px 20px">
		<form id="fm">
			<div>
				<label>用户名:</label><span id="acctName"></span>
				<!-- 					data-options="required:true,validType:['length[1,18]','name'],editable:false"> -->
			</div>
			<div>
				<label>密码:</label><input name="pazzwd" class="easyui-textbox"
					data-options="required:true,validType:['length[8,255]']">
			</div>
			<div>
				<label>关联的角色:</label><input name="role.id" class="easyui-combobox"
					data-options="required:true,url:'UpmsRole?act=combobox'" id="role">
			</div>
			<div>
				<label>指派的用户为:</label><span id="empName"></span>
			</div>
			<div>
				<input name="name" type="hidden"> <input type="hidden"
					id="vid" />
			</div>
		</form>
	</div>
</body>
</html>