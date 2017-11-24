<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='worksheet.page.title' /></title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript">
	var REFRESH_TASK_TIME =<%=session.getAttribute("REFRESH_TASK_TIME")%>;
</script>
<script type="text/javascript" src="static/js/col/WorksheetCol.js"></script>
<script type="text/javascript"
	src="static/js/validator/WorksheetValidator.js"></script>
<script type="text/javascript" src="static/js/opr/WorksheetDefatil.js"></script>
</head>
<body>
	<table id="tt"></table>
	<div id="toolbar">
		<table>
			<tr>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a href="javascript:WorksheetHandling();void(0);"
					class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true"><spr:message
							code='worksheet.opr.handling' /></a></td>
				<td><label><spr:message code='worksheet.search.process' />：</label>
					<input class="easyui-combobox" id="ccP"
					data-options="valueField:'id',textField:'name',url:'metaProcedure?goto=combobox3',onChange:search4More">
				</td>
			</tr>
		</table>
	</div>
	<div>
		<div id="handling"></div>
	</div>
</body>
</html>