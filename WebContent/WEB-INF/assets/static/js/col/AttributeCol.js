$(function() {
	dataArr = [ [ {
		field : 'id',
		title : $.i18n.prop('attribute.obj.id'),
		width : 30,
		hidden : true
	}, {
		field : 'codeId',
		title : $.i18n.prop('attribute.obj.codeID'),
		width : 30
	}, {
		field : 'name',
		title : $.i18n.prop('attribute.obj.name'),
		width : 30
	}, {
		field : 'component',
		title : $.i18n.prop('attribute.obj.component'),
		width : 30,
		formatter : function(v) {
			if (v) {
				return v.name;
			}
			return "";
		}
	}, {
		field : 'path',
		title : $.i18n.prop('attribute.obj.path'),
		width : 30
	}, {
		field : 'comments',
		title : $.i18n.prop('attribute.obj.comments'),
		width : 30
	} ] ];
	AttributeValueArr = [ [ {
		field : 'id',
		title : $.i18n.prop('attrVal.obj.id'),
		width : 30,
		hidden : true
	}, {
		field : 'codeId',
		title : $.i18n.prop('attrVal.obj.codeID'),
		width : 30
	}, {
		field : 'name',
		title : $.i18n.prop('attrVal.obj.name'),
		width : 30
	}, {
		field : 'attribute',
		title : $.i18n.prop('attrVal.obj.type'),
		width : 30,
		formatter : function(r) {
			if (r) {
				return r.name;
			}
			return "";
		}
	}, {
		field : 'samplepath',
		title : $.i18n.prop('attrVal.obj.samplepath'),
		width : 30
	}, {
		field : 'pixel',
		title : $.i18n.prop('attrVal.obj.pixel'),
		width : 30
	}, {
		field : 'comments',
		title : $.i18n.prop('attrVal.obj.comments'),
		width : 30
	}
	// , {
	// field : 'opr',
	// title : $.i18n.prop('attrVal.obj.opr'),
	// width : 30,
	// formatter : function(v, r, index) {
	// return "<a href='javascript:delCfg(" + v
	// + ")'>删除</a>";
	// // return "<a href=\"javascript:delElement('"
	// // + v
	// // + "')\" class=\"easyui-linkbutton\"
	// // data-options=\"plain:true\" >删除</a>";
	// }
	// }
	] ];

});
