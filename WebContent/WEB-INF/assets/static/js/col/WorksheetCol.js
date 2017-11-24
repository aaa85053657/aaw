$(function() {
	dataArr = [ [ {
		field : 'mid',
		title : $.i18n.prop('worksheet.obj.mid'),
		width : 30,
		hidden : true
	}, {
		field : 'sid',
		title : $.i18n.prop('worksheet.obj.sid'),
		width : 30,
		hidden : true
	}, {
		field : 'id',
		title : $.i18n.prop('worksheet.obj.id'),
		width : 30,
		hidden : true
	}, {
		field : 'serialNumber',
		title : $.i18n.prop('worksheet.obj.serialNumber'),
		width : 30
	}, {
		field : 'slaveCommande',
		title : $.i18n.prop('worksheet.obj.slaveCommande'),
		width : 30,
		formatter : function(v, r) {
			if (v) {
				return v.mainCommande.codeId + "," + v.codeId;
			}
			return "订单已删除"
		}
	}, {
		field : 'timeCreation',
		title : $.i18n.prop('worksheet.obj.timeCreation'),
		width : 30
	}, {
		field : 'timeModification',
		title : $.i18n.prop('worksheet.obj.timeModification'),
		width : 30
	}, {
		field : 'timeDelete',
		title : $.i18n.prop('worksheet.obj.timeDelete'),
		width : 30
	}, {
		field : 'timeFinish',
		title : $.i18n.prop('worksheet.obj.timeFinish'),
		width : 30
	}, {
		field : 'timeDelivery',
		title : $.i18n.prop('worksheet.obj.timeDelivery'),
		width : 30
	}, {
		field : 'timeReceive',
		title : $.i18n.prop('worksheet.obj.timeReceive'),
		width : 30
	}, {
		field : 'timeAbort',
		title : $.i18n.prop('worksheet.obj.timeAbort'),
		width : 30
	} ] ];
});