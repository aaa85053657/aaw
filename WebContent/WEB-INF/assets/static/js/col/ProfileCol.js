$(function() {
			dataArr = [[{
						field : 'id',
						title : $.i18n.prop('profile.obj.id'),
						width : 30,
						hidden : true
					}, {
						field : 'codeId',
						title : $.i18n.prop('profile.obj.codeID'),
						width : 30
					}, {
						field : 'name',
						title : $.i18n.prop('profile.obj.name'),
						width : 30
					}, {
						field : 'type',
						title : $.i18n.prop('profile.obj.type'),
						width : 30,
						formatter : function(r) {
							if (r) {
								return r.name;
							}
							return "";
						}
					}, {
						field : 'valueDefault',
						title : $.i18n.prop('profile.obj.valueDefault'),
						width : 30
					}, {
						field : 'valueMin',
						title : $.i18n.prop('profile.obj.valueMin'),
						width : 30
					}, {
						field : 'valueMax',
						title : $.i18n.prop('profile.obj.valueMax'),
						width : 30
					}, {
						field : 'comments',
						title : $.i18n.prop('profile.obj.comments'),
						width : 30
					}]];
		});