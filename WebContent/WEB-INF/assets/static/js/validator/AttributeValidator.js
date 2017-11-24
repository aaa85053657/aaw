$(function() {
	$.extend($.fn.validatebox.defaults.rules,
			{
				codeID : {
					validator : function(value) {
						return AAW.async("productAttribute?goto=unique",
								"codeId", value) == "1";
					},
					message : $.i18n.prop('attribute.vail.codeID')
				},

				name : {
					validator : function(value) {
						return AAW.async("productAttribute?goto=unique",
								"name", value) == "1";
					},
					message : $.i18n.prop('attribute.vail.name')
				},
				path : {
					validator : function(value) {
						return /^[0-9a-zA-Z]*$/.test(value);
					},
					message : $.i18n.prop('attribute.vail.path')
				},
				uniquePath : {
					validator : function(value) {
						return AAW.async("productAttribute?goto=unique",
								"path", value) == "1";
					},
					message : $.i18n.prop('attribute.vail.pathUnique')
				}
			});
});