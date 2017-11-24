<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='cst.page.title' /></title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript"
	src="static/js/common/datagrid-detailview.js"></script>
<script type="text/javascript"
	src="static/js/col/CommandeStatusTempCol.js"></script>
<script type="text/javascript"
	src="static/js/validator/CommandeStatusTempValidator.js"></script>
</head>
<body class="easyui-layout">
	<div data-options='region:"center",fit:true,border:false'>
		<table id="tt"></table>
		<div id="toolbar">
			<table>
				<tr>
					<td><a href="javascript:CST.addF();void(0);"
						class="easyui-linkbutton"
						data-options="iconCls:'icon-add',plain:true" id="addStart"><spr:message
								code='cst.btn.addStart' /></a></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a href="javascript:CST.createSpec();void(0);"
						class="easyui-linkbutton" id="addSpecical"
						data-options="iconCls:'icon-edit',plain:true"><spr:message
								code='cst.btn.addSpecial' /></a></td>
					<td><a href="javascript:goBack();void(0)"
						class="easyui-linkbutton"
						data-options="iconCls:'icon-undo',plain:true" id="btn_goBack">返回</a></td>
				</tr>
			</table>
		</div>
		<div>
			<div id="dlg" class="easyui-dialog"
				style="width: 430px; height: 300px; padding: 10px 20px"
				data-options="buttons : '#buttons'">
				<form id="fm" method="post" novalidate>
					<div>
						<label><spr:message code='cst.obj.name' />: </label> <input
							name="name" class="easyui-textbox"
							data-options="required:true,validType:['name']" />
						<!-- data-options="required:true,validType:['name']" -->
					</div>
					<div>
						<label><spr:message code='cst.obj.isSendMail' />:</label> <input
							name="isSendMail" class="easyui-combobox cc"
							data-options="required:true" />
					</div>
					<div>
						<label><spr:message code='cst.obj.comments' />:</label>
						<textarea rows="5" cols="30" name="comments"></textarea>
					</div>
					<div>
						<input type="hidden" id="validator_id" /><input type="hidden"
							id="groupId" name="groupId">
					</div>
				</form>
			</div>
			<div id="buttons">
				<a href="javascript:CST.save();void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-ok'"><spr:message
						code='crud.btn.sure' /></a> <a href="javascript:void(0);"
					class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
					onclick="javascript:$('#dlg').dialog('close');"><spr:message
						code='crud.btn.cancel' /></a>
			</div>
		</div>
		<jsp:include page="addNode.jsp" />
	</div>
	<div>
		<input type="hidden" id="hasStart" value="${start }"> <input
			type="hidden" id="hasEnd" value="${end }"> <input
			type="hidden" id="groupIdTemp" value="${groupId }">
	</div>

	<div id="dbcChoice"></div>
</body>
<script type="text/javascript" src="static/js/opr/CommandeStatusTemp.js"></script>
</html>
