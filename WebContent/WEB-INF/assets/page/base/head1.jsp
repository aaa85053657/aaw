<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="static/css/tanchu.css" />
<link rel="stylesheet" type="text/css"
	href="static/eui/themes/default/easyui.css" />
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="static/eui/themes/black/easyui.css" /> -->
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="static/eui/themes/bootstrap/easyui.css" /> -->
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="static/eui/themes/gray/easyui.css" /> -->
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="static/eui/themes/metro/easyui.css" /> -->
<link rel="stylesheet" type="text/css" href="static/eui/themes/icon.css">
<script type="text/javascript" src="static/eui/jquery.min.js"></script>
<script type="text/javascript" src="static/eui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="static/js/common/jquery.i18n.properties.js"></script>
<script type="text/javascript">
	$(function() {
		$.i18n.properties({
			name : 'strings', //资源文件名称
			path : 'static/js/locale/', //资源文件路径
			mode : 'map', //用Map的方式使用资源文件中的值
			language : $.ajax({
				async : false,
				cache : false,
				type : 'post',
				url : "lang"
			}).responseText,
			callback : function() {
			}
		});
	});
</script>