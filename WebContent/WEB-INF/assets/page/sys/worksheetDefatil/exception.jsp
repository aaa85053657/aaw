<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="dlg" class="easyui-dialog"
	style="width: 680px; height: 300px; padding: 10px 20px" closed="true"
	buttons="#dlg-buttons" modal="true">
	<form id="exceptionfm" method="post" novalidate>
		<div class="dataDiv">
			<div>
				<label>异常原因:</label> <input class="easyui-combobox"
					name="exceptionReason"
					data-options="required:true,data:[{id:'1',text:'操作失误'},{id:'2',text:'材料问题'}]">
			</div>
			<div>
				<label>其它原因:</label> <input type="text" name="otherReason"
					class="easyui-textbox" />
			</div>
			<div>
				源于工序:
				<div id="worksheetDetailId"></div>
			</div>
			<div>
				回滚工序:
				<div id="rollBackId"></div>
			</div>
		</div>
	</form>
</div>
<div id="dlg-buttons">
	<a href="javascript:saveException();void(0);" class="easyui-linkbutton"
		iconCls="icon-ok">确定</a> <a href="javascript:void(0)"
		class="easyui-linkbutton" iconCls="icon-cancel"
		onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>