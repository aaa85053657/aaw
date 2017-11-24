<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<script src="uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<script src="uploadify/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$("#uploadify").uploadify({
			//指定swf文件
			'swf' : 'uploadify/uploadify.swf',
			//后台处理的页面
			'uploader' : 'UploadHandler.ashx',
			//按钮显示的文字
			'buttonText' : '上传图片',
			//显示的高度和宽度，默认 height 30；width 120
			//'height': 15,
			//'width': 80,
			//上传文件的类型  默认为所有文件    'All Files'  ;  '*.*'
			//在浏览窗口底部的文件类型下拉菜单中显示的文本
			'fileTypeDesc' : 'Image Files',
			//允许上传的文件后缀
			'fileTypeExts' : '*.gif; *.jpg; *.png',
			//发送给后台的其他参数通过formData指定
			//'formData': { 'someKey': 'someValue', 'someOtherKey': 1 },
			//上传文件页面中，你想要用来作为文件队列的元素的id, 默认为false  自动生成,  不带#
			//'queueID': 'fileQueue',
			//选择文件后自动上传
			'auto' : false,
			//设置为true将允许多文件上传
			'multi' : false
		});
	});
</script>
</head>
<body>
	<div>
		<%--用来作为文件队列区域--%>
		<div id="fileQueue"></div>
		<input type="file" name="uploadify" id="uploadify" />
		<p>
			<a href="javascript:$('#uploadify').uploadify('upload')">上传</a>| <a
				href="javascript:$('#uploadify').uploadify('cancel')">取消上传</a>
		</p>
	</div>
</body>
</html>