$(function() {
	$.BaseCRUD({
		listURL : "deliveryVer?goto=list",
	});
});

var rid;

var uploadFile = function(id) {
	$('#dlg').dialog('open').dialog('setTitle', "上传文件");
	rid = id;
}

var uploadSave = function() {
	$.ajaxFileUpload({
		url : 'deliveryVer?act=uploadFile&id=' + rid,
		secureuri : false, // 是否启用安全提交,默认为false
		fileElementId : 'appFile', // 文件选择框的id属性
		dataType : 'text', // 服务器返回的格式,可以是json或xml等
		success : function(data, status) { // 服务器响应成功时的处理函数
			$.messager.alert($.i18n.prop('model.cfg.tip.title'), "上传成功",
					'warning');
			$('#dlg').dialog('close');
			$('#tt').datagrid('reload');
		}
	});
}

var downloadFile = function(id) {
	// $.post("deliveryVer?act=downloadFile&id=" + id);
	$.download("deliveryVer?act=downloadFile&id=" + id, 'post');
}

jQuery.download = function(url, data, method) {
	if (url && data) {
		data = typeof data == 'string' ? data : jQuery.param(data);
		var inputs = '';
		jQuery.each(data.split('&'), function() {
			var pair = this.split('=');
			inputs += '<input type="hidden" name="' + pair[0] + '" value="'
					+ pair[1] + '" />';
		});
		jQuery(
				'<form action="' + url + '" method="' + (method || 'post')
						+ '">' + inputs + '</form>').appendTo('body').submit()
				.remove();
	}
};