<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>只读</h1>
	<form action="">
		MetaProcedure status:
		<c:if test="${wd4mp.status==1}">进行中</c:if>
		<c:if test="${wd4mp.status==2}">成功</c:if>
		<c:if test="${wd4mp.status==3}">失败</c:if>
		<c:if test="${wd4mp.status==4}">回滚</c:if>
		<c:if test="${wd4mp.status==4}">终止</c:if>
		<c:if test="${wd4mp.status==49}">操作中</c:if>
		<br>操作员工：${wd4mp.operator.name }
		<hr>
		<div class="_hidden">
			<input type="hidden" name="pid" value="${pid }">
		</div>
		<div>
			<c:if test="${!empty detail}">
				<table>
					<c:forEach var="v" items="${detail}" varStatus="st">
						<tr>
							<td>Profile Name:${v.profile.name }</td>
							<td>优先级:${v.priority }</td>
							<td>备注：${v.comments }</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</form>
</body>
</html>