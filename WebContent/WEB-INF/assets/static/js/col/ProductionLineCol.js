$(function() {
			dataArr = [[{
						field : 'id',
						title : $.i18n.prop('pline.obj.id'),
						width : 30,
						hidden : true
					}, {
						field : 'codeId',
						title : $.i18n.prop('pline.obj.codeID'),
						width : 30
					}, {
						field : 'name',
						title : $.i18n.prop('pline.obj.name'),
						width : 30
					}, {
						field : 'averagetime',
						title : $.i18n.prop('pline.obj.averagetime'),
						width : 30
					}, {
						field : 'comments',
						title : $.i18n.prop('pline.obj.comments'),
						width : 30
					}]];

			cfgArr = [[{
						field : 'codeId',
						title : $.i18n.prop('pline.obj2.codeId'),
						width : 30
					}, {
						field : 'name',
						title : $.i18n.prop('pline.obj2.name'),
						width : 30
					}, {
						field : 'comments',
						title : $.i18n.prop('pline.obj2.comments'),
						width : 30
					}, {
						field : 'sequenceNum',
						title : $.i18n.prop('pline.obj2.sequenceNum'),
						width : 30
					}, {
						field : 'id',
						title : $.i18n.prop('pline.obj2.id'),
						width : 30,
						formatter : function(v, r, index) {
							return "<a href='javascript:delCfg(" + v
									+ ")'>删除</a>";
						}
					}]];
		});
