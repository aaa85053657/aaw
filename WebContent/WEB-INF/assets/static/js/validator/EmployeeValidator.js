$(function() {
	$
			.extend($.fn.validatebox.defaults.rules,
					{
						codeID : {
							validator : function(value) {
								return AAW.async("employee?goto=unique",
										"codeId", value) == "1";
							},
							message : $.i18n.prop('employee.vail.codeID')
						},

						uniqueAccount : {
							validator : function(value) {
								return AAW.async("UpmsAccount?act=unique&id="
										+ $("#validator_id").val(), "name",
										value) == "1";
							},
							message : '输入账户已存在'
						},
						equals : {
							validator : function(value, param) {
								return value == $(param[0]).val();
							},
							message : '两次密码不一致请重新输入'
						}
					});
})