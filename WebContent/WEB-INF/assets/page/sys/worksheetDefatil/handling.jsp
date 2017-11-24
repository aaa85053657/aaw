<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript" src="static/js/opr/Handling.js"></script>
</head>
<body>
	<div>
		<h1>副单:${sm.codeId }</h1>
		<h1>所属主单:${sm.mainCommande.codeId }</h1>
		<h1>备注:${sm.comments }</h1>
		<input type="hidden" id="sid" value="${sid }">
		<table class="easyui-datagrid" id="scdtt"
			style="width: 400px; height: 250px"></table>
		<div>
			<table id="cfgtt"></table>
		</div>
	</div>
	<div align="center">
		<!-- 加工处理项 -->
		<div class="easyui-accordion" style="width: 98%; height: auto;"
			data-options="multiple:true">
			<c:if test="${!empty metaProcedureList}">
				<c:forEach var="mpl" items="${metaProcedureList}">
					<c:choose>
						<c:when test="${mpl.flag==1 }">
							<div title="${mpl.metaProcedure.name }"
								data-options="href:'worksheetdefatil?goto=oprProfile&id=${mpl.id }&t=${mpl.flag }',selected:true">
							</div>
						</c:when>
						<c:otherwise>
							<div title="${mpl.metaProcedure.name }"
								data-options="href:'worksheetdefatil?goto=oprProfile&id=${mpl.id }&t=${mpl.flag }'">
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			var sid = $("#sid").val();
			$("#scdtt").datagrid({
				url : 'worksheet?goto=querySlave&id=' + sid,
				fitColumns : true,
				singleSelect : true,
				pagination : false,
				fit : false,
				columns : [ [ {
					field : 'componentName',
					title : '组件',
					width : 100
				}, {
					field : 'attrName',
					title : '属性',
					width : 100
				}, {
					field : 'elementLeft',
					title : '左内容',
					width : 100
				}, {
					field : 'elementRight',
					title : '右内容',
					width : 100
				} ] ],
				onLoadSuccess : function() {
					$(this).datagrid("autoMergeCells", [ 'componentName' ]);
				}
			});

		})
	</script>
</body>
</html>