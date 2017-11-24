$(function() {
	$.extend($.fn.validatebox.defaults.rules,
			{
				codeID : {
					validator : function(value) {
						return AAW.async("productElement?goto=unique",
								"codeId", value) == "1";
					},
					message : $.i18n.prop('attrVal.vail.codeID')
				},

				name : {
					validator : function(value) {
						// return AAW.async("productElement?goto=unique",
						// "name", value) == "1";
						return true;
					},
					message : $.i18n.prop('attrVal.vail.name')
				}
			});
})
