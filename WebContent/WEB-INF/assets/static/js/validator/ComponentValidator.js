$.extend($.fn.validatebox.defaults.rules, {
	codeID : {
		validator : function(value) {
			return AAW.async("productComponent?goto=unique", "codeId", value) == "1";
		},
		message : $.i18n.prop('component.vail.codeID')
	},

	name : {
		validator : function(value) {
			return AAW.async("productComponent?goto=unique", "name", value) == "1";
		},
		message : $.i18n.prop('component.vail.name')
	}
});