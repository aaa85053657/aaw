<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='customer.page.title' /></title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript" src="static/js/col/CustomerCol.js"></script>
<script type="text/javascript"
	src="static/js/validator/CustomerValidator.js"></script>
<script type="text/javascript" src="static/js/opr/Customer.js"></script>
</head>
<body>
	<table id="tt"></table>
	<div id="toolbar">
		<a href="javascript:ccLoad();void(0);" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" id="btnAdd"><spr:message
				code='crud.btn.add' /></a> |<a
			href="javascript:ccLoad();backfill();void(0);"
			class="easyui-linkbutton" id="btnUpdate"
			data-options="iconCls:'icon-edit',plain:true"><spr:message
				code='crud.btn.update' /></a>| <a href="javascript:void(0);"
			class="easyui-linkbutton" id="btnDel"
			data-options="iconCls:'icon-remove',plain:true"><spr:message
				code='crud.btn.del' /></a>
	</div>
	<div>
		<div id="dlg" class="easyui-dialog"
			style="width: 620px; height: 250px; padding: 10px 10px">
			<form id="fm" method="post" novalidate>
				<div class="leftDIv">
					<div>
						<label><spr:message code='customer.obj.firstName' />:</label> <input
							name="firstName" class="easyui-textbox"
							data-options="required:true" />
					</div>
					<div>
						<label><spr:message code='customer.obj.middleName' />:</label> <input
							name="middleName" class="easyui-textbox" />
					</div>
					<div>
						<label><spr:message code='customer.obj.lastName' />:</label> <input
							name="lastName" class="easyui-textbox"
							data-options="required:true" />
					</div>
					<div>
						<label><spr:message code='customer.obj.sex' />:</label> <input
							id="csex" name="sex" class="easyui-combobox"
							data-options="required:true" />
					</div>
					<div>
						<label><spr:message code='customer.obj.email' />:</label> <input
							name="email" class="easyui-textbox"
							data-options="required:true,validType:['email']" />
					</div>
				</div>
				<div class="leftDIv">
					<div>
						<label><spr:message code='customer.obj.fax' />:</label> <input
							name="fax" class="easyui-textbox" />
					</div>
					<div>
						<label><spr:message code='customer.obj.codeID' />:</label> <input
							name="codeId" class="easyui-textbox"
							data-options="required:true,validType:['codeID']" />
					</div>
					<div>
						<label><spr:message code='customer.obj.country' />:</label> <input
							name="country.id" class="easyui-combobox" id="ccountry"
							data-options="required:true" />
					</div>
					<div>
						<label><spr:message code='customer.obj.telephone' />:</label> <input
							name="telephone" class="easyui-textbox" />
					</div>
					<div>
						<label><spr:message code='customer.obj.mobile' />:</label> <input
							name="mobile" class="easyui-textbox" />
					</div>
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