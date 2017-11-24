$(function() {
	dataArr = [ [ {
		field : 'id',
		title : $.i18n.prop('model.obj.id'),
		width : 30,
		hidden : true
	}, {
		field : 'codeId',
		title : $.i18n.prop('model.obj.codeID'),
		width : 30
	}, {
		field : 'line',
		title : $.i18n.prop('model.obj.line'),
		width : 30,
		formatter : function(v) {
			if (v) {
				return v.name;
			}
			return "";
		}
	}, {
		field : 'name',
		title : $.i18n.prop('model.obj.name'),
		width : 30
	}, {
		field : 'comments',
		title : $.i18n.prop('model.obj.comments'),
		width : 30
	}, {
		field : 'timeCreation',
		title : $.i18n.prop('model.obj.timeCreation'),
		width : 30
	}, {
		field : 'timeModification',
		title : $.i18n.prop('model.obj.timeModification'),
		width : 30
	}, {
		field : 'timeDelete',
		title : $.i18n.prop('model.obj.timeDelete'),
		width : 30
	} ] ];

	cfgArr = [ [
			{
				field : 'position',
				title : $.i18n.prop('model.mcd.position'),
				width : 30,
				hidden : true
			},
			{
				field : 'componentName',
				title : $.i18n.prop('model.mcd.componentName'),
				width : 30
			},
			{
				field : 'attributeName',
				title : $.i18n.prop('model.mcd.attributeName'),
				width : 30
			},
			{
				field : 'elementName',
				title : $.i18n.prop('model.mcd.elementName'),
				width : 30,
				hidden : true
			},
			{
				field : 'eLeft',
				title : $.i18n.prop('model.mcd.eLeft'),
				width : 30,
				formatter : function(v, r) {
					if (r.position == 1 || r.position == 3) {
						return r.elementName;
					}
					return "";
				}
			},
			{
				field : 'eRight',
				title : $.i18n.prop('model.mcd.eRight'),
				width : 30,
				formatter : function(v, r) {
					if (r.position == 2 || r.position == 3) {
						return r.elementName;
					}
					return "";
				}
			},
			{
				field : 'cfgID',
				title : $.i18n.prop('model.mcd.cfgID'),
				width : 30,
				formatter : function(v, r) {
					return "<a href='javascript:delCfg(" + v + ")'>删除单行配置</a>";
				}
			},
			{
				field : 'componentID',
				title : $.i18n.prop('model.mcd.componentID'),
				width : 30,
				formatter : function(v, r) {
					return "<a href='javascript:delCfg2(" + r.modelID + "," + v
							+ ")'>删除整个组件</a>";
				}
			}, {
				field : 'elementID',
				title : $.i18n.prop('model.mcd.elementID'),
				width : 30,
				hidden : true
			}, {
				field : 'attributeID',
				title : $.i18n.prop('model.mcd.attributeID'),
				width : 30,
				hidden : true
			}, {
				field : 'modelID',
				title : $.i18n.prop('model.mcd.modelID'),
				width : 30,
				hidden : true
			} ] ];
});
