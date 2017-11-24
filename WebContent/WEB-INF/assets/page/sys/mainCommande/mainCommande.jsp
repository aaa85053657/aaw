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
<script type="text/javascript" src="static/js/opr/MainCommande.js"></script>
<script type="text/javascript"
	src="static/js/common/easyui.mergeCell.js"></script>
<style type="text/css">
.fitem {
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<table id="tt"></table>
	<div id="toolbar">
		<table>
			<tr>
				<td><a href="javascript:disableCC();void(0);"
					class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true" id="btnAdd"><spr:message
							code='crud.btn.add' /></a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a href="javascript:backfill();void(0);"
					class="easyui-linkbutton" id="btnUpdate"
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
				<td><a href="javascript:statusOpr();" class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true"><spr:message
							code='mCommande.btn.statusOpr' /></a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a href="javascript:initSt();" class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true"><spr:message
							code='mCommande.btn.statusOpr.init' /></a></td>
			</tr>
		</table>
	</div>
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
						data-options="required:true" id="c2" />
				</div>
				<div class="fitem">
					<label> <spr:message code='mCommande.obj.customer' />:
					</label> <input name="customer.id" class="easyui-combobox"
						data-options="required:true,onSelect:addrVals,editable:true,onUnselect:unSel,onChange:unSel"
						id="c3" /> <a href="javascript:;" onclick="MC.openFmCus()">新增用户</a>
				</div>
				<div class="fitem">
					<label> <spr:message code='mCommande.obj.address' />:
					</label> <input name="address.id" class="easyui-combobox"
						data-options="required:true,disabled:true" id="c4" /> <a
						id="addAddrBtn" class="easyui-linkbutton"
						onclick="MC.openFmAddr()"
						data-options="iconCls:'icon-add',disabled:true">新增邮寄信息</a>
				</div>
				<div class="fitem">
					<label> <spr:message code='mCommande.obj.seller' />:
					</label> <input name="seller.id" class="easyui-combobox"
						data-options="required:true" id="c5" />
				</div>
				<div class="fitem">
					<label> <spr:message code='mCommande.obj.externalCode' />:
					</label> <input name="externalCode" class="easyui-textbox"
						data-options="required:false" />
				</div>
				<div>
					<input type="hidden" id="validator_id" /> <input type="hidden"
						name="timeCreation" /> <input type="hidden"
						name="timeModification" /> <input type="hidden" name="timeDelete" />
				</div>
			</form>
		</div>
		<div id="dbtns">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'" id="btnSave"><spr:message
					code='crud.btn.sure' /></a> <a href="javascript:void(0);"
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				onclick="javascript:$('#dlg').dialog('close');"><spr:message
					code='crud.btn.cancel' /></a>
		</div>
	</div>
	<div>
		<div id="dlg-cfg" class="easyui-dialog"
			style="width: 640px; height: 420px; padding: 10px 20px"
			data-options="buttons : '#dlg-buttons-cfg'">
			<form id="fm-cfg" method="post" novalidate>
				<div style="margin-bottom: 10px; height: 30px;">
					<div class="leftDIv">
						<label><spr:message code='slaveCommande.obj.codeID' />:</label> <input
							name="commande.codeId" class="easyui-textbox"
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
				<div style="margin-top: 10px;">
					<label style="float: left; width: 60px;"><spr:message
							code='slaveCommande.obj.comments' />:</label>
					<textarea rows="3" cols="50" name="commande.comments"></textarea>
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
						<label><spr:message code='customer.obj.middleName' />:</label> <input
							name="middleName" class="easyui-textbox" />
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
							name="codeId" class="easyui-textbox"
							data-options="required:true,validType:['codeIDCus']" />
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
						name="address" class="easyui-textbox" data-options="required:true" />
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
	<div>
		<div id="dlg-print" class="easyui-dialog"
			style="width: 340px; height: 180px; padding: 5px 5px">
			<div id='print_zone'>test</div>
		</div>
	</div>
</body>
</html>