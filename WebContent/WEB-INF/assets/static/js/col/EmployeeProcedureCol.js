$(function() {
	dataArr = [ [ {
		field : 'id',
		title : $.i18n.prop('employee.obj.id'),
		width : 30,
		hidden : true
	}, {
		field : 'codeId',
		title : $.i18n.prop('employee.obj.codeID'),
		width : 30
	}, {
		field : 'name',
		title : $.i18n.prop('employee.obj.name'),
		width : 30
	}, {
		field : 'age',
		title : $.i18n.prop('employee.obj.age'),
		width : 30
	}, {
		field : 'sex',
		title : $.i18n.prop('employee.obj.sex'),
		width : 30,
		formatter : sexFormat
	}, {
		field : 'email',
		title : $.i18n.prop('employee.obj.email'),
		width : 30
	}, {
		field : 'fax',
		title : $.i18n.prop('employee.obj.fax'),
		width : 30
	}, {
		field : 'timeCreation',
		title : $.i18n.prop('employee.obj.timeCreation'),
		width : 30,
		hidden : true
	}, {
		field : 'timeModification',
		title : $.i18n.prop('employee.obj.timeModification'),
		hidden : true,
		width : 30
	}, {
		field : 'timeDelete',
		title : $.i18n.prop('employee.obj.timeDelete'),
		hidden : true,
		width : 30
	}, {
		field : 'isIncumbent',
		title : $.i18n.prop('employee.obj.isIncumbent'),
		width : 30,
		formatter : incumbentFormat
	}, {
		field : 'address',
		title : $.i18n.prop('employee.obj.address'),
		width : 30
	}, {
		field : 'isStaff',
		title : $.i18n.prop('employee.obj.isStaff'),
		width : 30,
		formatter : staffFormat
	}, {
		field : 'country',
		title : $.i18n.prop('employee.obj.country'),
		width : 30,
		formatter : function(row) {
			if (row) {
				return row.name;
			}
			return '';
		}
	}, {
		field : 'company',
		title : $.i18n.prop('employee.obj.company'),
		width : 30,
		formatter : function(row) {
			if (row) {
				return row.name;
			}
			return '';
		}
	}, {
		field : 'companyAddress',
		title : $.i18n.prop('employee.obj.companyAddress'),
		width : 30
	}, {
		field : 'account',
		title : $.i18n.prop('employee.obj.account'),
		width : 30,
		hidden : true,
		formatter : function(row) {
			if (row) {
				return row.name;
			}
			return '';
		}

	} ] ];
	arr = [ [
			{
				field : 'id',
				title : 'id',
				width : 30,
				hidden : true
			},
			{
				field : 'codeId',
				title : $.i18n.prop('employee.skill.codeID'),
				width : 50
			},
			{
				field : 'name',
				title : $.i18n.prop('employee.skill.name'),
				width : 50
			},
			{
				field : 'comments',
				title : $.i18n.prop('employee.skill.dc'),
				width : 50
			},
			{
				field : 'opr',
				title : $.i18n.prop('employee.skill.opr'),
				formatter : function(v, row, index) {
					return '<a href="javascript:del(' + row.id + ')">'
							+ $.i18n.prop('employee.skill.opr.unbind') + '</a>';
				}
			} ] ];
});