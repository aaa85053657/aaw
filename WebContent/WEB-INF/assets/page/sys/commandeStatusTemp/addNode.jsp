<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<div>
	<div id="dlg-node" class="easyui-dialog"
		style="width: 430px; height: 300px; padding: 10px 20px"
		data-options="buttons : '#buttons-node'">
		<form id="fm-node" method="post" novalidate>
			<div>
				<label><spr:message code='cst.obj.name' />: </label> <input
					name="name" class="easyui-textbox"
					data-options="required:true,validType:['name']" />
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
				<input type="hidden" id="validator_id" />
			</div>
			<div>
				<input name="previousId" type="hidden" />
			</div>
			<div>
				<input name="parentId" type="hidden" />
			</div>
			<div>
				<input type="hidden" id="groupId2" name="groupId">
			</div>
		</form>
	</div>
	<div id="buttons-node">
		<a href="javascript:CST.saveNode();void(0);" class="easyui-linkbutton"
			data-options="iconCls:'icon-ok'"><spr:message
				code='crud.btn.sure' /></a> <a href="javascript:void(0);"
			class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
			onclick="javascript:$('#dlg-node').dialog('close');"><spr:message
				code='crud.btn.cancel' /></a>
	</div>
</div>