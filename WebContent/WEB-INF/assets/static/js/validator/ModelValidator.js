$(function() {
	$.extend($.fn.validatebox.defaults.rules, {
		codeID : {
			validator : function(value) {
				return AAW.async("productModel?goto=unique", "codeId", value) == "1";
			},
			message : $.i18n.prop('model.vail.codeID')
		},

		name : {
			validator : function(value) {
				return AAW.async("productModel?goto=unique", "name", value) == "1";
			},
			message : $.i18n.prop('model.vail.name')
		}
	});
});