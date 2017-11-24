$(function() {
	$.extend($.fn.validatebox.defaults.rules, {
		name : {
			validator : function(value) {
				return AAW.async("commandePriority?goto=unique", "name", value) == "1";
			},
			message : $.i18n.prop('priority.vail.name')
		}
	});
})