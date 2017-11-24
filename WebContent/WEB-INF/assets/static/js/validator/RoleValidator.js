$(function() {
	$.extend($.fn.validatebox.defaults.rules, {
		rolename : {
			validator : function(value) {
				return AAW.async("upmsRole?goto=unique", "name", value) == "1";
			},
			message : "角色名已存在"
		}
	});
})