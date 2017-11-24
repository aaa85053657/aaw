<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div style="margin: 10px;">
		<div <c:if test="${flag==1||flag==4 }">style="display: none"</c:if>
			id="divMain">
			<form method="post" id="fm1">
				操作员工： <input id="oprId2" class="easyui-combobox"
					style="width: 135px;" name="operatorId"
					data-options="url:'employee?goto=combobox',required:true" />
				<hr>
				<div class="_hidden">
					<input type="hidden" value="${pid }" id="worksheetIsWorking"
						name="WorksheetDetailId">
					<c:set var="ppid" value="${pid }" scope="session"></c:set>
				</div>
				<div style="height: auto;">
					<c:if test="${!empty detail}">
						<table>
							<c:forEach var="v" items="${detail}" varStatus="st">
								<tr>
									<td>Profile Name:${v.profile.name }</td>
									<td>优先级:${v.priority }</td>
									<td>备注：<input class="easyui-textbox" style="width: 135px;"
										name="proList[${st.index }].comments" value="${v.comments }" /><input
										type="hidden" name="proList[${st.index }].id" value="${v.id }"><input
										type="hidden" name="proList[${st.index }].name"
										value="${v.profile.name }"></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
			</form>
		</div>
		<div>
			<div <c:if test="${flag==9}">style="display: none"</c:if> id="div1">
				<a href="javascript:beginClick();void(0);" class="easyui-linkbutton"
					id="begin" data-options="iconCls:'icon-save'">开始</a>
			</div>
			<div <c:if test="${flag==1||flag==4}">style="display: none"</c:if>
				id="div2">
				<a href="javascript:saveWorking();void(0);"
					class="easyui-linkbutton" id="save"
					data-options="iconCls:'icon-cancel'">结束</a> <a
					href="javascript:exceptionWorking();void(0);" id="except"
					class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">异常</a>
			</div>
		</div>
		<jsp:include page="exception.jsp" />
		<jsp:include page="timeOut.jsp" /></div>
</body>
</html>