$(function() {
			$.BaseCRUD({
						listURL : "profileType?goto=list",
						addURL : "profileType?goto=save",
						updateURL : "profileType?goto=update",
						delURL : "profileType?goto=del",
						titleAddF : $.i18n.prop('profileType.title.add'),
						titleUpdateF : $.i18n.prop('profileType.title.update')
					});
		});