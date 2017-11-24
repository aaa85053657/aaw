<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${report.name }</title>
<script type="text/javascript" src="report/static/config.js"></script>
</head>
<body style="padding: 10px;">
	<fieldset style="width: 80%; margin: auto;">
		<legend>查询条件</legend>
		<div>

			<c:forEach items="${rplist }" var="rp" varStatus="st">
				<input type="hidden" id="rid" value="${report.id }">
				<span>${rp.description }</span>
				<c:if test="${rp.type==5}">
					<input type="text" name="${rp.name }" readonly="readonly"
						class="pa Wdate input_style "
						onClick="WdatePicker({dateFmt:'yyyy-M-d 00:00:00'})" />
				</c:if>
				<c:if test="${rp.type!=5 }">
					<c:if test="${ empty rp.selectOptions }">
						<input type="text" name="${rp.name }" class="pa"
							value="${rp.defaultValue }" />
					</c:if>
					<c:if test="${ !empty rp.selectOptions}">
						<!-- 多选显示框 -->
						<input type="text" id="${rp.name }"
							value="${rp.defaultSelectOptionKeys }"
							onclick="openSelect('${rp.name }')" />
						<!-- 多选实际数据框 -->
						<input type="hidden" id="val_${rp.name }" name="${rp.name }"
							value="${rp.defaultSelectOptionKeys }" class="pa" />
						<!-- 多选数列框 -->
						<input type="hidden" id="selindex_${rp.name }">
						<c:if test="${rp.multiSelection==0 }">
							<input type="button" style="width: 30px" value="..."
								onclick="openSelect('${rp.name }')">
							<div id="dlg_${rp.name }" class="easyui-dialog"
								style="width: 620px; height: 450px; padding: 10px 10px">
								<div style="float: left;">
									<table id="selectTB_${rp.name }" class="selectTable">
										<c:forEach items="${rp.selectOptions }" var="rs"
											varStatus="st2">
											<tr id="tb_tr_${rp.name }_${st2.index}"
												onclick="selectRow('${rp.name }',${st2.index})">
												<c:forEach items="${rs }" var="so" varStatus="st3">
													<c:if test="${st3.index==0 }">
														<td class="selectTD"><input type="hidden"
															id="is_click_${rp.name }_${st2.index}" value="0"><input
															type="hidden" id="key_${rp.name }_${st2.index}"
															value="${so }">${so }</td>
													</c:if>
													<c:if test="${st3.index!=0 }">
														<td class="selectTD">${so }</td>
													</c:if>
												</c:forEach>
											</tr>
										</c:forEach>
									</table>
								</div>
							</div>
						</c:if>
						<c:if test="${rp.multiSelection==1 }">
							<input type="button" style="width: 30px" value="..."
								onclick="openSelect('${rp.name }')">
							<div id="dlg_${rp.name }" class="easyui-dialog"
								style="width: 620px; height: 450px; padding: 10px 10px;"
								data-options="buttons : '#dlg-buttons-${rp.name }'">
								<div style="float: left;">
									<table id="selectTB_${rp.name }" class="selectTable">
										<c:forEach items="${rp.selectOptions }" var="rs"
											varStatus="st2">
											<tr id="tb_tr_${rp.name }_${st2.index}"
												onclick="selectRows('${rp.name }',${st2.index})">
												<c:forEach items="${rs }" var="so" varStatus="st3">
													<c:if test="${st3.index==0 }">
														<td class="selectTD"><input type="hidden"
															id="is_click_${rp.name }_${st2.index}" value="0"><input
															type="hidden" id="key_${rp.name }_${st2.index}"
															value="${so }">${so }</td>
													</c:if>
													<c:if test="${st3.index!=0 }">
														<td class="selectTD">${so }</td>
													</c:if>
												</c:forEach>
											</tr>
										</c:forEach>
									</table>
								</div>
								<div style="float: right; margin-right: 30px;">
									<div style="line-height: 50px;">
										<input type="button" value="全选"
											onclick="allClick('${rp.name}')" style="width: 100px;">
									</div>
									<div style="line-height: 50px;">
										<input type="button" value="不选"
											onclick="cancelClick('${rp.name}')" style="width: 100px;">
									</div>
								</div>
							</div>
							<div id="dlg-buttons-${rp.name }">
								<a href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'icon-ok',onClick:selectSure">确定</a> <a
									href="javascript:void(0);" class="easyui-linkbutton"
									data-options="iconCls:'icon-cancel'"
									onclick="javascript:$('#dlg_${rp.name }').dialog('close');">取消</a>
							</div>
						</c:if>
					</c:if>
				</c:if>
				<input type="hidden" id="tp_${st.count }" value="${rp.type }">
			</c:forEach>
		</div>
		<div style="margin-top: 5px;">
			<input type="button" value="导出" onclick="download()"><input
				type="button" value="查询" onclick="createTable()"> <input
				type="button" value="重置" onclick="resetTable()"> <input
				type="button" value="邮件发送" onclick="outEmail()">

		</div>
	</fieldset>
	<div id="table" style="margin: auto; width: 80%; padding-top: 10px;">
		<input type="hidden" id="collength" value="${fn:length(rclist)}">
		<table id="reportTable" style="width: 100%; border: 1px;"
			cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<c:forEach items="${rclist }" var="rc" varStatus="st">
						<td class="col1">${rc.description }</td>
					</c:forEach>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>

	<script type="text/javascript">
	var clickInput = "";

	var keyNum=-1;
	
	function download() {
		var regInt = /^[0-9]*[1-9][0-9]*$/;
		var regFLT = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
		var flag = 1;
		var rid = $("#rid").val();
		var tempurl = window.config.root + "/report/downloadExecutionResultInXlsx?reportId=" + rid;
		tempurl += "&params={";
		$(".pa").each(function(index, val) {
			var pro = $(this).attr("name");
			var val = $(this).val();
			var valId = $(this).attr("id");
			var tp = $("#tp_" + (index + 1)).val();
			if (val == null || val == "") {
				flag = 2;
			} 
			if(valId==undefined){
				tempurl += "'" + pro + "':'" + val + "',";
				}else{
					tempurl += "'" + pro + "':[";
					var vals = val.split(",");
					for(var i=0;i<vals.length;i++){
						if(i==0){
							//tempurl+="'"+vals[i]+"'";	
							if(tp==1||tp == 3){
								tempurl+=vals[i];		
							}else{
							tempurl+="'"+vals[i]+"'";	
							}
						}else{
							//tempurl+=",'"+vals[i]+"'";
							if(tp==1||tp == 3){
								tempurl+=","+vals[i];		
							}else{
							tempurl+=",'"+vals[i]+"'";
							}
						}
					}
					tempurl += "],";
				}
		});
		if (flag == 2) {
			alert("请填写查询条件");
		} else {
			var url = tempurl.substring(0, (tempurl.length - 1));
			url += "}";
			window.location.href = url;
		}
	}

	function createTable() {
		var msg = "";
		var regInt = /^[0-9]*[1-9][0-9]*$/;
		var regFLT = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
		var flag = 1;
		var rid = $("#rid").val();
		var tempurl = "reportCtl?goto=findReport&rid=" + rid;
		tempurl += "&params={";
		$(".pa").each(function(index, val) {
			var pro = $(this).attr("name");
			var val = $(this).val();
			var valId = $(this).attr("id");
			var tp = $("#tp_" + (index + 1)).val();
			if (val == null || val == "") {
				flag = 2;
				msg = "请填写查询条件";
			} 
			if(valId==undefined){
			tempurl += "'" + pro + "':'" + val + "',";
			}else{
				tempurl += "'" + pro + "':[";
				var vals = val.split(",");
				for(var i=0;i<vals.length;i++){
					if(i==0){
						if(tp==1||tp == 3){
							tempurl+=vals[i];		
						}else{
						tempurl+="'"+vals[i]+"'";	
						}
					}else{
						if(tp==1||tp == 3){
							tempurl+=","+vals[i];		
						}else{
						tempurl+=",'"+vals[i]+"'";
						}
					}
				}
				tempurl += "],";
			}
		});
		if (flag == 2) {
			alert(msg);
		} else {
			var url = tempurl.substring(0, (tempurl.length - 1));
			url += "}";
			$.post(url, function(data) {
				if(data.total!=0){
				var collength = $("#collength").val();
				$("#reportTable tbody").empty();
				var html = "";
				$.each(data.rows, function(index, val) {
					html += "<tr>";
					for (var i = 0; i < collength; i++) {
						html += "<td>" + val[i] + "</td>";
					}
					html += "</tr>"
				});
				$("#reportTable tbody").append(html);
				}else{
					alert("根据查询条件，暂无数据！");
				}
			})
		}
	}
	
	var tempV="";
	var tempI="";
	var priorInd=-1;

	function openSelect(val) {
		 tempV="";
		 tempI="";
		 priorInd=-1;
		$("#dlg_"+val).dialog('open').dialog('setTitle', "请选择").dialog('center');
		clickInput = val;
		$("#selectTB_"+val+" tr").each(function(index, vale){
			$(this).css({
				background : "white"
			});	
			$("#is_click_" + val+"_"+index).val(0);
		});
		//选中的行index
		var indexVal=$("#selindex_" + clickInput).val();
		var inputVal=$("#val_" + clickInput).val();
		//初始化 选中key，index
		tempV=inputVal;
		tempI=indexVal;
		var idxs = tempI.split(",");
		for (var i = 0; i < idxs.length; i++) {
			$("#tb_tr_" + val+"_"+(idxs[i])).css({
				background : "#FFE48D"
			})
			$("#is_click_" + val+"_"+(idxs[i])).val(1);
		}	
	}
	
	
	
    //val表格名称  val2行id  多选
	function selectRows(val,val2) {
		//行是否被选中
		var is_click = $("#is_click_" + val+"_"+val2).val();
		//被点击行的key
		var key = $("#key_" + val+"_"+val2).val();
		//选中的内容
		//var inputVal = $("#val_" + clickInput).val();
		//选中的行index
		//var indexVal=$("#selindex_" + clickInput).val();
		if (is_click == 0) {
			//选中状态
		  if(keyNum!=16||priorInd==-1){
			$("#tb_tr_" + val+"_"+val2).css({
				background : "#FFE48D"
			})
			//添加选中key
			if (tempV == "") {
				tempV = key;
			} else {
				tempV += "," + key;
			}
			//添加选中index
			if (tempI == ""&&tempI!="0") {
				tempI = val2;
			} else {
				tempI += "," + val2;
			}
			$("#is_click_" + val+"_"+val2).val(1);
			priorInd=val2;
		   }else{
			  if(priorInd<val2){
				  for(var i=priorInd;i<=val2;i++){
						var key2 = $("#key_" + val+"_"+i).val();
						$("#tb_tr_" + val+"_"+i).css({
							background : "#FFE48D"
						})
						//添加选中key
						if (tempV == "") {
							tempV = key2;
						} else {
							tempV += "," + key2;
						}
						//添加选中index
						if (tempI == ""&&tempI!="0") {
							tempI = i;
						} else {
							tempI += "," + i;
						}
						$("#is_click_" + val+"_"+i).val(1); 
				  }
			  }else{
				  for(var i=val2;i<=priorInd;i++){
					  var key2 = $("#key_" + val+"_"+i).val();
						$("#tb_tr_" + val+"_"+i).css({
							background : "#FFE48D"
						})
						//添加选中key
						if (tempV == "") {
							tempV = key2;
						} else {
							tempV += "," + key2;
						}
						//添加选中index
						if (tempI == ""&&tempI!="0") {
							tempI = i;
						} else {
							tempI += "," + i;
						}
						$("#is_click_" + val+"_"+i).val(1); 
				  }
			  }
		   }
		} else {
			//撤销选中
			$("#tb_tr_" +val+"_"+val2).css({
				background : "white"
			})
			//移除选中key
			var keys = tempV.split(",");
			var newInputVal = "";
			for (var i = 0; i < keys.length; i++) {
				if (keys[i] != key) {
					if (newInputVal == "") {
						newInputVal += keys[i];
					} else {
						newInputVal += "," + keys[i];
					}
				}
			}
			//移除选中index
			var idxs = tempI.split(",");
			var newIndexVal = "";
			for (var i = 0; i < idxs.length; i++) {
				if (idxs[i] != val2) {
					if (newIndexVal == "") {
						newIndexVal += idxs[i];
					} else {
						newIndexVal += "," + idxs[i];
					}
				}
			}
			$("#is_click_" + val+"_"+val2).val(0);
			tempV=newInputVal;
			tempI=newIndexVal;
			$("#selindex_" + clickInput).val(newIndexVal);
		}
	}
    
    //多选确定
    function selectSure(){
    	if(tempV==''){
    		$("#" + clickInput).val("");
    	}else{
    	    $("#" + clickInput).val("+");
    	}
    	$("#val_" + clickInput).val(tempV);
		$("#selindex_" + clickInput).val(tempI);
    	$("#dlg_"+clickInput).dialog('close');
    }
    
    //全选
    function allClick(name){
    	tempV='';
    	tempI='';
    	$("#selectTB_"+name+" tr").each(function(index, val){
			$(this).css({
				background : "#FFE48D"
			});	
			$("#is_click_" + name+"_"+index).val(1);
			var key = $("#key_" + name+"_"+index).val();
			if (tempV == "") {
				tempV = key;
			} else {
				tempV += "," + key;
			}
			if (tempI == ""&&tempI!="0") {
				tempI = index;
			} else {
				tempI += "," + index;
			}
		});	
    }
    
    //全不取消
    function cancelClick(name){
    	tempV='';
    	tempI='';
    	$("#selectTB_"+name+" tr").each(function(index, val){
			$(this).css({
				background : "white"
			});	
			$("#is_click_" + name+"_"+index).val(0);
		});	
    }
    
    //val表格名称  val2行id  单选
	function selectRow(val,val2) {
		//行是否被选中
		var is_click = $("#is_click_" + val+"_"+val2).val();
		//被点击行的key
		var key = $("#key_" + val+"_"+val2).val();
		//选中的内容
		var inputVal = $("#" + clickInput).val();
		//选中的行index
		var indexVal=$("#selindex_" + clickInput).val();
		if (is_click == 0) {
			//清空表单选中状态
			$("#selectTB_"+val+" tr").each(function(index, val){
				val.css({
					background : "white"
				});	
			});
			//选中状态
			$("#tb_tr_" + val+"_"+val2).css({
				background : "#FFE48D"
			})
			inputVal=key;
			indexVal=val2;
			$("#is_click_" + val+"_"+val2).val(1);
			$("#" + clickInput).val(inputVal);
			$("#val_" + clickInput).val(inputVal);
			$("#selindex_" + clickInput).val(indexVal);
		} else {
			//撤销选中
			$("#tb_tr_" +val+"_"+val2).css({
				background : "white"
			})
			//移除选中key
			var keys = inputVal.split(",");
			var newInputVal = "";
			for (var i = 0; i < keys.length; i++) {
				if (keys[i] != key) {
					if (newInputVal == "") {
						newInputVal += keys[i];
					} else {
						newInputVal += "," + keys[i];
					}
				}
			}
			//移除选中index
			var idxs = indexVal.split(",");
			var newIndexVal = "";
			for (var i = 0; i < idxs.length; i++) {
				if (idxs[i] != val) {
					if (newIndexVal == "") {
						newIndexVal += idxs[i];
					} else {
						newIndexVal += "," + idxs[i];
					}
				}
			}
			$("#is_click_" + val+"_"+val2).val(0);
			$("#" + clickInput).val(newInputVal);
			$("#val_" + clickInput).val(newInputVal);
			$("#selindex_" + clickInput).val(newIndexVal);
		}
	}
    
	//发送邮件
	function outEmail() {
		var regInt = /^[0-9]*[1-9][0-9]*$/;
		var regFLT = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
		var flag = 1;
		var rid = $("#rid").val();
		var tempurl = window.config.root + "/report/sendEmailWithExecutionResultInXlsx?reportId=" + rid;
		tempurl += "&params={";
		$(".pa").each(function(index, val) {
			var pro = $(this).attr("name");
			var val = $(this).val();
			var valId = $(this).attr("id");
			var tp = $("#tp_" + (index + 1)).val();
			if (val == null || val == "") {
				flag = 2;
			} 
			if(valId==undefined){
				tempurl += "'" + pro + "':'" + val + "',";
				}else{
					tempurl += "'" + pro + "':[";
					var vals = val.split(",");
					for(var i=0;i<vals.length;i++){
						if(i==0){
							//tempurl+="'"+vals[i]+"'";	
							if(tp==1||tp == 3){
								tempurl+=vals[i];		
							}else{
							tempurl+="'"+vals[i]+"'";	
							}
						}else{
							//tempurl+=",'"+vals[i]+"'";
							if(tp==1||tp == 3){
								tempurl+=","+vals[i];		
							}else{
							tempurl+=",'"+vals[i]+"'";
							}
						}
					}
					tempurl += "],";
				}
		});
		if (flag == 2) {
			alert("请填写查询条件");
		} else {
			var url = tempurl.substring(0, (tempurl.length - 1));
			url += "}";
			window.location.href = url;
		}
	}
    
    
    
	function keyDown(e) {  
        var keycode = e.which;   
        keyNum=keycode;
	} 
	document.onkeydown = keyDown;
	
	function keyUp(e) {
		keyNum=-1; 
	} 
	document.onkeyup = keyUp;
	
	function resetTable(){
		var rid=$("#rid").val();
		$(".pa").each(function(index, val) {
             $(this).val('');
         	var pro = $(this).attr("name");
         	$("#selindex_"+pro).val('');
         	$("#"+pro).val('');
		});
		$("#reportTable tbody").empty();
	}
    
</script>


</body>
</html>