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
	var search4More = function(d) {
		if (!d) {
			$("#searchForm").form("clear");
		}
		$("#tt").datagrid({
			url : "franchises?goto=map4EUI",
			queryParams : AAW.queryParams($("#searchForm"))
		});
	}
</script>

<div class="serachDiv">
	<form id="searchForm">
		<div>
			<label>级别:</label><input type="text" name="level"
				class="easyui-combobox"
				data-options="valueField:'id',textField:'name',url:'franchises?goto=levelList'" />
		</div>
		<div>
			<label>名称:</label><input type="text" name="fname"
				class="easyui-textbox" placeholder="支持模糊查询" />
		</div>
	</form>
	<div style="text-align: center;">
		<a class="easyui-linkbutton" onclick="search4More(true);"
			style="height: 24px;">查询</a> <a class="easyui-linkbutton"
			onclick="search4More(false);"
			style="height: 24px; margin-left: 20px;">全部</a>
	</div>
</div>