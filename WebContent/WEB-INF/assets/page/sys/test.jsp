<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='attribute.page.title' /></title>
<jsp:include page="../base/head2.jsp" />
<script type="text/javascript"
	src="static/js/common/datagrid-detailview.js"></script>
<!-- <script type="text/javascript" src="static/js/col/AttributeCol.js"></script>
<script type="text/javascript"
	src="static/js/validator/AttributeValidator.js"></script>
<script type="text/javascript" src="static/js/opr/Attribute.js"></script> -->
<script>
	var data = [ {
		"productid" : "FI-SW-01",
		"productname" : "Koi",
		"unitcost" : 10.00,
		"status" : "P",
		"listprice" : 36.50,
		"attr1" : "Large",
		"itemid" : "EST-1"
	}, {
		"productid" : "K9-DL-01",
		"productname" : "Dalmation",
		"unitcost" : 12.00,
		"status" : "P",
		"listprice" : 18.50,
		"attr1" : "Spotted Adult Female",
		"itemid" : "EST-10"
	}, {
		"productid" : "RP-SN-01",
		"productname" : "Rattlesnake",
		"unitcost" : 12.00,
		"status" : "P",
		"listprice" : 38.50,
		"attr1" : "Venomless",
		"itemid" : "EST-11"
	}, {
		"productid" : "RP-SN-01",
		"productname" : "Rattlesnake",
		"unitcost" : 12.00,
		"status" : "P",
		"listprice" : 26.50,
		"attr1" : "Rattleless",
		"itemid" : "EST-12"
	}, {
		"productid" : "RP-LI-02",
		"productname" : "Iguana",
		"unitcost" : 12.00,
		"status" : "P",
		"listprice" : 35.50,
		"attr1" : "Green Adult",
		"itemid" : "EST-13"
	}, {
		"productid" : "FL-DSH-01",
		"productname" : "Manx",
		"unitcost" : 12.00,
		"status" : "N",
		"listprice" : 158.50,
		"attr1" : "Tailless",
		"itemid" : "EST-14"
	}, {
		"productid" : "FL-DSH-01",
		"productname" : "Manx",
		"unitcost" : 12.00,
		"status" : "P",
		"listprice" : 83.50,
		"attr1" : "With tail",
		"itemid" : "EST-15"
	}, {
		"productid" : "FL-DLH-02",
		"productname" : "Persian",
		"unitcost" : 12.00,
		"status" : "P",
		"listprice" : 23.50,
		"attr1" : "Adult Female",
		"itemid" : "EST-16"
	}, {
		"productid" : "FL-DLH-02",
		"productname" : "Persian",
		"unitcost" : 12.00,
		"status" : "N",
		"listprice" : 89.50,
		"attr1" : "Adult Male",
		"itemid" : "EST-17"
	}, {
		"productid" : "AV-CB-01",
		"productname" : "Amazon Parrot",
		"unitcost" : 92.00,
		"status" : "N",
		"listprice" : 63.50,
		"attr1" : "Adult Male",
		"itemid" : "EST-18"
	} ];
	$(function() {
		var dg = $('#dg').datagrid({
			filterBtnIconCls : 'icon-filter'
		});
		dg.datagrid('enableFilter', [ {
			field : 'listprice',
			type : 'numberbox',
			options : {
				precision : 1
			},
			op : [ 'equal', 'notequal', 'less', 'greater' ]
		}, {
			field : 'unitcost',
			type : 'numberbox',
			options : {
				precision : 1
			},
			op : [ 'equal', 'notequal', 'less', 'greater' ]
		}, {
			field : 'status',
			type : 'combobox',
			options : {
				panelHeight : 'auto',
				data : [ {
					value : '',
					text : 'All'
				}, {
					value : 'P',
					text : 'P'
				}, {
					value : 'N',
					text : 'N'
				} ],
				onChange : function(value) {
					if (value == '') {
						dg.datagrid('removeFilterRule', 'status');
					} else {
						dg.datagrid('addFilterRule', {
							field : 'status',
							op : 'equal',
							value : value
						});
					}
					dg.datagrid('doFilter');
				}
			}
		} ]);
	});
</script>
</head>
<body class="easyui-layout">
	<div data-options='region:"center",fit:true,border:false'>

		<table title="用户筛选" id="dg" style="width: 700px; height: 250px"
			data-options="singleSelect:true,collapsible:true,url:'customerInfo?goto=list2',method:'get'">
			<thead>
				<tr>
					<th data-options="field:'firstName',width:80">ID</th>
					<!-- 	<th data-options="field:'productid',width:100">Product</th>
				<th data-options="field:'listprice',width:80,align:'right'">List
					Price</th>
				<th data-options="field:'unitcost',width:80,align:'right'">Unit
					Cost</th>
				<th data-options="field:'attr1',width:250">Attribute</th>
				<th data-options="field:'status',width:60,align:'center'">Status</th> -->
				</tr>
			</thead>
		</table>


		<!--  <table id="dg" title="DataGrid" style="width: 700px; height: 250px"
			data-options="
				url:'customerInfo?goto=list2
			">
			 <thead>
				<tr>
					<th data-options="field:'itemid',width:80">Item ID</th>
					<th data-options="field:'productid',width:100">Product</th>
					<th data-options="field:'listprice',width:80,align:'right'">List
						Price</th>
					<th data-options="field:'unitcost',width:80,align:'right'">Unit
						Cost</th>
					<th data-options="field:'attr1',width:250">Attribute</th>
					<th data-options="field:'status',width:60,align:'center'">Status</th>
				</tr>
			</thead> 
		<thead>
			<tr>
				<th data-options="field:'id',width:80">ID</th>
				 	<th data-options="field:'productid',width:100">Product</th>
				<th data-options="field:'listprice',width:80,align:'right'">List
					Price</th>
				<th data-options="field:'unitcost',width:80,align:'right'">Unit
					Cost</th>
				<th data-options="field:'attr1',width:250">Attribute</th>
				<th data-options="field:'status',width:60,align:'center'">Status</th> 
			</tr>
		</thead>
		</table>
		-->
	</div>
</body>
</html>
