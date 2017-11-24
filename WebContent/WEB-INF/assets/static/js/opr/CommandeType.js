$(function() {
	$.BaseCRUD({
		listURL : "commandeType?goto=list",
		addURL : "commandeType?goto=save",
		updateURL : "commandeType?goto=update",
		delURL : "commandeType?goto=del",
		titleAddF : $.i18n.prop('commandeType.title.add'),
		titleUpdateF : $.i18n.prop('commandeType.title.update')
	});
});

var backfill = function() {
	var r = AAW.selectedRow();
	if (r != null && r.csg != null) {
		$("#csgId").combobox('select', r.csg.id);
	}
}