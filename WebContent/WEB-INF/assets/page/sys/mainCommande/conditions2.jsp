<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.serachDiv {
	padding: 10px;
}

.serachDiv div {
	line-height: 30px;
	clear: both;
}

.serachDiv div label {
	display: block;
	float: left;
	width: 80px;
	text-align: right;
}
</style>
<script type="text/javascript">
	function opt(id, text) {
		var obj = {
			id : id,
			text : text
		};
		return obj;
	}

	var symbol = [ {
		id : '>',
		text : '>'
	}, {
		id : '=',
		text : '='
	}, {
		id : '<',
		text : '<'
	} ];
	var srh = function(d) {
		if (d) {
			$("#tt").datagrid({
				url : "mainCommande?goto=listMDC2",
				queryParams : AAW.queryParams($("#searchForm"))
			});
		} else {
			$("#searchForm").form("clear");
			$("#molos_st1").combobox({
				disabled : true
			});
			$("#molos_symbol").combobox({
				disabled : true
			});
			$("#tt").datagrid({
				url : "mainCommande?goto=listMDC2",
				queryParams : AAW.queryParams($("#searchForm"))
			});
		}
	}

	function molosProc(r) {
		$.post("productionLine?goto=checkProcCout", {
			id : r.id
		}, function(rs) {
			if (rs > 0) {
				$("#molos_proc_count").text(rs);
				var data = new Array();
				data.push(opt(0, 0));
				for (var i = 1; i < rs + 1; i++) {
					data.push(opt(i, i));
				}
				$("#molos_symbol").combobox({
					disabled : false
				});
				$("#molos_st1").combobox({
					disabled : false
				}).combobox("loadData", data);
			}
		});
	}
</script>
<div class="serachDiv">
	<form id="searchForm">
		<div>
			<label>生产线:</label><input type="text" name="lineID"
				class="easyui-combobox"
				data-options="url:'productionLine?goto=combobox',onSelect:molosProc" />
		</div>
		<div>
			<label>第三方ID:</label><input type="text" name="third"
				class="easyui-textbox" placeholder="支持模糊查询" />
		</div>
		<div>
			<label>顾客姓名:</label><input type="text" name="name"
				class="easyui-textbox" />
		</div>
		<div>
			<label>生产进度:</label> <input style="width: 50px;" type="text"
				name=symbol class="easyui-combobox" data-options="data:symbol"
				id="molos_symbol" /><input type="text" name="st1"
				style="width: 50px;" class="easyui-combobox"
				data-options="disabled:true" id="molos_st1" />/ <span
				id="molos_proc_count">0</span>
		</div>
		<div style="text-align: center;">
			<a class="easyui-linkbutton" onclick="srh(true);"
				style="height: 24px;">查询</a> <a class="easyui-linkbutton"
				onclick="srh(false);" style="height: 24px; margin-left: 20px;">全部</a>
		</div>
	</form>
</div>