<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<div>
	<div id="slave-detail" class="easyui-dialog"
		style="width: 640px; height: 380px; padding: 10px 20px"
		data-options="buttons:[{text:'<spr:message code="crud.btn.cancel" />',iconCls:'icon-cancel',handler:function(){$('#slave-detail').dialog('close');}}]">
		<div>
			<table class="easyui-datagrid" style="width: 600px;" id="dgSd">
			</table>
		</div>
		<div style="margin-top: 10px;">
			<table class="easyui-datagrid" style="width: 600px;" id="dgSd2">
			</table>
		</div>
	</div>
	<div id="slaveChoiceTip"></div>
	<div id="slave-modify" class="easyui-dialog"
		style="width: 440px; height: 250px; padding: 10px 20px"
		data-options="buttons : [{text:'<spr:message code="crud.btn.sure" />',iconCls:'icon-ok',handler:function(){saveChange();}},{text:'<spr:message code="crud.btn.cancel" />',iconCls:'icon-cancel',handler:function(){$('#slave-modify').dialog('close');}}]">
		<form id="fm-slave-modify" method="post" novalidate
			action="slaveCommande?goto=update">
			<div class="fitem">
				<label><spr:message code='slaveCommande.obj.codeID' />:</label> <input
					name="codeId" class="easyui-textbox"
					data-options="required:true,validType:['slCodeID']" />
			</div>
			<div>
				<label style="float: left; width: 60px;"><spr:message
						code='slaveCommande.obj.comments' />:</label>
				<textarea rows="3" cols="30" name="comments"></textarea>
			</div>
			<input type="hidden" name="id" id="vali_id">
		</form>
	</div>

	<div id="eleValChange" class="easyui-dialog"
		style="width: 350px; height: 160px; padding: 10px 20px"
		data-options="buttons : [{text:'<spr:message code="crud.btn.sure" />',iconCls:'icon-ok',handler:function(){saveEleValChange();}},{text:'<spr:message code="crud.btn.cancel" />',iconCls:'icon-cancel',handler:function(){$('#eleValChange').dialog('close');}}]">
		<form id="fm-eleValChange" method="post" novalidate
			action="slaveCommandeConfig?goto=update">
			<div id="eL" style="height: 30px;"></div>
			<div id="eR" style="height: 30px;"></div>
			<input type="hidden" name="id">
		</form>
	</div>
</div>


