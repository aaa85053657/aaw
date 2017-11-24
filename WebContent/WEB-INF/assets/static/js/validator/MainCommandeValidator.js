$(function() {
	$.extend($.fn.validatebox.defaults.rules,
			{
				codeID : {
					validator : function(value) {
						return AAW.async("mainCommande?goto=unique", "codeId",
								value) == "1";
					},
					message : $.i18n.prop('mCommande.vail.codeID')
				},
				codeIDCus : {
					validator : function(value) {
						return AAW.async("customerInfo?goto=unique", "codeId",
								value) == "1";
					},
					message : $.i18n.prop('customer.vail.codeID')
				},
				externalCode : {
					validator : function(value) {
						return AAW.async("mainCommande?goto=unique2",
								"externalCode", value) == "1";
					},
					message : $.i18n.prop('customer.vail.codeID')
				},
				slCodeID : {
					validator : function(value) {
						return $.ajax({
							async : false,
							cache : false,
							type : 'post',
							url : "slaveCommande?goto=unique",
							data : {
								'propName' : "codeId",
								'propVal' : value,
								'id' : $("#vali_id").val()
							}
						}).responseText == "1"
					},
					message : $.i18n.prop('slave.vail.codeID')
				}
			});
})