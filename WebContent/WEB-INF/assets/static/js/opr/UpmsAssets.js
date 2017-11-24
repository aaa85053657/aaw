$(function() {
	init();
});
var init = function() {
	$("#treeg").tree({
		url : "UpmsAssets?act=listAll",
		animate : true,
		lines : true
	});
}
