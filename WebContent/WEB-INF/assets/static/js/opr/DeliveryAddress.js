$(function() {
			$.BaseCRUD({
						listURL : "deliveryAddress?goto=list",
						addURL : "deliveryAddress?goto=save",
						updateURL : "deliveryAddress?goto=update",
						delURL : "deliveryAddress?goto=del",
						titleAddF : $.i18n.prop('daddr.title.add'),
						titleUpdateF : $.i18n.prop('daddr.title.update')
					});
			init();
		});

var init = function() {
	$("#cc").combobox('reload', 'customerInfo?goto=combobox');
}
var backfill = function() {
	$("#cc").combobox('select', AAW.selectedRow().customer.id);
}