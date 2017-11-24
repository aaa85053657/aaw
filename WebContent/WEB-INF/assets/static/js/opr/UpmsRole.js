var url;
var roleID;
$(function() {
			init();
		});
var init = function() {
	$("#roles").datagrid({
				url : "upmsRole?goto=roleList",
				fitColumns : true,
				columns : dataArr,
				toolbar : "#toolbar",
				rowStyler : function(index, row) {
					if (row.name == "SuperAdministrator") {
						return 'display:none;';
					}
				}
			});
}

var showAss = function() {
	var row = $("#roles").datagrid("getSelected");
	$("#cc").panel({
				href : "upmsRole?goto=view2&id=" + row.id
			});
	this.roleID = row.id
}

var test = function() {
	MyOp.checkback();
}

var MyOp = {
	show : function() {
		$("#fm").form('clear');
		$("#dlg").dialog('center').dialog('open').dialog('setTitle', "添加角色");
		this.url = "upmsRole?goto=save";
	},
	update : function() {
		var row = $("#roles").datagrid('getSelected');
		if (row) {
			$("#fm").form('load', row);
			$("#dlg").dialog('center').dialog('open')
					.dialog('setTitle', "修改角色");
			this.url = "upmsRole?goto=update";
		} else {
			$.messager.alert('提示', '请选择需要修改的数据!', 'warning');
		}
	},
	save : function() {
		$("#fm").form('submit', {
					url : this.url,
					onSubmit : function() {
						return $(this).form('validate');
					},
					success : function(r) {
						var data = eval('(' + r + ')');
						$('#dlg').dialog('close');
						var msg = null;
						if (data.suc == 1) {
							$('#roles').datagrid('reload');
							msg = "<span class='tip_suc'>" + data.msg
									+ "</span>";
						} else {
							msg = "<span class='tip_error'>" + data.msg
									+ "</span>";
						}
						$.messager.show({
									title : '操作结果',
									msg : msg
								});
					}
				});
	},
	bind : function() {
		var nodes = $("#treeg2").tree("getChecked",
				['checked', 'indeterminate']);
		var ar = new Array();
		if (nodes.length > 0) {
			for (var i = 0; i < nodes.length; i++) {
				ar.push(nodes[i].id);
			}
			$.post("upmsRole?goto=bind&id=" + roleID, {
						rids : ar
					}, function(data) {
						if (data.suc == 1) {
							$('#tt').datagrid('reload');
							msg = "<span class='tip_suc'>" + data.msg
									+ "</span>";
						} else {
							msg = "<span class='tip_error'>" + data.msg
									+ "</span>";
						}
						$.messager.show({
									title : '操作结果',
									msg : msg
								});
					}, "json");
		} else {
			ar.push(0);
			$.post("upmsRole?goto=bind&id=" + roleID, {
						rids : ar
					}, function(data) {
						if (data.suc == 1) {
							$('#tt').datagrid('reload');
							msg = "<span class='tip_suc'>" + data.msg
									+ "</span>";
						} else {
							msg = "<span class='tip_error'>" + data.msg
									+ "</span>";
						}
						$.messager.show({
									title : '操作结果',
									msg : msg
								});
					}, "json");
		}
	},
	checkback : function() {
		$.post("upmsRole?goto=findBind&id=" + roleID, function(data) {
					$(data).each(function(i, obj) {
								console.log(obj);
								var n = $("#treeg2").tree('find', obj);
								console.log(n);
								if (n) {
									$("#treeg2").tree('check', n.target);
								}
							});
				});
	}
}
