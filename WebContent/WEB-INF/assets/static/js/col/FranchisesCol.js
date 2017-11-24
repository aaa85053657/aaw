$(function() {
	dataArr = [ [
			{
				field : 'id',
				title : "id",
				width : 30,
				hidden : true
			},
			{
				field : 'code',
				title : $.i18n.prop('franchises.obj.mid'),
				width : 30
			},
			{
				field : 'name',
				title : $.i18n.prop('franchises.obj.name'),
				width : 30
			},
			{
				field : 'description',
				title : $.i18n.prop('franchises.obj.description'),
				width : 30
			},
			{
				field : 'address',
				title : $.i18n.prop('franchises.obj.address'),
				width : 30
			},
			{
				field : 'contactName',
				title : $.i18n.prop('franchises.obj.contactName'),
				width : 30
			},
			{
				field : 'contactNumber',
				title : $.i18n.prop('franchises.obj.contactNumber'),
				width : 30
			},
			{
				field : 'contactEmail',
				title : $.i18n.prop('franchises.obj.email'),
				width : 30
			},
			{
				field : 'contactPhone',
				title : $.i18n.prop('franchises.obj.phone'),
				width : 30,
				hidden : true
			},
			{
				field : 'state',
				title : $.i18n.prop('franchises.obj.state'),
				width : 30,
				formatter : function(r) {
					if (r == 0) {
						return $.i18n.prop('franchises.obj.state.nactivtion')
					} else if (r == 1) {
						return $.i18n.prop('franchises.obj.state.activtion');
					} else {
						return $.i18n.prop('franchises.obj.state.del');
					}
				}
			},
			{
				field : 'franchiseLevel',
				title : $.i18n.prop('franchises.obj.level'),
				width : 30,
				formatter : function(v) {
					if (v == 1) {
						return $.i18n.prop('franchises.obj.levelOne');
					} else if (v == 2) {
						return $.i18n.prop('franchises.obj.levelTow');
					} else if (v == 3) {
						return $.i18n.prop('franchises.obj.levelThree');
					} else if (v == 4) {
						return $.i18n.prop('franchises.obj.levelFour');
					} else if (v == 5) {
						return $.i18n.prop('franchises.obj.levelFive');
					} else {
						return "";
					}
				}
			},
			{
				field : 'parentId',
				title : $.i18n.prop('franchises.obj.parentId'),
				width : 30,
				hidden : true
			},
			{
				field : 'createTime',
				title : $.i18n.prop('franchises.obj.createTime'),
				width : 30,
				hidden : true
			},
			{
				field : 'modificationTime',
				title : $.i18n.prop('franchises.obj.modificationTime'),
				width : 30,
				hidden : true
			},
			{
				field : 'deleteTime',
				title : $.i18n.prop('franchises.obj.deleteTime'),
				width : 30,
				hidden : true
			},
			{
				field : 'isCreateAcc',
				title : $.i18n.prop('franchises.opt'),
				width : 30,
				formatter : function(v) {
					if (v == 1) {
						return "<a href='javascript:BtnCfg(1)'>"
								+ $.i18n.prop('franchises.add.acc') + "</a>"
					} else {
						return "<a href='javascript:BtnCfg(2)'>"
								+ $.i18n.prop('franchises.update.acc') + "</a>"
					}
				}
			} ] ];
});