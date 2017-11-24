$
		.extend(
				$.fn.validatebox.defaults.rules,
				{

					phone : {// 验证电话号码
						validator : function(value) {
							return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i
									.test(value);
						},
						message : '格式不正确,请使用下面格式:0510-88888888'
					},
					mobile : {// 验证手机号码
						validator : function(value) {
							return /^(13|14|15|18)\d{9}$/i.test(value);
						},
						message : "无效的手机号码！"
					},

					qq : {// 验证QQ,从10000开始
						validator : function(value) {
							return /^[1-9]\d{4,9}$/i.test(value);
						},
						message : 'QQ号码格式不正确'
					},

					age : {// 验证年龄
						validator : function(value) {
							return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i
									.test(value);
						},
						message : '年龄必须是0到120之间的整数'
					}
				});