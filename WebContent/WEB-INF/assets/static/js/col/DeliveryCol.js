$(function() {
	dataArr = [ [
			{
				field : 'id',
				title : $.i18n.prop('delivery.obj.id'),
				width : 30,
				hidden : true
			},
			{
				field : 'currentVer',
				title : $.i18n.prop('delivery.obj.currentVer'),
				width : 30
			},
			{
				field : 'lastVer',
				title : $.i18n.prop('delivery.obj.lastVer'),
				width : 30
			},
			{
				field : 'creationTime',
				title : $.i18n.prop('delivery.obj.creationTime'),
				width : 30
			},
			{
				field : 'fileInfo',
				title : $.i18n.prop('customer.obj.fileInfo'),
				width : 30,
				formatter : function(val, row) {
					if (val) {
						return "<a href='javascript:downloadFile("
								+ row.id
								+ ")'>下载</a>&nbsp;<a href='javascript:uploadFile("
								+ row.id + ")'>重传</a>";
					} else {
						return "<a href='javascript:uploadFile(" + row.id
								+ ")'>上传</a>";
					}
				}
			} ] ];
});
