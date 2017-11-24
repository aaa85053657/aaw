$(function() {
	$.BaseCRUD({
		listURL : "productElement?goto=list",
		addURL : "productElement?goto=save",
		updateURL : "productElement?goto=update",
		delURL : "productElement?goto=del",
		titleAddF : $.i18n.prop('attrVal.title.add'),
		titleUpdateF : $.i18n.prop('attrVal.title.update')
	});

	$("#uploadImg").click(
			function() {
				$('#dlg-upload').dialog('center').dialog('open').dialog(
						'setTitle', "上传示例图");
			});

	$("#initPic")
			.click(
					function() {
						$
								.post(
										"productElement?goto=imgrefresh",
										function(data) {
											var html = "图片更新后</br>" + "含有图片属性："
													+ data.pic + " 缺失图片属性："
													+ data.picNull + "</br>";
											html += "<a href='javascript:download()'>具体详情请点击此处下载查看</a>";
											$("#dlInfo").html(html);
											$("#dlg-dl")
													.dialog('center')
													.dialog('open')
													.dialog('setTitle', "上传示例图");
										});
					});
});
function upload() {
	$("#fm-upload").ajaxSubmit(
			{
				url : "productElement/upload",
				dataType : "json",
				resetForm : true,
				error : function() {
					console.log("error")
				},
				success : function(v) {
					if (v && v == 1) {
						$('#dlg-upload').dialog('close');
						var d = new Date();
						$("#pre").attr("src",
								"productElement/show?t=" + d.getMilliseconds())
					}
				}
			})
}
var backfill = function() {
	$("#cc").combobox('select', AAW.selectedRow().attribute.id);
	if (AAW.selectedRow().samplepath) {
		$("#pre").attr("src",
				"productElement/showById?id=" + AAW.selectedRow().id);
	}
}
var ccLoad = function() {
	$("#cc").combobox('reload', 'productAttribute?goto=combobox');
	$("#pre").removeAttr("src");
}

var showPicture = function(id) {
	$("#picture").attr('src', "productElement/showById?id=" + id);
	$('#dlg-showPic').dialog('center').dialog('open').dialog('setTitle',
			"显示示例图");

}

var download = function() {
	$.download('productElement?goto=downloadEle', 'post');
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

//
// var fileUpload = function(newValue, oldValue) {
// console.log(newValue);
// // $.ajaxFileUpload({
// // url : 'uploadFile', // 需要链接到服务器地址
// // secureuri : false,
// // fileElementId : 'sampleUpload', // 文件选择框的id属性
// // dataType : 'json', // 服务器返回的格式，可以是json
// // success : function(data, status) { // 相当于java中try语句块的用法
// // console.log(data);
// // if (data.status == 1) {
// // $("#pre").attr("src", data.src);
// // $("input[name='samplepath']").val(data.src);
// // }
// // },
// // error : function(data, status, e) {
// // // $('#result').html('添加失败');
// // }
// // });
//
// }

