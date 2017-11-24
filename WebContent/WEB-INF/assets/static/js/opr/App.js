$(function() {
    var index = layer.open({
        type: 1,
        content: $('#test7'),
        area: 'auto',
        title: '激活态->订单开始（5秒倒计时）'
    });
    setTimeout("closeLayer(" + index + ")", 5000);
});

function openExcepiton() {
    var wid = $("#worksheetIsWorking").val();
    $.post('worksheetdefatil?goto=getWorksheet&wid=' + wid,
    function(data) {
        $("#worksheetDetailId").html("");
        $("#rollBackId").html("");
        $.each(data,
        function(index, item) {
            var name = data[index].name;
            var id = data[index].id;
            $("#worksheetDetailId").html($("#worksheetDetailId").html() + "<input type='checkbox' name='worksheetDetailId'value=" + id + " checked='checked'/>" + name);
            $("#rollBackId").html($("#rollBackId").html() + "<input type='checkbox' name='rollBackId'value=" + id + " checked='checked'/>" + name);
        })
    });
    layer.open({
        type: 1,
        content: $('#test'),
        area: 'auto',
        title: '异常处理',
        btn: '确定',
        yes: function(index, layero) {
            var index = layer.confirm('确认提交？', {
                btn: ['是', '否']
                // 按钮
            },
            function() {
                $('#exceptionfm').form('submit', {
                    url: 'worksheetdefatil?goto=saveExcption',
                    onSubmit: function() {
                        return $(this).form('validate');
                    },
                    success: function(r) {
                        layer.open({
                            content: '异常提交成功，操作流程已回滚！',
                            btn: '我知道了',
                            yes: function(index, layero) {
                                window.location.href = "app";
                            }
                        });
                    }
                })
            },
            function() {
                closeLayer(index);
            });
        }
    });
}
function openOthers() {
    $.post("worksheet?goto=findWorking",
    function(data) {
        var tableHTML = "";
        tableHTML += "<table body='1' cellpadding='1' cellspacing='1' style='width: 300px; height: 100px;'>";
        tableHTML += "<tr style='height: 25px;'><td>订单号</td><td>型号</td><td>工艺师</td><td></td></tr>";
        if (data != null) {
            $.each(data,
            function(index, e) {
                var code = e.slaveCommande.mainCommande.codeId;
                var type = e.slaveCommande.model.name;
                var opr = e.opr.name;
                tableHTML += "<tr style='height: 25px;'><td>" + code + "</td><td>" + type + "</td><td>" + opr + "</td><td><a href='javascript:lookMain(" + e.id + ")'>查看</a></td></tr>";
            });
        }
        tableHTML += "</table>";
        $("#test2").html(tableHTML);
    });
    layer.open({
        type: 1,
        content: $('#test2'),
        area: 'auto',
        title: '其他订单'
    });
}

function openCell() {
    layer.open({
        type: 1,
        content: $('#test3'),
        area: 'auto',
        title: '呼叫'
    });
}

function chang(val) {
    var id = val.value;
    var code = $("#wcodeMain").val();
    var ename = $("#ename").val();
    var dmname = $("#dmname").val();
    if (code == id) {
        var msg = "订单号:" + code + "由 工艺师:" + ename + "完成 工序:" + dmname;
        layer.confirm(msg, {
            btn: ['是', '否']
            // 按钮
        },
        function() {
            var url = "worksheetdefatil?goto=working";
            if ($('#fm1').form('validate')) {
                $.post(url, $("#fm1").serialize(),
                function() {
                    var index = layer.open({
                        content: "完成订单",
                        btn: '我知道了',
                        yes: function(index, layero) {
                            window.location.href = "app";
                        }
                    }) setTimeout("goPage()", 3000);
                });
            }
        })
    } else {
        $.post('worksheet?goto=findByCode', {
            id: id
        },
        function(data) {
            if (data.type == 0) {
                var index = layer.open({
                    type: 0,
                    content: '无法识别代码,请先扫描订单条码或者工牌,并按后续提示操作',
                    area: 'auto',
                    title: '无效登录（3秒倒计时）'
                });
                setTimeout("closeLayer(" + index + ")", 3000);
            } else if (data.type == 1) {
                $('#wcode').html(data.object.slaveCommande.mainCommande.codeId + "," + data.object.slaveCommande.codeId);
                $('#wid').val(data.object.id);
                var index = layer.open({
                    type: 1,
                    content: $('#test5'),
                    area: 'auto',
                    title: '中间态->继续扫描(5秒倒计时)'
                });
                setTimeout("closeLayer(" + index + ")", 5000);
            } else if (data.type == 2) {
                $('#ecode').html(data.object.name);
                $('#eid').val(data.object.id);
                var index = layer.open({
                    type: 1,
                    content: $('#test6'),
                    area: 'auto',
                    title: '中间态->继续扫描(5秒倒计时)'
                });
                setTimeout("closeLayer(" + index + ")", 5000);
            } else if (data.type == 3) {
                var index = layer.open({
                    type: 0,
                    content: '其他工作台正在操作此订单!',
                    area: 'auto',
                    title: '无效登录（3秒倒计时）'
                });
                setTimeout("closeLayer(" + index + ")", 3000);
            }
        })
    }
}

function validEcode(value) {
    var id = $('#wid').val();
    var code = value.value;
    $.post('worksheet?goto=findByCode2', {
        id: id,
        code: code,
        type: 1
    },
    function(data) {
        if (data.type == 1) {
            var url = "worksheetdefatil?goto=workingTemp";
            $.post(url, $("#fm1").serialize(),
            function() {
                window.location.href = "worksheet?goto=appView&did=" + data.detail.id + "&eid=" + data.employee.id;
            });
        } else if (data.type == 2) {
            var index = layer.confirm('操作人员不对，是否需要更换操作人员？', {
                btn: ['替换,并返回订单', '不替换,并返回订单', '取消'],
                btn3: function(i, l) {
                    closeLayer(index);
                }
                // 按钮
            },
            function() {
                var did = data.detail.id;
                var eid = data.employee.id;
                $.post('worksheet?goto=updateEMP', {
                    did: did,
                    eid: eid
                },
                function(data) {
                    window.location.href = "worksheet?goto=appView&did=" + did + "&eid=" + eid;
                })
            },
            function() {
                window.location.href = "worksheet?goto=appView3&did=" + did;
            });
        } else if (data.type == 3) {
            var index = layer.open({
                type: 0,
                content: '其他工作台正在操作此订单!',
                area: 'auto',
                title: '无效登录（3秒倒计时）'
            });
            setTimeout("closeLayer(" + index + ")", 3000);
        } else {
            var index = layer.open({
                type: 0,
                content: '无法识别代码,请先扫描订单条码或者工牌,并按后续提示操作',
                area: 'auto',
                title: '无效登录（3秒倒计时）'
            });
            setTimeout("closeLayer(" + index + ")", 3000);
        }
    });
}

function validWcode(value) {
    var id = $('#eid').val();
    var code = value.value;
    $.post('worksheet?goto=findByCode2', {
        id: id,
        code: code,
        type: 2
    },
    function(data) {
        if (data.type == 1) {
            var url = "worksheetdefatil?goto=workingTemp";
            $.post(url, $("#fm1").serialize(),
            function() {
                window.location.href = "worksheet?goto=appView&did=" + data.detail.id + "&eid=" + data.employee.id;
            });
        } else if (data.type == 2) {
            var index = layer.confirm('操作人员不对，是否需要更换操作人员？', {
                btn: ['替换,并返回订单', '不替换,并返回订单', '取消'],
                btn3: function(i, l) {
                    closeLayer(index);
                }
                // 按钮
            },
            function() {
                var did = data.detail.id;
                var eid = data.employee.id;
                $.post('worksheet?goto=updateEMP', {
                    did: did,
                    eid: eid
                },
                function(data) {
                    window.location.href = "worksheet?goto=appView&did=" + did + "&eid=" + eid;
                })
            },
            function() {
                window.location.href = "worksheet?goto=appView3&did=" + did;
            });
        } else if (data.type == 3) {
            var index = layer.open({
                type: 0,
                content: '其他工作台正在操作此订单!',
                area: 'auto',
                title: '无效登录（3秒倒计时）'
            });
            setTimeout("closeLayer(" + index + ")", 3000);
        } else {
            var index = layer.open({
                type: 0,
                content: '无效登录',
                area: 'auto',
                title: '无效登录（3秒倒计时）'
            });
            setTimeout("closeLayer(" + index + ")", 3000);
        }
    });
}

function closeLayer(index) {
    layer.close(index);
}

function goPage() {
    window.location.href = "app";
}

function lookMain(wid) {
    $.post('worksheet?goto=clickWork', {
        wid: wid
    },
    function(data) {
        if (data.type == 1) {
            var wid = data.object.id;
            var url = "worksheetdefatil?goto=workingTemp";
            $.post(url, $("#fm1").serialize(),
            function() {
                window.location.href = "worksheet?goto=appView3&did=" + wid;
            });
        } else {
            var index = layer.open({
                type: 0,
                content: '其他工作台正在操作此订单!',
                area: 'auto',
                title: '无效登录（3秒倒计时）'
            });
            setTimeout("closeLayer(" + index + ")", 3000);
        }
    })
}