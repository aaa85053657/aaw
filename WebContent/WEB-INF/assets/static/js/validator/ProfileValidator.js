$(function() {
	$.extend($.fn.validatebox.defaults.rules, {
		codeID : {
			validator : function(value) {
				return AAW.async("profile?goto=unique", "codeId", value) == "1";
			},
			message : $.i18n.prop('profile.vail.codeID')
		},
		name : {
			validator : function(value) {
				return AAW.async("profile?goto=unique", "name", value) == "1";
			},
			message : $.i18n.prop('profile.vail.name')
		},
		vMax : {
			validator : function(value) {
				var v1 = $('#vMin').numberbox('getValue');
				var v2 = $('#vDefault').numberbox('getValue');
				if ((v1 && value < parseInt(v1))
						|| (v1 && value < parseInt(v2))) {
					return false
				}
				return true;
			},
			message : $.i18n.prop('profile.vail.vMax')
		},
		vMin : {
			validator : function(value) {
				var v1 = $('#vMax').numberbox('getValue');
				var v2 = $('#vDefault').numberbox('getValue');
				if ((v1 && value > parseInt(v1))
						|| (v1 && value > parseInt(v2))) {
					return false
				}
				return true;
			},
			message : $.i18n.prop('profile.vail.vMin')
		},
		vDefault : {
			validator : function(value) {
				var v1 = $('#vMax').numberbox('getValue');
				var v2 = $('#vMin').numberbox('getValue');
				if ((v1 && value > parseInt(v1))
						|| (v1 && value < parseInt(v2))) {
					return false
				}
				return true;
			},
			message : $.i18n.prop('profile.vail.vDefault')
		}
	});
});