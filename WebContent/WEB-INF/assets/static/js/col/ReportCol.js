$(function() {
	dataArr = [ [ {
		field : 'id',
		title : $.i18n.prop('priority.obj.id'),
		width : 30,
		hidden : true
	}, {
		field : 'name',
		title : $.i18n.prop('report.obj.name'),
		width : 30
	}, {
		field : 'description',
		title : $.i18n.prop('report.obj.description'),
		width : 30
	}, {
		field : 'sqlText',
		title : $.i18n.prop('report.obj.sqlText'),
		width : 30,
		hidden : true
	}, {
		field : 'comments',
		title : $.i18n.prop('priority.obj.comments'),
		width : 30
	} ] ];
});
