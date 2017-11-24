<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报表</title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript" src="report/static/config.js"></script>
<script type="text/javascript" src="static/js/col/ReportCol.js"></script>
<script type="text/javascript" src="static/js/opr/Report.js"></script>
<script type="text/javascript" src="static/my97/WdatePicker.js"></script>
<style type="text/css">
.col1 {
	width: 100px;
	line-height: 25px;
	text-align: center;
}

#reportTable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	width: 100%;
	border-collapse: collapse;
}

#reportTable td, #reportTable th {
	font-size: 1em;
	border: 1px solid #98bf21;
	padding: 3px 7px 2px 7px;
}

#reportTable th {
	font-size: 1.1em;
	text-align: left;
	padding-top: 5px;
	padding-bottom: 4px;
	background-color: #A7C942;
	color: #ffffff;
}

#reportTable tr.alt td {
	color: #000000;
	background-color: #EAF2D3;
}

.selectTable {
	border: solid #add9c0;
	border-width: 1px 0px 0px 1px;
}

.selectTD {
	border: solid #add9c0;
	border-width: 0px 1px 1px 0px;
	width: 70px;
	text-align: center;
	height: 30px;
}
</style>
</head>
<body>
	<table id="tt"></table>
	<div id="toolbar">
		<a href="javascript:addReport();void(0);" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true">新增</a> |<a
			href="javascript:updateReoprt();void(0);" class="easyui-linkbutton"
			id="btnUpdate" data-options="iconCls:'icon-edit',plain:true">修改</a>|
		<a href="javascript:delClick();void(0);" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true">删除</a>
	</div>
	<div>
		<div id="dlg" class="easyui-dialog"
			style="width: 620px; height: 250px; padding: 10px 10px"></div>
	</div>
	<div>
		<div id="dlg-detail" class="easyui-dialog"
			style="width: 900px; height: 550px; padding: 10px 10px"></div>
	</div>
	<div>
		<div id="reportEditorDlg" class="easyui-dialog"
			style="width: 1000px; height: 500px; padding: 10px 10px">
			<iframe id="reportEditorWindow"
				style="border: none; width: 100%; height: 100%;"></iframe>
		</div>
	</div>
</body>
</html>