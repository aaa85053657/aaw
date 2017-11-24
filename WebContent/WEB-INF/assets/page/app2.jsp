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
<link rel="stylesheet" type="text/css" href="static/css/app.css">
<script type="text/javascript" src="static/eui/jquery.min.js"></script>
<script type="text/javascript" src="static/eui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/layer/layer.js"></script>
<script type="text/javascript" src="static/js/common/molos.timer.js"></script>
<script type="text/javascript" src="static/js/app/app2.js"></script>
<script type="text/javascript" src="static/js/app/showtime.js"></script>
</head>
<body>
	<div class="molos_head">
		<div class="head_left">
			条形码扫入: <input type="text" id="firstInput" onchange="chang(this)">
		</div>
		<div class="head_right">
			<div style="margin-right: 22px;">
				当前时间: <span id="clockbox"></span>
			</div>
			<div>${employee.name }<input type="hidden" id="ename"
					value="${employee.name }">
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="main">
		<!--左大块  -->
		<div class="left">
			<!--第一块  -->
			<div class="molos_base">
				<table class="gridtable">
					<tr>
						<td>订单号:</td>
						<td>${detail.worksheetDetail.worksheet.slaveCommande.mainCommande.codeId }</td>
					</tr>
					<tr>
						<td>第三方ID:</td>
						<td>${detail.worksheetDetail.worksheet.slaveCommande.mainCommande.externalCode }</td>
					</tr>
					<tr>
						<td>型号:</td>
						<td>${detail.worksheetDetail.worksheet.slaveCommande.model.name }</td>
					</tr>
					<tr>
						<td>优先级:</td>
						<td>${detail.worksheetDetail.worksheet.slaveCommande.mainCommande.priority.name }</td>
					</tr>
					<tr>
						<td>备注:</td>
						<td>${detail.worksheetDetail.worksheet.slaveCommande.comments }</td>
					</tr>
					<tr>
						<td>型号参数:</td>
						<td>${detail.worksheetDetail.worksheet.slaveCommande.model.name }</td>
					</tr>
				</table>
				<!--第一个白块  -->
				<%-- <div class="molos_base_left">
					
					<div class="molos_base_line">订单号:${detail.worksheetDetail.worksheet.slaveCommande.mainCommande.codeId },${detail.worksheetDetail.worksheet.slaveCommande.codeId },${detail.worksheetDetail.worksheet.slaveCommande.mainCommande.externalCode }</div>
					<div class="molos_base_line">优先级:高</div>
					<div class="molos_base_line">备注:</div>
					<div class="molos_base_comments">${detail.worksheetDetail.worksheet.slaveCommande.comments }</div>
					<c:if test="${! empty list2 }">
						<c:forEach items="${list2 }" var="dd">
							<div class="molos_base_line">属性:${dd.attribute.name }</div>
							<div class="molos_base_line">左自定义:${dd.elementLeft }
								右自定义:${dd.elementRight }</div>
						</c:forEach>
					</c:if>
				</div>
				<!--第二个白块  -->
				<div class="molos_base_left">
					<div class="molos_base_line">型号:${detail.worksheetDetail.worksheet.slaveCommande.model.name }</div>
					<div class="molos_base_line">&nbsp;</div>
					<div class="molos_base_line">型号参数:</div>
					<div class="molos_base_comments">"3x Vajhfskdhfksfsk</div>
				</div> --%>
			</div>
			<div class="clear"></div>
			<!--第二块  -->
			<div class="molos_base">
				<table class="gridtable">
					<tr>
						<th class="col1">组件</th>
						<th class="col2">属性</th>
						<th class="col2">左</th>
						<th class="col2">右</th>
					</tr>
					<c:forEach items="${cfgDetails }" var="cd" varStatus="status">
						<tr>
							<td class="col1">${cd.componentName }</td>
							<td class="col2">${cd.attrName }</td>
							<td class="col2"><a href="javascript:showPic(${cd.eid })">${cd.elementLeft }</a></td>
							<td class="col2"><a href="javascript:showPic(${cd.eid })">${cd.elementRight }</a></td>
						</tr>
					</c:forEach>
					<c:if test="${! empty list2 }">
						<c:forEach items="${list2 }" var="dd">
							<tr>
								<td class="col1">${dd.component.name }</td>
								<td class="col2">${dd.attribute.name }</td>
								<td class="col2">${dd.elementLeft }</td>
								<td class="col2">${dd.elementRight }</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</div>
		<div class="blank"></div>
		<!--右大块  -->
		<div class="right">
			<!--第一块  -->
			<div class="molos_base">
				<form method="post" id="fm1">
					<div class="_hidden">
						<input type="hidden" name="operatorId" value="${employee.id }">
						<input type="hidden" value="${detail.worksheetDetail.id }"
							name="WorksheetDetailId" id="worksheetIsWorking"> <input
							type="hidden" value="${detail.worksheetDetail.worksheet.id }"
							id="worksheetId">
					</div>
					<div class="molos_base_line">
						工序名称:${detail.worksheetDetail.metaProcedure.name }<input
							type="hidden" id="dmname"
							value="${detail.worksheetDetail.metaProcedure.name }">
					</div>
					<div class="molos_base_line">备注:</div>
					<table class="pro_table">
						<c:forEach items="${details }" var="ds" varStatus="status">
							<tr>
								<td class="td_col1">${ds.worksheetDetail.profile.name }:</td>
								<td class="td_col2"><input
									name="proList[${status.index }].comments"
									value="${ds.comments }" /></td>
								<input type="hidden" name="proList[${status.index }].id"
									value="${ds.worksheetDetail.id }">
							</tr>
						</c:forEach>
					</table>
				</form>
			</div>
			<!--第二块  -->
			<div class="molos_base btns">
				<!--异常  -->
				<div>
					<button onclick="javascript:openExcepiton();void(0);">异常</button>
				</div>
				<!--其他订单  -->
				<div>
					<button onclick="javascript:openOthers();void(0);">其他订单</button>
				</div>
				<!--呼叫  -->
				<div>
					<button onclick="javascript:openCell();void(0);">呼叫</button>
				</div>
				<!--退出  -->
				<div>
					<button>退出</button>
				</div>
			</div>
		</div>
	</div>


	<!--弹窗部分  -->

	<!--异常  -->
	<div id="test" style="display: none;">
		<form id="exceptionfm" method="post" novalidate>
			<div class="ex_container">
				<div>
					<div>异常原因:</div>
					<div class="ex_reason" id="ex_resonDiv"></div>
				</div>
				<div class="clear"></div>
				<div>
					<label>备注:</label> <input type="text" name="otherReason" />
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
	<div id="test2">
		<div class="other_order"></div>
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
		<table style="border: 0px">
			<tr>
				<td>订单号:</td>
				<td><span id="wcode"></span></td>
			</tr>
			<tr>
				<td>员工工号:</td>
				<td><input type="text" class="inCode" id="secondInput1"
					onchange="validEcode(this)"></td>
			</tr>
		</table>
	</div>

	<!--中间态2  -->
	<div id="test6">
		<input type="hidden" id="eid">
		<table style="border: 0px">
			<tr>
				<td>订单号:</td>
				<td><input type="text" class="inCode" id="secondInput2"
					onchange="validWcode(this)"></td>
			</tr>
			<tr>
				<td>员工工号:</td>
				<td><span id="ecode"></span></td>
			</tr>
		</table>
	</div>

	<!-- 提示 -->
	<div id="test7">
		<div>
			订单号:<input type="text" readonly="readonly" id="wcodeMain"
				value="${detail.worksheetDetail.worksheet.slaveCommande.mainCommande.codeId}">
		</div>
		<div>
			员工工号:<input type="text" readonly="readonly" value="${employee.name }">
		</div>
	</div>

	<!--选择操作时间  -->
	<div id="test8">
		<form id="workForm" method="post">
			<input type="hidden" id="eidCom" name="eid"> <input
				type="hidden" id=didCom name="did">
			<div>
				<input name="oprTime" id="ot" type="hidden">
			</div>
		</form>
	</div>

	<!--显示图片 -->
	<div id="pic1">
		<img alt="暂无图片样例" id="pic" src="" style="width: 200px; height: 150px;">
	</div>
</body>
</html>