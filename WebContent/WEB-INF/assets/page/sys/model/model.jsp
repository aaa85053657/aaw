<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spr:message code='model.page.title' /></title>
<jsp:include page="../../base/head2.jsp" />
<script type="text/javascript"
	src="static/js/common/datagrid-detailview.js"></script>
<script type="text/javascript" src="static/js/col/ModelCol.js"></script>
<script type="text/javascript"
	src="static/js/validator/ModelValidator.js"></script>
<script type="text/javascript" src="static/js/opr/Model.js"></script>
<script type="text/javascript"
	src="static/js/common/easyui.mergeCell.js"></script>
</head>
<body>
	<div class="easyui-layout" id="layout1" data-options="fit:true">
		<div id="east" data-options="region:'east',split:true" title="组件"
			style="width: 350px;">
			<div
				style="width: 50%; height: 70%; float: left; border-right: solid 1px black;">
				<div style="height: 65%;">
					<div style="height: 15%; margin: 10px 0px 10px 30px">
						<a href="javascript:void(0);" class="easyui-linkbutton"
							iconCls="icon-ok" id="btn_save">保存</a>
					</div>
					<div style="height: 85%; overflow: auto;">
						<ul class="easyui-tree" id="comtree"
							data-options="method:'get',animate:true"></ul>
					</div>
				</div>
				<div
					style="height: 35%; margin-top: 10px; border-top: solid 1px black; overflow: auto;">
					<h5 style="text-align: center;">排列位置</h5>
					<ul class="easyui-tree3" id="comtree3"
						data-options="method:'get',animate:true,checkbox:true"></ul>
				</div>
			</div>
			<div style="width: 45%; height: 70%; float: left; overflow: auto;">
				<div style="margin: 10px 0px 0px 30px">
					<a style="float: left; margin-right: 2px;"
						href="javascript:void(0);" class="easyui-linkbutton"
						id="btn_allSelect">全选</a><a style="float: left;"
						href="javascript:void(0);" class="easyui-linkbutton"
						id="btn_cancel">取消</a>
				</div>
				<div style="clear: both"></div>
				<div>
					<ul class="easyui-tree" id="comtree2"
						data-options="method:'get',animate:true,checkbox:true"></ul>
				</div>
			</div>
			<div style="clear: both;"></div>
			<div style="border-top: solid 1px black; padding: 10px 0 0 10px">
				<img alt="" id="showPic" src="" style="width: 250px; height: 150px">
			</div>
		</div>
		<div data-options="region:'center',iconCls:'icon-ok'">
			<table id="tt"></table>
			<div id="toolbar">
				<a href="javascript:ccLoad();void(0);" class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true" id="btnAdd"><spr:message
						code='crud.btn.add' /></a> |<a
					href="javascript:ccLoad();backfill();void(0);"
					class="easyui-linkbutton" id="btnUpdate"
					data-options="iconCls:'icon-edit',plain:true"><spr:message
						code='crud.btn.update' /></a>| <a href="javascript:void(0);"
					class="easyui-linkbutton" id="btnDel"
					data-options="iconCls:'icon-remove',plain:true"><spr:message
						code='crud.btn.del' /></a>| <a href="javascript:BtnCfg();"
					class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true"><spr:message
						code='model.btn.cfg' /></a>
			</div>
		</div>
	</div>
	<div>
		<div id="dlg" class="easyui-dialog"
			style="width: 560px; height: 340px; padding: 10px 20px">
			<form id="fm" method="post" novalidate>
				<div class="fitem">
					<label><spr:message code='model.obj.codeID' />:</label> <input
						name="codeId" class="easyui-textbox"
						data-options="required:true,validType:['codeID']" />
				</div>
				<div class="fitem">
					<label> <spr:message code='model.obj.name' />:
					</label> <input name="name" class="easyui-textbox"
						data-options="required:true,validType:['name']" />
				</div>
				<div class="fitem">
					<label> <spr:message code='model.obj.line' />:
					</label> <input name="line.id" class="easyui-combobox" id="cc"
						data-options="required:true" />
				</div>
				<div class="fitem">
					<label> <spr:message code='model.obj.modelState' />:
					</label> <input name="modelStatus" class="easyui-combobox"
						data-options="required:true,data:[{id:1,text:'创建中'},{id:2,text:'生产'}]" />
				</div>
				<div class="fitem">
					<label style="float: left;"><spr:message
							code='model.obj.comments' />:</label>
					<textarea rows="5" cols="30" name="comments"></textarea>
				</div>
				<div>
					<input type="hidden" id="validator_id" /> <input type="hidden"
						name="timeCreation" /> <input type="hidden"
						name="timeModification" /> <input type="hidden" name="timeDelete" />
				</div>
			</form>
		</div>
		<div id="dlg-buttons">
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
			style="width: 350px; height: 200px; padding: 10px 20px"
			data-options="buttons : '#dlg-buttons-cfg'">
			<form id="fm-cfg" method="post" novalidate>
				<!-- 				<table> -->
				<!-- 					<thead> -->
				<!-- 						<tr> -->
				<%-- 							<th><spr:message code='model.cfg.component' /></th> --%>
				<%-- 							<th><spr:message code='model.cfg.position' /></th> --%>
				<%-- 							<th><spr:message code='model.cfg.element' /></th> --%>
				<%-- 							<th><spr:message code='model.cfg.opr' /></th> --%>
				<!-- 						</tr> -->
				<!-- 					</thead> -->
				<!-- 					<tbody id="cfgBody"> -->
				<!-- 						<tr> -->
				<!-- 							<td><input name="component[]" class="easyui-combobox pSel" -->
				<!-- 								data-options="required:true,onSelect:selCheck" id="cCom" /></td> -->
				<!-- 							<td><input name="position[]" class="easyui-combobox" -->
				<!-- 								data-options="required:true" id="cPos" /></td> -->
				<!-- 							<td><input class="easyui-combobox" -->
				<!-- 								data-options="required:true,groupField:'group',multiple:true,onSelect:setV" -->
				<!-- 								id="cEle" /> <input type="hidden" name="element[]"></td> -->
				<!-- 							<td></td> -->
				<!-- 						</tr> -->
				<!-- 					</tbody> -->
				<!-- 				</table> -->

				<div>
					<label> <spr:message code='model.cfg.component' />:
					</label> <input name="componentID" class="easyui-combobox pSel"
						data-options="required:true,onSelect:selCheck" id="cCom" />
				</div>
				<div>
					<label> <spr:message code='model.cfg.position' />:
					</label><input name="position" class="easyui-combobox"
						data-options="required:true" id="cPos" />
				</div>
				<div>
					<label> <spr:message code='model.cfg.element' />:
					</label><input name="element[]" class="easyui-combobox"
						data-options="required:true,groupField:'group',multiple:true"
						id="cEle" />
				</div>
			</form>
		</div>
		<div id="dlg-buttons-cfg">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'" id="btnCfg"><spr:message
					code='crud.btn.sure' /></a> <a href="javascript:void(0);"
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				onclick="javascript:$('#dlg-cfg').dialog('close');"><spr:message
					code='crud.btn.cancel' /></a>

			<!-- 					<a href="javascript:BtnAddRow();" -->
			<%-- 				class="easyui-linkbutton" data-options="iconCls:'icon-add'"><spr:message --%>
			<%-- 					code='model.cfg.addOneRow' /></a> --%>
		</div>
	</div>
</body>
</html>