<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="dlg2" class="easyui-dialog"
	style="width: 680px; height: 150px; padding: 10px 20px"
	data-options="closed:true,buttons:'#dlg-buttons',modal:true">
	<form id="timeoutfm" method="post" novalidate>
		<div class="dataDiv">
			<div>
				<input type="hidden" name="wid" id="wdid" />
			</div>
			<div>
				<label>加工时间:</label> <input class="easyui-combobox" name="timeout"
					id="timeout"
					data-options="required:true,data:[{id:'5',text:'5分钟'},{id:'10',text:'10分钟'},{id:'30',text:'30分钟'},{id:'60',text:'60分钟'}]">
			</div>
		</div>
	</form>
</div>
<div id="dlg-buttons">
	<a href="javascript:beginWorking();void(0);" class="easyui-linkbutton"
		data-options="iconCls:'icon-ok'">确定</a> <a href="javascript:void(0)"
		class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
		onclick="javascript:$('#dlg2').dialog('close')">取消</a>
</div>