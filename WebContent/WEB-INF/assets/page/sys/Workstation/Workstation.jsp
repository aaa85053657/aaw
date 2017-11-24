<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='Workstation.page.title' /></title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript" src="static/js/opr/Workstation.js"></script>
<script type="text/javascript" src="static/js/common/jquery.form.js"></script>
</head>
<body>
	<table id="tt"></table>
	<div id="toolbar">
		<table>
			<tr>
				<td><a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true" id="btnAdd"><spr:message
							code='crud.btn.add' /></a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton"
					id="btnUpdate" data-options="iconCls:'icon-edit',plain:true"><spr:message
							code='crud.btn.update' /></a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton"
					id="btnDel" data-options="iconCls:'icon-remove',plain:true"><spr:message
							code='crud.btn.del' /></a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a href="javascript:BtnCfg();void(0);"
					class="easyui-linkbutton"
					data-options="iconCls:'icon-remove',plain:true"><spr:message
							code='wt.btn.config.wp' /></a></td>
			</tr>
		</table>
	</div>
	<div>
		<div id="dlg" class="easyui-dialog"
			style="width: 430px; height: 300px; padding: 10px 20px">
			<form id="fm" method="post" novalidate>
				<div>
					<label><spr:message code='Workstation.obj.codeID' />:</label> <input
						name="codeId" class="easyui-textbox"
						data-options="required:true,validType:['codeID']" />
				</div>
				<div>
					<label><spr:message code='Workstation.obj.name' />: </label> <input
						name="name" class="easyui-textbox"
						data-options="required:true,validType:['name']" />
				</div>
				<div>
					<label><spr:message code='Workstation.obj.parameter' />: </label>
					<input name="parameter" class="easyui-textbox"
						data-options="required:true,validType:['parameter']" />
				</div>
				<div>
					<label><spr:message code='Workstation.obj.comments' />:</label>
					<textarea rows="5" cols="30" name="comments"></textarea>
				</div>
				<div>
					<input type="hidden" id="validator_id" /> <input type="hidden"
						name="timeCreation" /> <input type="hidden"
						name="timeModification" /> <input type="hidden" name="timeDelete" />
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
	<!-- 	<div id="win"></div> -->


	<div>
		<div id="dlg-cfg" class="easyui-dialog"
			style="width: 560px; height: 280px; padding: 10px 20px"
			data-options="buttons : '#dlg-buttons-cfg'">
			<form id="fm-cfg" method="post" novalidate>
				<table id="cfgBody">
					<tr>
						<td><label> <spr:message code='Workstation.cfg.line' />:
						</label> <input name="sequenceNum[]" class="easyui-combobox" atid="0" id="gd"
							data-options="required:true,onSelect:selLine,url:'productionLine?goto=combobox'" /></td>
						<td><label> <spr:message
									code='Workstation.cfg.procedure' />:
						</label> <input name="procedure[]" class="easyui-combobox pSel" id="ck_0"
							data-options="required:true,onSelect:selCheck" /></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="dlg-buttons-cfg">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'" id="btnCfg"><spr:message
					code='crud.btn.sure' /></a> <a href="javascript:void(0);"
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				onclick="javascript:$('#dlg-cfg').dialog('close');"><spr:message
					code='crud.btn.cancel' /></a><a href="javascript:BtnAddRow();"
				class="easyui-linkbutton" data-options="iconCls:'icon-add'"><spr:message
					code='pline.cfg.addOneRow' /></a>
		</div>
	</div>
</body>
</html>