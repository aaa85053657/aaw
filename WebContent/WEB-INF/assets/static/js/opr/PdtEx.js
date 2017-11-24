var PdtEx = {
	init : function() {
		this.initGrid();
	},
	initGrid : function() {
		var $this = this;
		$.BaseCRUD({
			listURL : "ProductionException?act=list",
			addURL : "ProductionException?act=save",
			updateURL : "ProductionException?act=update",
			delURL : "ProductionException?act=del",
			titleAddF : $.i18n.prop('PdtEx.title.add'),
			titleUpdateF : $.i18n.prop('PdtEx.title.update')
		});
	}
}

$(function() {
	dataArr = [ [ {
		field : 'id',
		title : $.i18n.prop('PdtEx.obj.id'),
		width : 30,
		hidden : true
	}, {
		field : 'codeId',
		title : $.i18n.prop('PdtEx.obj.codeID'),
		width : 30
	}, {
		field : 'name',
		title : $.i18n.prop('PdtEx.obj.name'),
		width : 30
	}, {
		field : 'comments',
		title : $.i18n.prop('PdtEx.obj.comments'),
		width : 30
	} ] ];
	valRules = {
		codeID : {
			validator : function(value) {
				return AAW.async("ProductionException?act=unique", "codeId",
						value) == "1";
			},
			message : $.i18n.prop('PdtEx.vail.codeID')
		},
		name : {
			validator : function(value) {
				return AAW.async("ProductionException?act=unique", "name",
						value) == "1";
			},
			message : $.i18n.prop('PdtEx.vail.name')
		}
	}
	PdtEx.init();
	$.extend($.fn.validatebox.defaults.rules, valRules);
})
