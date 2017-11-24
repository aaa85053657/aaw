<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript"
	src="static/js/opr/upms/molos.plugins.global.js"></script>
<script type="text/javascript" src="static/js/opr/upms/UpmsRole.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',collapsible:false,split:true"
		title="角色信息" style="width: 420px;">
		<div id="tt"></div>
	</div>
	<div data-options="region:'center'">
		<div class="easyui-panel" data-options="title:'绑定资源信息',footer:'#ft'"
			style="height: 100%;">
			<ul id="assets" class="easyui-tree"
				data-options="url:'UpmsAssets?act=listAll',checkbox:true,animate:true,idField:'id',lines:true,formatter:UpmsRole.permission.fm"></ul>
		</div>
		<div id="ft" style="padding: 5px;">
			<div style="float: right;">
				<a href="javascript:$('#assets').tree('expandAll');void(0);"
					class="easyui-linkbutton" data-options="iconCls:'icon-remove'">展开</a>
				<a href="javascript:$('#assets').tree('collapseAll');void(0);"
					class="easyui-linkbutton" data-options="iconCls:'icon-remove'">收起</a>
				<a href="javascript:UpmsRole.permission.bind();"
					class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
			</div>
		</div>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width: 320px; height: auto; padding: 10px 20px">
		<form id="fm">
			<div>
				<label>角色名称:</label><input name="name" class="easyui-textbox"
					data-options="required:true,validType:['length[1,45]','name']">
			</div>

			<div>
				<input type="hidden" id="vid" />
			</div>
		</form>
	</div>
</body>
</html>