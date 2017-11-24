$(function() {
			dataArr = [[{
						field : 'id',
						title : $.i18n.prop('LogException.obj.id'),
						width : 30,
						hidden : true
					}, {
						field : 'employee',
						title : $.i18n.prop('LogException.obj.employee'),
						width : 30,
						formatter : function(v) {
							if (v) {
								return v.name;
							}
							return "";
						}
					}, {
						field : 'level',
						title : $.i18n.prop('LogException.obj.level'),
						width : 30
					}, {
						field : 'content',
						title : $.i18n.prop('LogException.obj.content'),
						width : 30
					}, {
						field : 'requestAddr',
						title : $.i18n.prop('LogException.obj.requestAddr'),
						width : 30
					}]];
		});
