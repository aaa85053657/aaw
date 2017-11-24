$(function() {
	$
			.extend($.fn.validatebox.defaults.rules,
					{
						serialNumber : {
							validator : function(value) {
								if (AAW.empty(value)) {
									return true;
								}
								return AAW.async("worksheet?goto=unique",
										"serialNumber", value) == "1";
							},
							message : $.i18n
									.prop('worksheet.vail.serialNumber')
						},
						name : {
							validator : function(value) {
								return AAW.async("worksheet?goto=unique",
										"name", value) == "1";
							},
							message : $.i18n.prop('worksheet.vail.name')
						},
						uniqueCommande : {
							validator : function(value) {
								return AAW.async(
										"worksheet?goto=uniqueCommande",
										"commande", value) == "1";
							},
							message : "此单号已创建加工单"
						}
					});
});