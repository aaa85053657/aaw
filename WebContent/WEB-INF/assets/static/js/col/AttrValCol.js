$(function() {
	dataArr = [ [
			{
				field : 'id',
				title : $.i18n.prop('attrVal.obj.id'),
				width : 30,
				hidden : true
			},
			{
				field : 'codeId',
				title : $.i18n.prop('attrVal.obj.codeID'),
				width : 30
			},
			{
				field : 'name',
				title : $.i18n.prop('attrVal.obj.name'),
				width : 30
			},
			{
				field : 'attribute',
				title : $.i18n.prop('attrVal.obj.type'),
				width : 30,
				formatter : function(r) {
					if (r) {
						return r.name;
					}
					return "";
				}
			},
			{
				field : 'samplepath',
				title : $.i18n.prop('attrVal.obj.samplepath'),
				width : 30,
				formatter : function(v, row) {
					if (v) {
						return "<a href='javascript:showPicture(" + row.id
								+ ")'>" + v + "</a>";
					}
				}
			}, {
				field : 'pixel',
				title : $.i18n.prop('attrVal.obj.pixel'),
				width : 30
			}, {
				field : 'comments',
				title : $.i18n.prop('attrVal.obj.comments'),
				width : 30
			} ] ];
});
