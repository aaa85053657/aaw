$(function() {
	$.extend($.fn.validatebox.defaults.rules, {
		codeID : {
			validator : function(value) {
				return AAW.async("customerInfo?goto=unique", "codeId", value) == "1";
			},
			message : $.i18n.prop('customer.vail.codeID')
		}
	});
})