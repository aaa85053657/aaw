$(function() {
	dataArr = [ [
			{
				field : 'id',
				title : $.i18n.prop('employee.obj.id'),
				width : 30,
				hidden : true
			},
			{
				field : 'codeId',
				title : $.i18n.prop('employee.obj.codeID'),
				width : 30
			},
			{
				field : 'badgeCode',
				title : $.i18n.prop('employee.obj.badgeCode'),
				width : 30
			},
			{
				field : 'name',
				title : $.i18n.prop('employee.obj.name'),
				width : 30
			},
			{
				field : 'age',
				title : $.i18n.prop('employee.obj.age'),
				width : 30
			},
			{
				field : 'sex',
				title : $.i18n.prop('employee.obj.sex'),
				width : 30,
				formatter : sexFormat
			},
			{
				field : 'email',
				title : $.i18n.prop('employee.obj.email'),
				width : 30
			},
			{
				field : 'fax',
				title : $.i18n.prop('employee.obj.fax'),
				width : 30
			},
			{
				field : 'timeCreation',
				title : $.i18n.prop('employee.obj.timeCreation'),
				width : 30,
				hidden : true
			},
			{
				field : 'timeModification',
				title : $.i18n.prop('employee.obj.timeModification'),
				hidden : true,
				width : 30
			},
			{
				field : 'timeDelete',
				title : $.i18n.prop('employee.obj.timeDelete'),
				hidden : true,
				width : 30
			},
			{
				field : 'isIncumbent',
				title : $.i18n.prop('employee.obj.isIncumbent'),
				width : 30,
				formatter : incumbentFormat
			},
			{
				field : 'address',
				title : $.i18n.prop('employee.obj.address'),
				width : 30
			},
			{
				field : 'isStaff',
				title : $.i18n.prop('employee.obj.isStaff'),
				width : 30,
				formatter : staffFormat
			},
			{
				field : 'country',
				title : $.i18n.prop('employee.obj.country'),
				width : 30,
				formatter : function(row) {
					if (row) {
						return row.name;
					}
					return '';
				}
			},
			{
				field : 'company',
				title : $.i18n.prop('employee.obj.company'),
				width : 30,
				formatter : function(row) {
					if (row) {
						return row.name;
					}
					return '';
				}
			},
			{
				field : 'companyAddress',
				title : $.i18n.prop('employee.obj.companyAddress'),
				width : 30
			},
			{
				field : 'account',
				title : $.i18n.prop('employee.obj.opr'),
				width : 30,
				formatter : function(row) {
					if (row) {
						return row.name
								+ "|<a href='javascript:Emp.updateAcc();void(0);'>"
								+ "修改账户" + "</a>";
					}
					return "<a href='javascript:Emp.createAcc();void(0);'>"
							+ $.i18n.prop('employee.obj.bindOpr') + "</a>";
				}
			} ] ];
});
