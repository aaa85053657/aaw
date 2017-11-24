$(function() {
	$.BaseCRUD({
		listURL : "commandeStatusGroup?goto=list",
		addURL : "commandeStatusGroup?goto=save",
		updateURL : "commandeStatusGroup?goto=update",
		delURL : "commandeStatusGroup?goto=del",
		titleAddF : $.i18n.prop('csg.title.add'),
		titleUpdateF : $.i18n.prop('csg.title.update')
	});
	init();
});

var init = function() {
	$("#tt").datagrid(
			{
				onDblClickRow : function() {
					var row = $("#tt").datagrid('getSelected');
					window.location.href = "commandeStatusTemp?goto=view&id="
							+ row.id;
				}
			});
}
