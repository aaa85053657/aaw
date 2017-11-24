<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="dlg" class="easyui-dialog"
	style="width: 600px; height: auto; padding: 10px 20px"
	data-options="closed:true,buttons:'#dlg-buttons'">
	<form id="fm" method="post" novalidate>
		<div>
			<input type="hidden" id="validator_id" value="1">
		</div>
		<div>
			<div>
				<span style="color: red; float: left;">*</span><label
					style="float: left;">原帐号:&nbsp;</label> <input name="oldAcccount"
					type="text" class="easyui-textbox" data-options="required:true" />
			</div>
			<div>
				<span style="color: red; float: left;">*</span><label
					style="float: left;">原密码:&nbsp;</label> <input name="oldPazzword"
					type="password" class="easyui-textbox" data-options="required:true" />
			</div>
		</div>
		<div style="margin-top: 30px">
			<div>
				<span style="color: red; float: left;">*</span><label
					style="float: left;">新登录帐号:&nbsp;</label> <input name="name"
					type="text" class="easyui-textbox"
					data-options="required:true,validType:['uniqueAccount']" />
			</div>
			<div>
				<span style="color: red; float: left;">*</span><label
					style="float: left;">新密码:&nbsp;</label> <input name="pazzwd"
					id="pazzwd" type="password" class="easyui-textbox"
					data-options="required:true" />
			</div>
			<div>
				<span style="color: red; float: left;">*</span><label
					style="float: left;">请重复密码:&nbsp;</label> <input name="repazzword"
					type="password" class="easyui-textbox" data-options="required:true"
					validType="equals['#pazzwd']" />
			</div>
		</div>
	</form>
</div>
<div id="dlg-buttons">
	<a href="javascript:save();void(0);" class="easyui-linkbutton"
		data-options="iconCls:'icon-ok'">确定</a> <a href="javascript:void(0)"
		data-options="iconCls:'icon-cancel'" class="easyui-linkbutton"
		onclick="javascript:$('#dlg-acc').dialog('close')">取消</a>
</div>