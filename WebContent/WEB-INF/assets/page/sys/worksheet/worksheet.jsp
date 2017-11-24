<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='worksheet.page.title' /></title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript" src="static/js/col/WorksheetCol.js"></script>
<script type="text/javascript"
	src="static/js/validator/WorksheetValidator.js"></script>
<script type="text/javascript" src="static/js/opr/Worksheet.js"></script>
</head>
<body>
	<table id="tt"></table>
	<div id="toolbar">
		<table>
			<tr>
				<td><a href="javascript:ccLoad();void(0);"
					class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true" id="btnAdd"><spr:message
							code='crud.btn.add' /></a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a href="javascript:backfill();void(0);"
					class="easyui-linkbutton" id="btnUpdate"
					data-options="iconCls:'icon-edit',plain:true"><spr:message
							code='crud.btn.update' /></a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton"
					id="btnDel" data-options="iconCls:'icon-remove',plain:true"><spr:message
							code='crud.btn.del' /></a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a href="javascript:endWorking();void(0);"
					class="easyui-linkbutton"
					data-options="iconCls:'icon-remove',plain:true"><spr:message
							code='worksheet.btn.stop' /></a></td>
			</tr>
		</table>
	</div>
	<div>
		<div id="dlg" class="easyui-dialog"
			style="width: 380px; height: 320px; padding: 10px 20px">
			<form id="fm" method="post" novalidate>
				<div>
					<label><spr:message code='worksheet.obj.slaveCommande' />:</label>
					<input class="easyui-combobox" style="width: 30%;" id="mcc"
						data-options="required:true" />-<input name="slaveCommande.id"
						class="easyui-combobox"
						data-options="required:true,validType:['slaveCommande']"
						style="width: 30%;" id="scc" />
				</div>
				<div>
					<label><spr:message code='worksheet.obj.serialNumber' />:</label>
					<input name="serialNumber" class="easyui-textbox"
						data-options="validType:['serialNumber']" />
				</div>
				<div>
					<label> <spr:message code='worksheet.obj.timeFinish' />:
					</label> <input name="timeFinish" class="easyui-datebox" />
				</div>
				<div>
					<label> <spr:message code='worksheet.obj.timeDelivery' />:
					</label> <input name="timeDelivery" class="easyui-datebox" />
				</div>
				<div>
					<label> <spr:message code='worksheet.obj.timeReceive' />:
					</label> <input name="timeReceive" class="easyui-datebox" />
				</div>
				<div>
					<label> <spr:message code='worksheet.obj.timeAbort' />:
					</label> <input name="timeAbort" class="easyui-datebox" />
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
</body>
</html>