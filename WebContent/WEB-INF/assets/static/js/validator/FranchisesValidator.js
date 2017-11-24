$(function() {
	$.extend($.fn.validatebox.defaults.rules, {
		uniqueAccount : {
			validator : function(value) {
				var id = $("#aid").val();
				if (id == null || id == "") {
					id = 0;
				}
				return AAW.async("franchisesAcc?act=unique&id=" + id,
						"account", value) == "1";
			},
			message : '输入账户已存在'
		},
		uniquename : {
			validator : function(value) {
				var id = $("#validator_id").val();
				if (id == null || id == "") {
					id = 0;
				}
				return AAW.async("franchises?act=unique&id=" + id,
						"name", value) == "1";
			},
			message : '输入账户已存在'
		},
		uniqueemail : {
			validator : function(value) {
				var id = $("#validator_id").val();
				if (id == null || id == "") {
					id = 0;
				}
				return AAW.async("franchises?act=unique&id=" + id,
						"contactEmail", value) == "1";
			},
			message : '输入账户已存在'
		},
	});
})