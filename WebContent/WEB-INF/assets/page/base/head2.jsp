<%@page import="org.springframework.web.servlet.support.RequestContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="head1.jsp" />
<%
	RequestContext rc = new RequestContext(request);
	String locale = rc.getLocale().getLanguage();
	String lang = "<script type=\"text/javascript\" src=\"static/eui/locale/easyui-lang-zh_CN.js\"></script>";
	if (locale.equals("zh")) {
		lang = "<script type=\"text/javascript\" src=\"static/eui/locale/easyui-lang-zh_CN.js\"></script>";
	} else if (locale.equals("en")) {
		lang = "<script type=\"text/javascript\" src=\"static/eui/locale/easyui-lang-en.js\"></script>";
	}
	out.print(lang);
%>
<!-- <script type="text/javascript" -->
<!-- 	src="static/eui/locale/easyui-lang-zh_CN.js"></script> -->
<!-- <script type="text/javascript" src="static/eui/locale/easyui-lang-en.js"></script> -->
<script type="text/javascript" src="static/js/common/defaultSet.js"></script>
<script type="text/javascript" src="static/js/common/molos.win.js"></script>
<script type="text/javascript" src="static/js/common/jQuery.print.js"></script>
<script type="text/javascript" src="static/js/common/jquery.AAW.js"></script>
<script type="text/javascript"
	src="static/js/common/easyui.mergeCell.js"></script>
<script type="text/javascript"
	src="static/js/common/datagrid-detailview.js"></script>
<script type="text/javascript" src="static/js/common/datagrid-filter.js"></script>
<script type="text/javascript"
	src="static/js/common/Jquery.easyui.tooltip.js"></script>