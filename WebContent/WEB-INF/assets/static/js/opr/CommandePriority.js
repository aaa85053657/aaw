$(function() {
			$.BaseCRUD({
						listURL : "commandePriority?goto=list",
						addURL : "commandePriority?goto=save",
						updateURL : "commandePriority?goto=update",
						delURL : "commandePriority?goto=del",
						titleAddF : $.i18n.prop('priority.title.add'),
						titleUpdateF : $.i18n.prop('priority.title.update')
					});
		});