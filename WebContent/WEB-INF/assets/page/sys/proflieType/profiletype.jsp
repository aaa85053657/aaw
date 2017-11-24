<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='profileType.page.title' /></title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript" src="static/js/col/ProfileTypeCol.js"></script>
<script type="text/javascript"
	src="static/js/validator/ProfileTypeValidator.js"></script>
<script type="text/javascript" src="static/js/opr/ProfileType.js"></script>
</head>
<body>
	<table id="tt"></table>
	<div id="toolbar">
		<a href="javascript:void(0);" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" id="btnAdd"><spr:message
				code='crud.btn.add' /></a> |<a href="javascript:void(0);"
			class="easyui-linkbutton" id="btnUpdate"
			data-options="iconCls:'icon-edit',plain:true"><spr:message
				code='crud.btn.update' /></a>| <a href="javascript:void(0);"
			class="easyui-linkbutton" id="btnDel"
			data-options="iconCls:'icon-remove',plain:true"><spr:message
				code='crud.btn.del' /></a>
	</div>
	<div>
		<div id="dlg" class="easyui-dialog"
			style="width: 450px; height: 320px; padding: 10px 20px;">
			<form id="fm" method="post" novalidate>
				<div>
					<label><spr:message code='profileType.obj.codeID' />:</label> <input
						name="codeId" class="easyui-textbox"
						data-options="required:true,validType:['codeID']" />
				</div>
				<div>
					<label><spr:message code='profileType.obj.name' />: </label> <input
						name="name" class="easyui-textbox"
						data-options="required:true,validType:['name']" />
				</div>
				<div>
					<label><spr:message code='profileType.obj.comments' />:</label>
					<textarea rows="5" cols="30" name="comments"></textarea>
				</div>
				<input type="hidden" id="validator_id" />
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
</body>
</html>