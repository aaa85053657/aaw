<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='attrVal.page.title' /></title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript" src="static/js/col/AttrValCol.js"></script>
<script type="text/javascript"
	src="static/js/validator/AttrValValidator.js"></script>
<script type="text/javascript" src="static/js/opr/AttrVal.js"></script>
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
				<td><a href="javascript:ccLoad();backfill();void(0);"
					class="easyui-linkbutton" id="btnUpdate"
					data-options="iconCls:'icon-edit',plain:true"><spr:message
							code='crud.btn.update' /></a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton"
					id="btnDel" data-options="iconCls:'icon-remove',plain:true"><spr:message
							code='crud.btn.del' /></a></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton"
					id="initPic" data-options="iconCls:'icon-add',plain:true"><spr:message
							code='crud.btn.init' /></a></td>
			</tr>
		</table>
	</div>
	<div>
		<div id="dlg" class="easyui-dialog"
			style="width: 720px; height: 400px; padding: 5px 5px">
			<form id="fm" method="post" novalidate enctype="multipart/form-data">
				<div class="leftDIv" style="width: 67%;">
					<div>
						<label><spr:message code='attrVal.obj.codeID' />:</label> <input
							name="codeId" class="easyui-textbox"
							data-options="required:true,validType:['codeID']" />
					</div>
					<div>
						<label> <spr:message code='attrVal.obj.name' />:
						</label> <input name="name" class="easyui-textbox"
							data-options="required:true,validType:['name']" />
					</div>
					<div>
						<label><spr:message code='attrVal.obj.samplepath' />:</label> <a
							id="uploadImg" href="javascript:;">[<spr:message
								code='attrVal.obj.uploadBtn' />]
						</a>
					</div>
					<div>
						<label><spr:message code='attrVal.obj.atrribute' />:</label> <input
							name="attribute.id" id="cc" class="easyui-combobox"
							data-options="required:true" />
					</div>
					<div class="comment" style="clear: both; height: 0; width: 100%;">
						<label style="float: left;"><spr:message
								code='attrVal.obj.comments' />:</label>
						<textarea rows="5" cols="30" name="comments"></textarea>
					</div>
				</div>
				<div class="leftDIv" style="width: 33%; height: 280px;">
					<div>
						<img id="pre" style="width: 100%;">
					</div>
					<input type="hidden" id="validator_id" />
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
	<div>
		<div id="dlg-upload" class="easyui-dialog"
			style="width: 420px; height: 110px; padding: 5px 5px"
			data-options="buttons : '#dlg-buttons-upload'">
			<form id="fm-upload" method="post" novalidate
				enctype="multipart/form-data">
				<div>
					<input class="easyui-filebox" name="sample"
						data-options="prompt:'Choose a file...'" style="width: 100%">
				</div>
			</form>
		</div>
		<div id="dlg-buttons-upload">
			<a href="javascript:upload();" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'" id="btnSave"><spr:message
					code='crud.btn.sure' /></a> <a href="javascript:void(0);"
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				onclick="javascript:$('#dlg-upload').dialog('close');"><spr:message
					code='crud.btn.cancel' /></a>
		</div>
	</div>
	<div>
		<div id="dlg-showPic" class="easyui-dialog"
			data-options="buttons : 'dlg-buttons-show'"
			style="width: 420px; height: 200px; padding: 5px 5px">
			<div>
				<img alt="" src="" id="picture" style="width: 200px; height: 150px">
			</div>
			<div id="dlg-buttons-show"></div>
		</div>
	</div>
	<div>
		<div id="dlg-dl" class="easyui-dialog"
			data-options="buttons : 'dlg-buttons-show'"
			style="width: 420px; height: 200px; padding: 5px 5px">
			<div id="dlInfo"
				style="text-align: center; size: 16px; line-height: 30px"></div>
			<div id="dlg-buttons-dl"></div>
		</div>
	</div>
	<script type="text/javascript" src="static/js/common/jquery.form.js"></script>
</body>
</html>