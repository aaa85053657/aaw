$(function() {
			$.BaseCRUD({
						listURL : "country?goto=list",
						addURL : "country?goto=save",
						updateURL : "country?goto=update",
						delURL : "country?goto=del",
						titleAddF : $.i18n.prop('country.title.add'),
						titleUpdateF : $.i18n.prop('country.title.update')
					});
		});