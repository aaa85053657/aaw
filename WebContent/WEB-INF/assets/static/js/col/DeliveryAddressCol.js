$(function() {
			dataArr = [[{
						field : 'id',
						title : $.i18n.prop('daddr.obj.id'),
						width : 30,
						hidden : true
					}, {
						field : 'customer',
						title : $.i18n.prop('daddr.obj.customer'),
						width : 30,
						formatter : function(r) {
							if (r) {
								return r.firstName + " " + r.middleName + " "
										+ r.lastName;
							}
							return "";
						}
					}, {
						field : 'address',
						title : $.i18n.prop('daddr.obj.address'),
						width : 30
					}, {
						field : 'posteCode',
						title : $.i18n.prop('daddr.obj.posteCode'),
						width : 30
					}]];
		});
