$(function() {
	$.extend($.fn.validatebox.defaults.rules, {
		codeID : {
			validator : function(value) {
				return AAW.async("profileType?goto=unique", "codeId", value) == "1";
			},
			message : $.i18n.prop('profileType.vail.codeID')
		},
		name : {
			validator : function(value) {
				return AAW.async("profileType?goto=unique", "name", value) == "1";
			},
			message : $.i18n.prop('profileType.vail.name')
		}
	});
});