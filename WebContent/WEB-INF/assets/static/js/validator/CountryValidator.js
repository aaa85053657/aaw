$(function() {
	$.extend($.fn.validatebox.defaults.rules, {
		codeID : {
			validator : function(value) {
				return AAW.async("country?goto=unique", "codeId", value) == "1";
			},
			message : $.i18n.prop('country.vail.codeID')
		},
		name : {
			validator : function(value) {
				return AAW.async("country?goto=unique", "name", value) == "1";
			},
			message : $.i18n.prop('country.vail.name')
		},
		shortCode : {
			validator : function(value) {
				return AAW.async("country?goto=unique", "codeId", value) == "1";
			},
			message : $.i18n.prop('country.vail.shortCode')
		},
		areaCode : {
			validator : function(value) {
				return AAW.async("country?goto=unique", "name", value) == "1";
			},
			message : $.i18n.prop('country.vail.areaCode')
		}
	});
})