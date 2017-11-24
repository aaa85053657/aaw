<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='procedure.page.title' /></title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript"
	src="static/js/common/datagrid-detailview.js"></script>
<script type="text/javascript" src="static/js/col/MetaProcedureCol.js"></script>
<script type="text/javascript"
	src="static/js/validator/MetaProcedureValidator.js"></script>
<script type="text/javascript" src="static/js/opr/MetaProcedure.js"></script>
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
				code='crud.btn.del' /></a>| <a href="javascript:BtnCfg();"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true"><spr:message
				code='procedure.btn.cfg' /></a>
	</div>
	<div>
		<div id="dlg" class="easyui-dialog"
			style="width: 560px; height: 280px; padding: 10px 20px">
			<form id="fm" method="post" novalidate>
				<div class="fitem">
					<label><spr:message code='procedure.obj.codeID' />:</label> <input
						name="codeId" class="easyui-textbox"
						data-options="required:true,validType:['codeID']" />
				</div>
				<div class="fitem">
					<label> <spr:message code='procedure.obj.name' />:
					</label> <input name="name" class="easyui-textbox"
						data-options="required:true,validType:['name']" />
				</div>
				<div class="fitem">
					<label style="float: left;"><spr:message
							code='procedure.obj.comments' />:</label>
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
	<div>
		<div id="dlg-cfg" class="easyui-dialog"
			style="width: 560px; height: 280px; padding: 10px 20px"
			data-options="buttons : '#dlg-buttons-cfg'">
			<form id="fm-cfg" method="post" novalidate>
				<table id="cfgBody">
					<tr>
						<td><label> <spr:message
									code='procedure.cfg.priority' />:
						</label> <input name="priority[]" class="easyui-numberbox"
							data-options="required:true" /></td>
						<td><label> <spr:message code='procedure.cfg.profile' />:
						</label> <input name="profile[]" class="easyui-combobox pSel"
							data-options="required:true,onSelect:selCheck" id="cPrf" /></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="dlg-buttons-cfg">
		<a href="javascript:void(0);" class="easyui-linkbutton"
			data-options="iconCls:'icon-ok'" id="btnCfg"><spr:message
				code='crud.btn.sure' /></a> <a href="javascript:void(0);"
			class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
			onclick="javascript:$('#dlg-cfg').dialog('close');"><spr:message
				code='crud.btn.cancel' /></a><a href="javascript:BtnAddRow();"
			class="easyui-linkbutton" data-options="iconCls:'icon-add'"><spr:message
				code='procedure.cfg.addOneRow' /></a>
	</div>
</body>
</html>