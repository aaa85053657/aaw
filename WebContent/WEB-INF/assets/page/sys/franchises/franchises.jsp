<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='worksheet.page.title' /></title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript" src="static/js/col/FranchisesCol.js"></script>
<script type="text/javascript"
	src="static/js/validator/FranchisesValidator.js"></script>
<script type="text/javascript" src="static/js/opr/Franchises.js"></script>
<script type="text/javascript" src="static/js/common/ajaxfileupload.js"></script>
</head>
<body>
	<div id="c_main" class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true" title="高级搜索"
			style="width: 335px;">
			<jsp:include page="conditions.jsp"></jsp:include>
		</div>
		<div data-options="region:'center',iconCls:'icon-ok'">
			<table id="tt"></table>
			<div id="toolbar">
				<table>
					<tr>
						<td><a href="javascript:clickAddAssets();void(0);"
							class="easyui-linkbutton"
							data-options="iconCls:'icon-add',plain:true"><spr:message
									code='crud.btn.add' /></a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						<td><a href="javascript:clickUpdateAssets();void(0);"
							class="easyui-linkbutton"
							data-options="iconCls:'icon-edit',plain:true"><spr:message
									code='crud.btn.update' /></a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						<td><a href="javascript:deleteRow();void(0);"
							class="easyui-linkbutton"
							data-options="iconCls:'icon-remove',plain:true"><spr:message
									code='crud.btn.del' /></a></td>
						<td><a href="javascript:intoInfo();void(0);"
							class="easyui-linkbutton"
							data-options="iconCls:'icon-remove',plain:true"><spr:message
									code='into' /></a></td>
						<td><a href="javascript:exportInfo();void(0);"
							class="easyui-linkbutton"
							data-options="iconCls:'icon-remove',plain:true"><spr:message
									code='export' /></a></td>
						<td><a href="javascript:createTree();void(0);"
							class="easyui-linkbutton"
							data-options="iconCls:'icon-remove',plain:true"><spr:message
									code='franchises.tree' /></a></td>
						<%-- 		<td><div class="datagrid-btn-separator"></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton"
					id="btnDel" data-options="iconCls:'icon-remove',plain:true"><spr:message
							code='crud.btn.del' /></a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a href="javascript:endWorking();void(0);"
					class="easyui-linkbutton"
					data-options="iconCls:'icon-remove',plain:true"><spr:message
							code='worksheet.btn.stop' /></a></td> --%>
					</tr>
				</table>
			</div>
			<div>
				<div id="dlg" class="easyui-dialog"
					style="width: 380px; height: 420px; padding: 10px 20px">
					<form id="fm" method="post" novalidate>
						<div>
							<label><spr:message code='franchises.obj.mid' />:</label><input
								disabled="disabled" name="code" id="showCodeId"
								class="easyui-textbox">
						</div>
						<div>
							<label><spr:message code='franchises.obj.name' />:</label> <input
								name="name" class="easyui-textbox"
								data-options="required:true,validType:['uniquename']">
						</div>
						<div>
							<label> <spr:message code='franchises.obj.address' />:
							</label> <input name="address" class="easyui-textbox" />
						</div>
						<div>
							<label> <spr:message code='franchises.obj.contactName' />:
							</label> <input name="contactName" class="easyui-textbox" />
						</div>
						<div>
							<label> <spr:message code='franchises.obj.contactNumber' />:
							</label> <input name="contactNumber" class="easyui-textbox" />
						</div>
						<div>
							<label> <spr:message code='franchises.obj.phone' />:
							</label> <input name=contactPhone class="easyui-textbox" />
						</div>
						<div>
							<label> <spr:message code='franchises.obj.email' />:
							</label> <input name=contactEmail class="easyui-textbox"
								data-options="required:true,validType:['email','uniqueemail']" />
						</div>
						<div>
							<label> <spr:message code='franchises.obj.description' />:
							</label> <input name="description" class="easyui-textbox" />
						</div>
						<div>
							<label> <spr:message code='franchises.obj.type' />:
							</label> <input name="category" class="easyui-numberbox" />
						</div>
						<div>
							<input type="hidden" name="id" id="validator_id" /> <input
								type="hidden" name="createTime" /> <input type="hidden"
								name="modificationTime" /> <input type="hidden"
								name="deleteTime" /> <input type="hidden" name="isCreateAcc" />
							<input type="hidden" name="state" /><input type="hidden"
								name="franchiseLevel" /> <input type="hidden" name="parentId" />
						</div>
					</form>
				</div>
				<div id="dlg-buttons">
					<a href="javascript:btnSave();void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'"><spr:message
							code='crud.btn.sure' /></a> <a href="javascript:void(0);"
						class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
						onclick="javascript:$('#dlg').dialog('close');"><spr:message
							code='crud.btn.cancel' /></a>
				</div>
			</div>

			<div>
				<div id="dlg-cfg" class="easyui-dialog"
					style="width: 450px; height: 350px; padding: 10px 20px"
					data-options="buttons : '#dlg-buttons-cfg'">
					<form id="fm-cfg" method="post" novalidate>
						<div style="line-height: 30px;">
							<label><spr:message code='franchises.acc.name' />:</label><input
								name="account" class="easyui-textbox" id="facc"
								data-options="required:true,validType:['uniqueAccount']">
						</div>
						<div style="line-height: 30px;">
							<label><spr:message code='franchises.acc.pwd' />:</label> <input
								name="pazzword" class="easyui-textbox" id="fpwd"
								data-options="required:true">
						</div>
						<div style="line-height: 30px;">
							<label> <spr:message code='franchises.acc.status' />:
							</label> <input name="status" type="radio" value="1"><spr:message code='franchises.obj.state.nactivtion' /><input
								name="status" type="radio" value="2" checked="true" /><spr:message code='franchises.obj.state.activtion' />
						</div>
						<div style="line-height: 30px;">
							<label> <spr:message code='franchises.acc.assets' />:
							</label>
							<div id="assets"></div>
						</div>
						<input type="hidden" id="fid" name="fid"> <input
							type="hidden" name="id" id="aid">
					</form>
				</div>
				<div id="dlg-buttons-cfg">
					<a href="javascript:saveAcc();void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'"><spr:message
							code='crud.btn.sure' /></a> <a href="javascript:void(0);"
						class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
						onclick="javascript:$('#dlg-cfg').dialog('close');"><spr:message
							code='crud.btn.cancel' /></a>
				</div>
			</div>

			<div>
				<div id="dlg-into" class="easyui-dialog"
					style="width: 450px; height: 350px; padding: 10px 20px"
					data-options="buttons : '#dlg-buttons-into'">
					<!-- action="franchises?act=intoFranchisesInfo" -->
					<form method="post" enctype="multipart/form-data" id="intoInfo">
						<input type="file" name="appFile" id="appFile" />
					</form>
				</div>
				<div id="dlg-buttons-into">
					<a href="javascript:saveInfo();void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'"><spr:message
							code='crud.btn.sure' /></a> <a href="javascript:void(0);"
						class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
						onclick="javascript:$('#dlg-into').dialog('close');"><spr:message
							code='crud.btn.cancel' /></a>
				</div>
			</div>

			<div>
				<div id="dlg-tree" class="easyui-dialog"
					style="width: 450px; height: 350px; padding: 10px 20px"
					data-options="buttons : '#dlg-buttons-tree'">
					<div id='print_zone' style="padding-top: 5px;">
						<ul class="easyui-tree" id="franTree"></ul>
					</div>
				</div>
				<div id="dlg-buttons-tree">
					<a href="javascript:printTree();void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'"><spr:message
							code='crud.btn.sure' /></a> <a href="javascript:void(0);"
						class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
						onclick="javascript:$('#dlg-tree').dialog('close');"><spr:message
							code='crud.btn.cancel' /></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>