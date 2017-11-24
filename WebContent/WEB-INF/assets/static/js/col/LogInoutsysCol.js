$(function() {
			dataArr = [[{
						field : 'id',
						title : $.i18n.prop('LogInoutsys.obj.id'),
						width : 30,
						hidden : true
					}, {
						field : 'timeCreation',
						title : $.i18n.prop('LogInoutsys.obj.timeCreation'),
						width : 30
					}, {
						field : 'ipFrom',
						title : $.i18n.prop('LogInoutsys.obj.ipFrom'),
						width : 30
					}, {
						field : 'browerInfo',
						title : $.i18n.prop('LogInoutsys.obj.browerInfo'),
						width : 30
					}, {
						field : 'type',
						title : $.i18n.prop('LogInoutsys.obj.type'),
						width : 30,
						formattor : function(v, r) {
							switch (v) {
								case 0 :
									return "登录";
								case 1 :
									return "注销";
								case 2 :
									return "session超时";
							}
							return "";
						}
					}]];
		});
