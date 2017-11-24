<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<div id="dlg_cAcc" class="easyui-dialog"
	style="width: 320px; height: auto; padding: 10px 20px"
	data-options="buttons:'#btn_cAcc'">
	<form id="fmAcc" method="post" novalidate>
		<div>
			<input name="id" type="hidden">
		</div>
		<div>
			<div>
				<span style="color: red; float: left;">*</span><label
					style="float: left;"><spr:message code='employee.acc.uname' />:</label>
				<input name="name" type="text" class="easyui-textbox"
					data-options="required:true,validType:['uniqueAccount']" />
			</div>
			<div>
				<span style="color: red; float: left;">*</span><label
					style="float: left;"><spr:message code='employee.acc.pwd' />:</label>
				<input name="pazzwd" type="password" class="easyui-textbox"
					data-options="required:true,validType:['length[8,255]']" />
			</div>
			<div>
				<span style="color: red; float: left;">*</span><label
					style="float: left;"><spr:message code='employee.acc.role' />:</label>
				<input name="role.id" id="role" class="easyui-combobox"
					data-options="url:'UpmsRole?act=combobox',required:true" />
			</div>
		</div>
	</form>
</div>
<div id="btn_cAcc">
	<a href="javascript:Emp.saveAcc();void(0);" class="easyui-linkbutton"
		data-options="iconCls:'icon-ok'" id="btnSave"><spr:message
			code='crud.btn.sure' /></a> <a href="javascript:void(0);"
		class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
		onclick="javascript:$('#dlg_cAcc').dialog('close');"><spr:message
			code='crud.btn.cancel' /></a>
</div>
