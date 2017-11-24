$(function() {
			$.BaseCRUD({
						listURL : "profile?goto=list",
						addURL : "profile?goto=save",
						updateURL : "profile?goto=update",
						delURL : "profile?goto=del",
						titleAddF : $.i18n.prop('profile.title.add'),
						titleUpdateF : $.i18n.prop('profile.title.update')
					});
		});
var backfill = function() {
	$("#cc").combobox('select', AAW.selectedRow().type.id);
}
var ccLoad = function() {
	$("#cc").combobox('reload', 'profileType?goto=combobox');
}