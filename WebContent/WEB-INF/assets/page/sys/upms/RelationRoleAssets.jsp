<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../base/head2.jsp" />
</head>
<body>
	<div class="easyui-panel" data-options="title:'绑定资源信息',footer:'#ft'"
		style="height: 100%;">
		<ul id="assets" class="easyui-tree"
			data-options="url: 'UpmsAssets?act=list4bind&roleID=${roleID }', checkbox: true, animate: true, idField: 'id', lines: true"></ul>
	</div>
	<div id="ft" style="padding: 5px;">
		<div style="float: right;">
			<a href="javascript:;" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">收起</a> <a
				href="javascript:MyOp.bind();" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">保存</a>
		</div>
	</div>
	<div class="hidden">
		<input id="roleID" value="${roleID }" type="hidden">
	</div>
</body>
</html>