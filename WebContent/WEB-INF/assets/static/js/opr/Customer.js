$(function() {
			$.BaseCRUD({
						listURL : "customerInfo?goto=list",
						addURL : "customerInfo?goto=save",
						updateURL : "customerInfo?goto=update",
						delURL : "customerInfo?goto=del",
						titleAddF : $.i18n.prop('customer.title.add'),
						titleUpdateF : $.i18n.prop('customer.title.update')
					});
			init();
		});

var init = function() {
	$('#csex').combobox({
				data : sexCombobox
			});
}
var backfill = function() {
	var row = AAW.selectedRow();
	$('#csex').combobox('select', row.sex);
	$("#ccountry").combobox('select', row.country.id);
}
var ccLoad = function() {
	$("#ccountry").combobox('reload', 'country?goto=combobox');
}