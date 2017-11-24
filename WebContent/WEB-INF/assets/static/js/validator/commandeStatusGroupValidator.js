$(function() {
	$.extend($.fn.validatebox.defaults.rules, {
		codeID : {
			validator : function(value) {
				return AAW.async("productAttribute?goto=unique", "codeId",
						value) == "1";
			},
			message : $.i18n.prop('attribute.vail.codeID')
		},

		name : {
			validator : function(value) {
				return AAW.async("productAttribute?goto=unique", "name", value) == "1";
			},
			message : $.i18n.prop('attribute.vail.name')
		}
	});
});