$(function() {
			$.BaseCRUD({
						listURL : "productComponent?goto=list",
						addURL : "productComponent?goto=save",
						updateURL : "productComponent?goto=update",
						delURL : "productComponent?goto=del",
						titleAddF : $.i18n.prop('component.title.add'),
						titleUpdateF : $.i18n.prop('component.title.update')
					});
		});