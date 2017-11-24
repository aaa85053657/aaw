$(function() {
	$.extend($.fn.validatebox.defaults.rules, {
		name : {
			validator : function(value) {
				var gid = $("#groupIdTemp").val();
				return $.ajax({
					async : false,
					cache : false,
					type : 'post',
					url : "commandeStatusTemp?goto=unique",
					data : {
						'propName' : 'name',
						'propVal' : value,
						'id' : $("#validator_id").val(),
						'gid' : $("#groupIdTemp").val()
					}
				}).responseText == "1";

				// AAW.async("commandeStatusTemp?goto=unique", "name", value);
			},
			message : $.i18n.prop('cst.vail.name')
		}
	});
})