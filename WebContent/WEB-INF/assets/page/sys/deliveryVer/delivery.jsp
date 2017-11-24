<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='customer.page.title' /></title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript" src="static/js/col/DeliveryCol.js"></script>
<script type="text/javascript" src="static/js/opr/Delivery.js"></script>
<script type="text/javascript" src="static/js/common/ajaxfileupload.js"></script>
</head>
<body>
	<table id="tt"></table>
	<div>
		<div id="dlg" class="easyui-dialog"
			style="width: 620px; height: 250px; padding: 10px 10px">
			<form id="fm" method="post" enctype="multipart/form-data" novalidate>
				<div class="dataDiv">
					<input type="file" name="appFile" id="appFile" />
				</div>
			</form>
		</div>
		<div id="dlg-buttons">
			<a href="javascript:uploadSave();void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'"><spr:message
					code='crud.btn.sure' /></a> <a href="javascript:void(0);"
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				onclick="javascript:$('#dlg').dialog('close');"><spr:message
					code='crud.btn.cancel' /></a>
		</div>
	</div>
</body>
</html>