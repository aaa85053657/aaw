<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='mCommande.page.title' /></title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript"
	src="static/js/common/datagrid-detailview.js"></script>
<script type="text/javascript" src="static/js/col/MainCommandeCol.js"></script>
<script type="text/javascript"
	src="static/js/validator/MainCommandeValidator.js"></script>
<script type="text/javascript" src="static/js/opr/MainCommande2.js"></script>
<script type="text/javascript"
	src="static/js/common/easyui.mergeCell.js"></script>
<style type="text/css">
.fitem {
	margin-bottom: 10px;
}

.rotate {
	width: 60px;
	height: 300px;
	transform: rotate(-90deg);
}

.col1 {
	width: 63px;
	height: 20px;
	border: solid 0.5px black;
	font-size: 8px;
	text-align: center;
}

.col2 {
	width: 71px;
	height: 20px;
	border: solid 0.5px black;
	font-size: 8px;
	text-align: center;
}

.col3 {
	width: 96px;
	height: 20px;
	border: solid 0.5px black;
	font-size: 8px;
	text-align: center;
}
</style>
</head>
<body>
	<div id="c_main" class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true" title="高级搜索"
			style="width: 335px;">
			<jsp:include page="conditions2.jsp"></jsp:include>
		</div>
		<div data-options="region:'center',iconCls:'icon-ok'">
			<table id="tt"></table>
			<div id="toolbar">
				<table>
					<tr>
						<td><a href="javascript:disableCC();void(0);"
							class="easyui-linkbutton"
							data-options="iconCls:'icon-add',plain:true" id="btnAdd2"><spr:message
									code='crud.btn.add' /></a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						<td><a href="javascript:backfill();void(0);"
							class="easyui-linkbutton" id="btnUpdate2"
							data-options="iconCls:'icon-edit',plain:true"><spr:message
									code='crud.btn.update' /></a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						<td><a href="javascript:void(0);" class="easyui-linkbutton"
							id="btnDel" data-options="iconCls:'icon-remove',plain:true"><spr:message
									code='crud.btn.del' /></a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						<td><a id="cfgSd" href="javascript:;" onclick="BtnCfg();"
							class="easyui-linkbutton"
							data-options="iconCls:'icon-add',plain:true,disabled: true"><spr:message
									code='mCommande.btn.cfg' /></a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						<td><a href="javascript:statusOpr();"
							class="easyui-linkbutton"
							data-options="iconCls:'icon-add',plain:true"><spr:message
									code='mCommande.btn.statusOpr' /></a></td>
					</tr>
				</table>
			</div>
		</div>
		<div>
			<div>
				<div id="dlg" class="easyui-dialog"
					style="width: 450px; height: 400px; padding: 10px 20px"
					data-options="buttons:'#dbtns'">
					<form id="fm" method="post" novalidate>
						<!-- 				<input name="codeId" type="hidden" /> -->
						<!-- 				<div class="fitem" style="display: none;"> -->
						<%-- 					<label><spr:message code='mCommande.obj.codeID' />:</label> <input --%>
						<!-- 						name="codeId" class="easyui-textbox" -->
						<!-- 						data-options="required:true,validType:['codeID']" /> -->
						<!-- 				</div> -->
						<div class="fitem">
							<label> <spr:message code='mCommande.obj.mnum' />:
							</label> <input disabled="disabled" name="codeId" id="showCodeId">
						</div>

						<div class="fitem">
							<label> <spr:message code='mCommande.obj.priority' />:
							</label> <input name="priority.id" class="easyui-combobox"
								data-options="required:true" id="c1" />
						</div>
						<div class="fitem">
							<label> <spr:message code='mCommande.obj.type' />:
							</label> <input name="type.id" class="easyui-combobox"
								data-options="required:true,onChange:typeSelect" id="c2" />
						</div>
						<div class="fitem">
							<label> <spr:message code='mCommande.obj.customer' />:
							</label> <input name="customer.id" class="easyui-combobox"
								data-options="required:true,onSelect:addrVals,editable:true,onUnselect:unSel,onChange:unSel"
								id="c3" /> <a href="javascript:;" onclick="MC.openFmCus()"><spr:message
									code='mCommande.add.use' /></a>&nbsp;&nbsp;<a href="javascript:;"
								onclick="MC.openSrcCus()"><spr:message
									code='mCommande.screen.use' /></a>
						</div>
						<div class="fitem">
							<label> <spr:message code='mCommande.obj.address' />:
							</label> <input name="address.id" class="easyui-combobox"
								data-options="required:true,disabled:true" id="c4" /> <a
								id="addAddrBtn" class="easyui-linkbutton"
								onclick="MC.openFmAddr()"
								data-options="iconCls:'icon-add',disabled:true"><spr:message
									code='mCommande.add.eAddress' /></a>
						</div>
						<div class="fitem" id="franLevel">
							<div>
								<label>级别:</label><input type="text" class="easyui-combobox"
									data-options="valueField:'id',textField:'name',url:'franchises?goto=levelList2',onChange:levelSelect" />
							</div>
						</div>
						<div class="fitem">
							<div>
								<label id="sell1"> <spr:message
										code='mCommande.obj.seller' />:
								</label><label id="sell2"> <spr:message
										code='mCommande.obj.franchises' />:
								</label> <input class="easyui-combobox" name="seller.id"
									data-options="required:true" id="c5" />
								<!-- name="seller.id" -->
							</div>
							<%-- 	<div id="sell2">
								<label> <spr:message code='mCommande.obj.franchises' />:
								</label> <input class="easyui-combobox" id="c52" name="fid.id"
									data-options="required:true" />
							</div> --%>
						</div>
						<div class="fitem">
							<label> <spr:message code='mCommande.obj.externalCode' />:
							</label> <input name="externalCode" class="easyui-textbox"
								data-options="required:false,validType:['externalCode']" />
						</div>
						<div>
							<input type="hidden" id="validator_id" name="id" /> <input
								type="hidden" name="timeCreation" /> <input type="hidden"
								name="timeModification" /> <input type="hidden"
								name="timeDelete" /><input type="hidden" name="orderStatus" />
						</div>
					</form>
				</div>
				<div id="dbtns">
					<a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'" id="btnSave2"><spr:message
							code='crud.btn.sure' /></a> <a href="javascript:void(0);"
						class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
						onclick="javascript:$('#dlg').dialog('close');"><spr:message
							code='crud.btn.cancel' /></a>
				</div>
			</div>
			<div>
				<div id="dlg-cfg" class="easyui-dialog"
					style="width: 840px; height: 550px; padding: 10px 20px"
					data-options="buttons : '#dlg-buttons-cfg'">
					<form id="fm-cfg" method="post" novalidate>
						<div style="margin-bottom: 10px; height: 30px;">
							<div class="leftDIv">
								<label><spr:message code='slaveCommande.obj.codeID' />:</label>
								<input name="commande.codeId" class="easyui-textbox"
									id="slCodeID2"
									data-options="required:true,validType:['slCodeID']" />
							</div>
							<div class="leftDIv">
								<label> <spr:message code='slaveCommande.obj.model' />:
								</label> <input name="commande.model.id" class="easyui-combobox"
									data-options="required:true,onSelect:modelSel" id="c6" />
							</div>
						</div>
						<div style="clear: both;"></div>
						<div id="dgc">
							<table class="easyui-datagrid" style="width: 400px;" id="dg">
							</table>
						</div>
						<div id="dgc2" style="margin-top: 10px;">
							<table class="easyui-datagrid" style="width: 400px;" id="dg2">
							</table>
						</div>
						<div style="margin-top: 10px;">
							<label style="float: left; width: 60px;"><spr:message
									code='slaveCommande.obj.comments' />:</label>
							<textarea rows="3" cols="80" name="commande.comments"></textarea>
						</div>
					</form>
				</div>
			</div>
			<div id="dlg-buttons-cfg">
				<a href="javascript:void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-ok'" id="btnCfg"><spr:message
						code='crud.btn.sure' /></a> <a href="javascript:void(0);"
					class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
					onclick="javascript:$('#dlg-cfg').dialog('close');"><spr:message
						code='crud.btn.cancel' /></a>
			</div>
			<jsp:include page="slaveDetail.jsp" />
			<jsp:include page="statusDeal.jsp" />


			<div>
				<div id="dlg-cus" class="easyui-dialog"
					data-options="buttons : '#dlg-buttons-cus'"
					style="width: 620px; height: 250px; padding: 10px 10px">
					<form id="fmCus" method="post" novalidate>
						<div class="leftDIv">
							<div>
								<label><spr:message code='customer.obj.firstName' />:</label> <input
									name="firstName" class="easyui-textbox"
									data-options="required:true" />
							</div>
							<div>
								<label><spr:message code='customer.obj.middleName' />:</label>
								<input name="middleName" class="easyui-textbox" />
							</div>
							<div>
								<label><spr:message code='customer.obj.lastName' />:</label> <input
									name="lastName" class="easyui-textbox"
									data-options="required:true" />
							</div>
							<div>
								<label><spr:message code='customer.obj.sex' />:</label> <input
									id="csex" name="sex" class="easyui-combobox"
									data-options="required:true" />
							</div>
							<div>
								<label><spr:message code='customer.obj.email' />:</label> <input
									name="email" class="easyui-textbox"
									data-options="required:true,validType:['email']" />
							</div>
						</div>
						<div class="leftDIv">
							<div>
								<label><spr:message code='customer.obj.fax' />:</label> <input
									name="fax" class="easyui-textbox" />
							</div>
							<div>
								<label><spr:message code='customer.obj.codeID' />:</label> <input
									name="codeId" class="easyui-textbox" id="codeId2"
									data-options="validType:['codeIDCus']" />
							</div>
							<div>
								<label><spr:message code='customer.obj.country' />:</label> <input
									name="country.id" class="easyui-combobox" id="ccountry"
									data-options="required:true" />
							</div>
							<div>
								<label><spr:message code='customer.obj.telephone' />:</label> <input
									name="telephone" class="easyui-textbox" />
							</div>
							<div>
								<label><spr:message code='customer.obj.mobile' />:</label> <input
									name="mobile" class="easyui-textbox" />
							</div>
						</div>
						<div>
							<input type="hidden" id="validator_id" />
						</div>
					</form>
				</div>
				<div id="dlg-buttons-cus">
					<a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'" onclick="MC.saveFmCus()"><spr:message
							code='crud.btn.sure' /></a> <a href="javascript:void(0);"
						class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
						onclick="javascript:$('#dlg-cus').dialog('close');"><spr:message
							code='crud.btn.cancel' /></a>
				</div>
			</div>
			<div>
				<div id="dlg-addr" class="easyui-dialog"
					data-options="buttons : '#dlg-buttons-addr'"
					style="width: 350px; height: 200px; padding: 10px 20px">
					<form id="fmAddr" method="post" novalidate>
						<div>
							<label><spr:message code='daddr.obj.posteCode' />: </label> <input
								name="posteCode" class="easyui-textbox"
								data-options="required:true" />
						</div>
						<div>
							<label><spr:message code='daddr.obj.address' />: </label> <input
								name="address" class="easyui-textbox"
								data-options="required:true" />
						</div>
					</form>
				</div>
				<div id="dlg-buttons-addr">
					<a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-ok'" onclick="MC.saveFmAddr()"><spr:message
							code='crud.btn.sure' /></a> <a href="javascript:void(0);"
						class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
						onclick="javascript:$('#dlg-addr').dialog('close');"><spr:message
							code='crud.btn.cancel' /></a>
				</div>
			</div>
		</div>
	</div>
	<div id="dlg-print" class="easyui-dialog"
		style="width: 650px; height: 550px; padding: 5px 5px; text-align: center;">
		<div id='print_zone' style="padding-top: 5px;">
			<div style="float: left;">
				<img alt="" src="" id="barcode">
			</div>
			<div style="float: left; vertical-align: middle;">
				<span id="sla" style="font-size: 23px;"></span>
			</div>
		</div>
	</div>
	<div id="dlg-print2" class="easyui-dialog"
		style="width: 772px; height: 610px; padding: 5px 5px; text-align: center;">
		<div id='print_zone2' style="padding-top: 5px;">
			<table
				style="border: solid 1px black; width: 400px; height: 220px; border-spacing: 0; margin: auto">
				<tr>
					<td class="col1" colspan="2"><div id="lcodeId"
							style="font-size: 10px;"></div></td>
					<td class="col2"><div id="ltime" style="font-size: 10px;"></div></td>
					<td class="col3"><div id="lpro" style="font-size: 10px;"></div></td>
					<td class="col2" rowspan="11"><img alt="" src="" id="barcode2"></td>
				</tr>
				<tr>
					<td class="col1"></td>
					<td class="col2">L</td>
					<td class="col2">R</td>
					<td class="col3">Cable</td>
				</tr>
				<tr>
					<td class="col1">外壳颜色</td>
					<td class="col2"><div id="scl"></div></td>
					<td class="col2"><div id="scr"></div></td>
					<td class="col3"><div id="cab"></div></td>
				</tr>
				<tr>
					<td class="col1">耳道颜色</td>
					<td class="col2"><div id="acl"></div></td>
					<td class="col2"><div id="acr"></div></td>
					<td class="col3">NOC</td>
				</tr>
				<tr>
					<td class="col1">面板颜色</td>
					<td class="col2"><div id="pcl"></div></td>
					<td class="col2"><div id="pcr"></div></td>
					<td class="col3"><div id="c2a"></div></td>
				</tr>
				<tr>
					<td class="col1">面板嵌入</td>
					<td class="col2"><div id="pql"></div></td>
					<td class="col2"><div id="pqr"></div></td>
					<td class="col3">备注</td>
				</tr>
				<tr>
					<td class="col1">嵌入位置</td>
					<td class="col2"><div id="qpl"></div></td>
					<td class="col2"><div id="qpr"></div></td>
					<td class="col3" rowspan="3"><div id="lcommit"></div></td>
				</tr>
				<tr>
					<td class="col1">插头类型</td>
					<td class="col2"><div id="ptl"></div></td>
					<td class="col2"><div id="ptr"></div></td>
				</tr>
				<tr>
					<td class="col1">INITIAL</td>
					<td class="col2"><div id="c1l"></div></td>
					<td class="col2"><div id="c1r"></div></td>
				</tr>
			</table>

		</div>
	</div>
	<div>
		<div id="dlg-customer" class="easyui-dialog"
			style="width: 772px; height: 610px; padding: 5px 5px; text-align: center;"
			data-options="buttons : '#dlg-buttons-cust'">
			<table class="easyui-datagrid" title="用户筛选" id="custt"
				style="width: 700px; height: 250px"
				data-options="singleSelect:true,collapsible:true">
			</table>
		</div>
		<div id="dlg-buttons-cust">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'" onclick="MC.getSelectSurt()"><spr:message
					code='crud.btn.sure' /></a><a href="javascript:void(0);"
				class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				onclick="MC.getReset()">重置</a> <a href="javascript:void(0);"
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				onclick="javascript:$('#dlg-customer').dialog('close');"><spr:message
					code='crud.btn.cancel' /></a>
		</div>
	</div>

</body>
</html>