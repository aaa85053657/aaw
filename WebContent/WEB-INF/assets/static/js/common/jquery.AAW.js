var AAW = {
	url : "",
	initGrid : function(_url, arr) {
		$("#tt").datagrid({
			url : _url,
			columns : arr,
			toolbar : "#toolbar"
		});
	},
	addF : function(title) {
		$('#dlg').dialog('open').dialog('setTitle', title);
		$('#fm').form('clear');
		if ($("#validator_id")) {
			$("#validator_id").val(0);
		}
	},
	updateF : function(_url, title) {
		var r = this.selectedRow();
		if (r) {
			$('#dlg').dialog('open').dialog('setTitle', title);
			$('#fm').form('clear').form('load', r);
			this.url = _url + "&id=" + r.id;
			if ($("#validator_id")) {
				$("#validator_id").val(r.id);
			}

		} else {
			$.messager.alert($.i18n.prop('crud.dialog.tip.title'), $.i18n
					.prop('crud.dialog.tip.update'), 'warning');
		}
	},
	save : function() {
		// $('#fm').form('submit', {
		// url : this.url,
		// onSubmit : function() {
		// return $(this).form('validate');
		// },
		// success : this.result
		// });

		if ($('#fm').form('validate')) {
			$.post(this.url, $("#fm").serialize(), this.result);
		}
	},
	del : function(_url) {
		var r = this.selectedRow();
		if (r) {
			$.messager.confirm($.i18n.prop('crud.dialog.tip.title'), $.i18n
					.prop('crud.dialog.tip.del'), function(r1) {
				if (r1) {
					$.post(_url, {
						id : r.id
					}, function(m) {
						AAW.tip(m);
					}, 'json');
				}
			});
		} else {
			$.messager.alert($.i18n.prop('crud.dialog.tip.title'), $.i18n
					.prop('crud.dialog.tip.del.warning'), 'warning');
		}
	},
	queryParams : function(obj) {// 多条件查询form表单处理
		var arr = $(obj).serializeArray();
		var str = "";
		for ( var i in arr) {
			var s1 = "";
			if (arr[i].value == null || arr[i].value == "") {
				s1 = '""';
			} else {
				s1 = '"' + arr[i].value + '"';
			}
			var s = '"' + arr[i].name + '"' + ":" + s1;
			str += s;
			if (i != arr.length - 1) {
				str += ",";
			}
		}
		str = "{" + str + "}";
		return $.parseJSON(str);
	},
	empty : function(v) {// 判定是否为空
		switch (typeof v) {
		case 'undefined':
			return true;
		case 'string':
			if (AAW.trim(v).length == 0)
				return true;
			break;
		case 'boolean':
			if (!v)
				return true;
			break;
		case 'number':
			if (0 === v)
				return true;
			break;
		case 'object':
			if (null === v)
				return true;
			if (undefined !== v.length && v.length == 0)
				return true;
			for ( var k in v) {
				return false;
			}
			return true;
			break;
		}
		return false;
	},
	trim : function(str) { // 删除左右两端的空格
		return str.replace(/(^\s*)|(\s*$)/g, "");
	},
	ltrim : function(str) { // 删除左边的空格
		return str.replace(/(^\s*)/g, "");
	},
	rtrim : function(str) { // 删除右边的空格
		return str.replace(/(\s*$)/g, "");
	},
	selectedRow : function() {
		return $('#tt').datagrid('getSelected');
	},
	result : function(r) {
		// var data = eval('(' + r + ')');
		AAW.tip(r);
	},
	tip : function(data) {
		$('#dlg').dialog('close');
		var msg = null;
		if (data.suc == 1) {
			$('#tt').datagrid('reload');
			msg = "<span class='tip_suc'>" + data.msg + "</span>";
		} else {
			msg = "<span class='tip_error'>" + data.msg + "</span>";
		}
		$.messager.show({
			title : $.i18n.prop('crud.dialog.tip.oprrs'),
			msg : msg
		});
	},
	async : function(_url, propName, propVal) {
		return $.ajax({
			async : false,
			cache : false,
			type : 'post',
			url : _url,
			data : {
				'propName' : propName,
				'propVal' : propVal,
				'id' : $("#validator_id").val()
			}
		}).responseText;
	},
	textBoxChange : function(id, b) {// b为true则textbox变为只读并清空内容，否则为可编辑状态
		if (b) {
			$("#" + id).textbox("clear");
		}
		$("#" + id).textbox('readonly', b);
	},
	saveTip : function(r, dlgID, ttID, isTree) {
		var data = eval('(' + r + ')');
		$('#' + dlgID).dialog('close');
		var msg = null;
		if (data.suc == 1) {
			if (isTree) {
				$('#' + ttID).treegrid('reload');
			} else {
				$('#' + ttID).datagrid('reload');
			}
			msg = "<span class='tip_suc'>" + data.msg + "</span>";
		} else {
			msg = "<span class='tip_error'>" + data.msg + "</span>";
		}
		$.messager.show({
			title : $.i18n.prop('crud.dialog.tip.oprrs'),
			msg : msg
		});
	}
};

(function($, A) {
	$.extend({
		CRUD : function(options) {
			var defaults = {
				colums : [ [] ],
				toolbar : "#toolbar",
				listURL : "",
				addURL : "",
				updateURL : "",
				delURL : "",
				addBtn : null,
				updateBtn : null,
				delBtn : null,
				saveBtn : null,
				titleAddF : $.i18n.prop('crud.form.title.add'),
				titleUpdateF : $.i18n.prop('crud.form.title.update')
			};
			var params = $.extend(defaults, options);
			var check = function() {// 功能完整性检查
				if (!params.listURL) {
					console.log("没有初始化数据列表显示的URL地址！将无法加载数据！");
				}
				if (!params.addURL) {
					console.log("没有初始化数据保存的URL地址！将无法保存数据！");
				}
				if (!params.updateURL) {
					console.log("没有初始化修改数据的URL地址！将无法进行数据修改操作 ！");
				}
				if (!params.delURL) {
					console.log("没有初始化数据删除URL地址！");
				}
				if (!params.saveBtn && (params.saveBtn || params.updateBtn)) {
					console.log("没有定义保存数据的按钮对象！");
				}
			};

			if (params.addBtn) {
				$(params.addBtn).click(function() {// 新增按钮功能绑定
					A.url = params.addURL;
					A.addF(params.titleAddF);
				});
			}
			if (params.updateBtn) {
				$(params.updateBtn).click(function() {// 修改按钮功能绑定
					A.updateF(params.updateURL, params.titleUpdateF);
				});
			}
			if (params.saveBtn) {
				$(params.saveBtn).click(function() {// 保存按钮功能绑定
					A.save();
				});
			}

			if (params.delBtn) {
				$(params.delBtn).click(function() {// 删除按钮功能绑定
					A.del(params.delURL);
				});
			}
			var initGrid = function() {// 初始化数据表格
				$("#tt").datagrid({
					url : params.listURL,
					columns : params.colums,
					toolbar : "#toolbar"
				});
			};
			return this.each(function() {
				initGrid();
			});
		},
		BaseCRUD : function(options) {
			var defaults = {
				toolbar : "#toolbar",
				listURL : "",
				addURL : "",
				updateURL : "",
				delURL : "",
				titleAddF : $.i18n.prop('crud.form.title.add'),
				titleUpdateF : $.i18n.prop('crud.form.title.update')
			};
			var params = $.extend(defaults, options);
			var check = function() {// 功能完整性检查
				if (!params.listURL) {
					console.log("没有初始化数据列表显示的URL地址！将无法加载数据！");
				}
				if (!params.addURL) {
					console.log("没有初始化数据保存的URL地址！将无法保存数据！");
				}
				if (!params.updateURL) {
					console.log("没有初始化修改数据的URL地址！将无法进行数据修改操作 ！");
				}
				if (!params.delURL) {
					console.log("没有初始化数据删除URL地址！");
				}
				if (!params.saveBtn && (params.saveBtn || params.updateBtn)) {
					console.log("没有定义保存数据的按钮对象！");
				}
			};
			$("#btnAdd").click(function() {// 新增按钮功能绑定
				A.url = params.addURL;
				A.addF(params.titleAddF);
			});
			$("#btnUpdate").click(function() {// 修改按钮功能绑定
				A.updateF(params.updateURL, params.titleUpdateF);
			});
			$("#btnSave").click(function() {// 保存按钮功能绑定
				A.save();
			});

			$("#btnDel").click(function() {// 删除按钮功能绑定
				A.del(params.delURL);
			});

			// 初始化数据表格
			$("#tt").datagrid({
				url : params.listURL,
				columns : dataArr,
				toolbar : "#toolbar"
			});
		}
	});
})(jQuery, AAW);
$(function() {
	isSMCombobox = [ {
		id : '1',
		text : $.i18n.prop('cst.sendM.y')
	}, {
		id : '0',
		text : $.i18n.prop('cst.sendM.n')
	} ];
	isSMFormat = function(r) {
		return cbbV(r, isSMCombobox);
	};
	posCombobox = [ {
		id : '1',
		text : $.i18n.prop('global.pos.left')
	}, {
		id : '2',
		text : $.i18n.prop('global.pos.right')
	}, {
		id : '3',
		text : $.i18n.prop('global.pos.all')
	} ];
	posFormat = function(value) {
		return cbbV(value, posCombobox);
	};
	sexCombobox = [ {
		id : '1',
		text : $.i18n.prop('global.sex.male')
	}, {
		id : '2',
		text : $.i18n.prop('global.sex.female')
	} ];
	sexFormat = function(value) {
		return cbbV(value, sexCombobox);
	};
	incumbentCombobox = [ {
		id : '1',
		text : $.i18n.prop('global.incumbent.is')
	}, {
		id : '2',
		text : $.i18n.prop('global.incumbent.isnot')
	} ];
	incumbentFormat = function(value) {
		return cbbV(value, incumbentCombobox);
	};
	staffCombobox = [ {
		id : '1',
		text : $.i18n.prop('global.staff.is')
	}, {
		id : '2',
		text : $.i18n.prop('global.staff.isnot')
	} ];
	staffFormat = function(value) {
		return cbbV(value, staffCombobox);
	};
	stCbb = [ {
		"id" : 0,
		text : $.i18n.prop('cst.st.processing')
	}, {
		"id" : 1,
		text : $.i18n.prop('cst.st.suc')
	}, {
		"id" : 2,
		text : $.i18n.prop('cst.st.fail')
	} ];
});

var cbbV = function(value, cbArr) {
	var v = "";
	$.each(cbArr, function(index, r) {
		if (r.id == value) {
			v = r.text;
			return false;
		}
	});
	return v;
}
var cbbV2 = function(value, cbArr) {
	var v = "";
	$.each(cbArr, function(index, r) {
		if (r.id == value) {
			v = r.status;
			return false;
		}
	});
	return v;
}