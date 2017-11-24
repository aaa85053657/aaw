var Login = {
	url : "main",
	submit : function() {
		$("input[name='brower']").val(navigator.userAgent);
		$("form").attr("action", this.url).submit();
	},
	enter : function() {
		$(document).keydown(function(event) {
			if (event.keyCode == 13) {
				$('form').each(function() {
					this.login();
				});
			}
		})
	},
	// 刷新验证码
	changeImg : function() {
		var imgSrc = $("#imgObj");
		var src = imgSrc.attr("src");
		imgSrc.attr("src", Login.chgUrl(src));
	},
	// 时间戳
	// 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
	chgUrl : function(url) {
		var timestamp = (new Date()).valueOf();
		url = url.substring(0, 17);
		if ((url.indexOf("&") >= 0)) {
			url = url + "×tamp=" + timestamp;
		} else {
			url = url + "?timestamp=" + timestamp;
		}
		return url;
	}
}