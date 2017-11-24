$(function() {
	$.extend($.fn.validatebox.defaults.rules, {
		codeID : {
			validator : function(value) {
				return AAW.async("productionLine?goto=unique", "codeId", value) == "1";
			},
			message : $.i18n.prop('pline.vail.codeID')
		},

		name : {
			validator : function(value) {
				return AAW.async("productionLine?goto=unique", "name", value) == "1";
			},
			message : $.i18n.prop('pline.vail.name')
		}
	});
});