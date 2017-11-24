$(function() {
	$.extend($.fn.validatebox.defaults.rules, {
		codeID : {
			validator : function(value) {
				return AAW.async("metaProcedure?goto=unique", "codeId", value) == "1";
			},
			message : $.i18n.prop('procedure.vail.codeID')
		},

		name : {
			validator : function(value) {
				return AAW.async("metaProcedure?goto=unique", "name", value) == "1";
			},
			message : $.i18n.prop('procedure.vail.name')
		}
	});
});