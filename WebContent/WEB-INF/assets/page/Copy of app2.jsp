<%@page import="cn.molos.common.SessionConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript" src="static/layer/jquery.min.js"></script>
<script type="text/javascript" src="static/eui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="static/css/app.css">
<script type="text/javascript" src="static/js/opr/App.js"></script>
</head>
<body>
	<div class="top" style="float: left; width: 1000px;">
		<div style="float: left;">
			条形码扫入: <input type="text" onblur="chang(this)">
		</div>
		<div style="float: right;">
			<div style="float: left; padding-right: 20px;">当前时间:2015/07/25&nbsp;22:05</div>
			<div style="float: right;">${employee.name }<input
					type="hidden" id="ename" value="${employee.name }">
			</div>
		</div>
	</div>
	<div class="main"
		style="float: left; width: 1000px; padding-top: 30px;">
		<!--左大块  -->
		<div class="left" style="width: 550px; float: left;">
			<!--第一块  -->
			<div
				style="float: left; border: 2px solid black; background-color: #DDDDDD; width: 550px; height: 200px;">
				<!--第一个白块  -->
				<div style="float: left; width: 200px; margin-left: 10px;">
					<div style="padding: 10px 0 0 5px;">订单号:${detail.worksheetDetail.worksheet.slaveCommande.mainCommande.codeId },${detail.worksheetDetail.worksheet.slaveCommande.codeId }</div>
					<div style="padding: 5px 0 0 5px;">优先级:高</div>
					<div style="padding: 5px 0 0 5px;">备注:</div>
					<div
						style="border: 1px solid black; width: 220px; height: 110px; float: left; margin-top: 10px; background-color: white;">${detail.worksheetDetail.worksheet.slaveCommande.comments }</div>
				</div>
				<!--第二个白块  -->
				<div style="float: left; width: 200px; margin-left: 30px;">
					<div style="padding: 10px 0 0 5px;">型号:${detail.worksheetDetail.worksheet.slaveCommande.model.name }</div>
					<br>
					<div style="padding: 10px 0 0 5px;">型号参数:</div>
					<div
						style="border: 1px solid black; width: 220px; height: 110px; float: left; margin-top: 10px; background-color: white;">"3x
						Vajhfskdhfksfsk</div>
				</div>
			</div>
			<!--第二块  -->
			<div
				style="float: left; border: 2px solid black; background-color: #DDDDDD; width: 550px; height: 200px; margin-top: 20px;">
				<table border="1" cellpadding="0" cellspacing="0"
					style="width: 550px; height: 200px; text-align: center;">
					<tr>
						<td>组件</td>
						<td>属性</td>
						<td>左/右</td>
					</tr>
					<c:forEach items="${cfgDetails }" var="cd" varStatus="status">
						<tr style="background-color: white;">
							<td>${cd.componentName }</td>
							<td>${cd.attrName }</td>
							<td>${cd.elementLeft }/${cd.elementRight }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<!--右大块  -->
		<div class="right"
			style="width: 400px; float: left; margin-left: 50px;">
			<!--第一块  -->
			<div
				style="float: left; border: 2px solid black; background-color: #DDDDDD; width: 400px; height: 200px; overflow-y: scroll;">
				<form method="post" id="fm1">
					<div class="_hidden">
						<input type="hidden" name="operatorId" value="${employee.id }">
						<input type="hidden" value="${detail.worksheetDetail.id }"
							name="WorksheetDetailId" id="worksheetIsWorking">
					</div>
					<div style="padding: 10px 0 0 5px;">
						工序名称:${detail.worksheetDetail.metaProcedure.name }<input
							type="hidden" id="dmname"
							value="${detail.worksheetDetail.metaProcedure.name }">
					</div>
					<c:forEach items="${details }" var="ds" varStatus="status">
                                                 备注:<input
							class="easyui-textbox" style="width: 135px;"
							name="proList[${status.index }].comments" value="${ds.comments }" />
						<input type="hidden" name="proList[${status.index }].id"
							value="${ds.worksheetDetail.id }">
						<input type="hidden" name="proList[${status.index }].name"
							value="${ds.worksheetDetail.profile.name }">
					</c:forEach>
				</form>
			</div>
			<!--第二块  -->
			<div
				style="float: left; border: 2px solid black; background-color: #DDDDDD; width: 400px; height: 200px; margin-top: 20px;">
				<!--异常  -->
				<div style="margin: 15px 0 0 60px; float: left;">
					<a id="go" name="test" href="javascript:openExcepiton();void(0);"><input
						type="submit" value="异常" style="width: 120px; height: 30px;"></a>
				</div>
				<!--其他订单  -->
				<div style="margin: 15px 0 0 30px; float: left;">
					<a id="go" name="test" href="javascript:openOthers();void(0);"><input
						type="submit" value="其他订单" style="width: 120px; height: 30px;"></a>
				</div>
				<!--呼叫  -->
				<div style="margin: 15px 0 0 60px; float: left;">
					<a id="go" name="test" href="javascript:openCell();void(0);"><input
						type="submit" value="呼叫" style="width: 120px; height: 30px;"></a>
				</div>
				<!--退出  -->
				<div style="margin: 15px 0 0 30px; float: left;">
					<a id="go" rel="leanModal" name="test4" href="#test4"><input
						type="submit" value="退出" style="width: 120px; height: 30px;"></a>
				</div>
			</div>
		</div>
	</div>


	<!--弹窗部分  -->

	<!--异常  -->
	<div id="test">
		<form id="exceptionfm" method="post" novalidate>
			<div class="dataDiv">
				<div>
					<label>异常原因:</label> <select name="exceptionReason">
						<option value="1">操作失误</option>
						<option value="2">材料问题</option>
					</select>
				</div>
				<div>
					<label>其它原因:</label> <input type="text" name="otherReason" />
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

	<!--其他订单  -->
	<div id="test2" style="border: 1px solid black;">
		<!-- 	<div id="tableDiv"></div> -->
	</div>
	<!--呼叫  -->
	<div id="test3">
		<p style="text-align: center;">是否呼叫客服热线？</p>
		<input type="submit" value="是" style="margin-left: 80px; width: 50px;" />&nbsp;<input
			type="submit" value="否" style="margin-left: 30px; width: 50px;" />
	</div>
	<!--退出  -->
	<div id="test4">
		<p style="text-align: center;">确认退出？</p>
		<br> <br> <input type="submit" value="是"
			style="margin-left: 80px; width: 50px;" />&nbsp;<input type="submit"
			value="否" style="margin-left: 30px; width: 50px;" />
	</div>

	<!--中间态1  -->
	<div id="test5">
		<input type="hidden" id="wid">
		<div>
			订单号:<span id="wcode"></span>
		</div>
		<div>
			员工工号:<input type="text" onblur="validEcode(this)">
		</div>
	</div>

	<!--中间态2  -->
	<div id="test6">
		<input type="hidden" id="eid">
		<div>
			订单号:<input type="text" onblur="validWcode(this)">
		</div>
		<div>
			员工工号:<span id="ecode"></span>
		</div>
	</div>

	<!-- 提示 -->
	<div id="test7">
		<div>
			订单号:<input type="text" readonly="readonly" id="wcodeMain"
				value="${detail.worksheetDetail.worksheet.slaveCommande.mainCommande.codeId},${detail.worksheetDetail.worksheet.slaveCommande.codeId }">
		</div>
		<div>
			员工工号:<input type="text" readonly="readonly" value="${employee.name }">
		</div>
	</div>

</body>
</html>