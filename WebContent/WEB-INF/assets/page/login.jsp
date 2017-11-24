<%@page import="cn.molos.common.SessionConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="static/css/login.css">
<jsp:include page="base/head1.jsp" />
<script type="text/javascript" src="static/js/common/Login.js"></script>
</head>
<body>
	<%
		if (session.getAttribute(SessionConstant.LOGIN_USER) != null) {
			response.sendRedirect("main");
		}
	%>
	<div class="loginTop">
		<div>
			<a href="?lang=en"><spr:message code='aaw.lang.en' /></a>|<a
				href="?lang=zh"><spr:message code='aaw.lang.zh' /></a>
		</div>
	</div>
	<div class="login-panel">
		<div class="logo"></div>
		<div class="login-form">
			<form action="login" method="post" class="form1">
				<input type="hidden" name="brower" />
				<div class="loginDiv">
					<label for="uname"><img src="static/images/user.gif"
						class="btnImg" /> <spr:message code='login.username' /></label> <input
						name="name" value="${oldacc }" />
				</div>
				<div class="loginDiv">
					<label for="pazzwd"><img src="static/images/password.gif"
						class="btnImg" /> <spr:message code='login.password' /></label> <input
						type="password" name="pazzwd" value="${oldpwd }" />
				</div>
				<div class="loginDiv">
					<label for="pazzwd"><spr:message code='login.validate' /></label>
					<input id="veryCode" type="text" name="veryCode"
						style="width: 80px; float: left; margin-right: 10px;" /><img
						id="imgObj" alt="验证码" src="ImgVailCode"
						onClick="Login.changeImg()" />
				</div>
				<div class="loginDiv">
					<label for="pazzwd"> <spr:message code='login.type' /></label><select
						name="type">
						<option value="1">总公司</option>
						<option value="2">加盟商</option>
					</select>
				</div>

				<c:if test="${!empty error}">
					<div class="login-error">${error }</div>
				</c:if>

				<a class="login-button" href="javascript:void(0);"
					onclick="Login.submit();"></a>

			</form>
		</div>
		<div class="loginbot"></div>
	</div>
	<div class="loginBottom"></div>
</body>
</html>