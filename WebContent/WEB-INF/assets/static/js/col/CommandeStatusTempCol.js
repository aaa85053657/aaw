$(function() {
	dataArr = [ [
			{
				field : 'id',
				title : $.i18n.prop('cst.obj.id'),
				width : 30,
				hidden : true
			},
			{
				field : 'parentId',
				title : $.i18n.prop('cst.obj.parentId'),
				width : 30,
				hidden : true
			},
			{
				field : 'nextId',
				title : $.i18n.prop('cst.obj.nextId'),
				width : 30,
				hidden : true
			},
			{
				field : 'previousId',
				title : $.i18n.prop('cst.obj.previousId'),
				width : 30,
				hidden : true
			},
			{
				field : 'name',
				title : $.i18n.prop('cst.obj.name'),
				width : 30,
				formatter : function(v, node) {
					if (node.children) {
						v += '&nbsp;<span style=\'color:blue\'>('
								+ node.children.length + ')</span>';
					}
					return v;
				}
			},
			{
				field : 'comments',
				title : $.i18n.prop('cst.obj.comments'),
				width : 30
			},
			{
				field : 'isSendMail',
				title : $.i18n.prop('cst.obj.isSendMail'),
				width : 30,
				formatter : function(v) {
					return v > 0 ? $.i18n.prop('cst.obj.isSendMail.y') : $.i18n
							.prop('cst.obj.isSendMail.n');
				}
			}, {
				field : 'hasChildren',
				title : $.i18n.prop('cst.obj.hasChildren'),
				width : 30
			}, {
				field : 'groupId',
				title : '关联分组',
				width : 30,
				hidden : true
			}
	// , {
	// field : 'opr',
	// title : $.i18n.prop('cst.obj.opr'),
	// width : 30,
	// formatter : function(v, r) {
	// return "<a href='javascript:CST.del(" + r.id + ","
	// + r.hasChildren + ")'>删除</a>";
	// }
	// }
	] ];
});
