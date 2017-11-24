$(function() {
			dataArr = [[{
						field : 'id',
						title : $.i18n.prop('customer.obj.id'),
						width : 30,
						hidden : true
					}, {
						field : 'firstName',
						title : $.i18n.prop('customer.obj.firstName'),
						width : 30
					}, {
						field : 'middleName',
						title : $.i18n.prop('customer.obj.middleName'),
						width : 30
					}, {
						field : 'lastName',
						title : $.i18n.prop('customer.obj.lastName'),
						width : 30
					}, {
						field : 'sex',
						title : $.i18n.prop('customer.obj.sex'),
						width : 30,
						formatter : sexFormat
					}, {
						field : 'email',
						title : $.i18n.prop('customer.obj.email'),
						width : 30
					}, {
						field : 'fax',
						title : $.i18n.prop('customer.obj.fax'),
						width : 30
					}, {
						field : 'country',
						title : $.i18n.prop('customer.obj.country'),
						width : 30,
						formatter : function(row) {
							if (row) {
								return row.name;
							}
							return '';
						}
					}, {
						field : 'telephone',
						title : $.i18n.prop('customer.obj.telephone'),
						width : 30
					}, {
						field : 'mobile',
						title : $.i18n.prop('customer.obj.mobile'),
						width : 30
					}, {
						field : 'timeCreation',
						title : $.i18n.prop('customer.obj.timeCreation'),
						width : 30
					}, {
						field : 'timeModification',
						title : $.i18n.prop('customer.obj.timeModification'),
						width : 30
					}, {
						field : 'timeDelete',
						title : $.i18n.prop('customer.obj.timeDelete'),
						width : 30
					}]];
		});
