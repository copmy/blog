$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/pays/list',
        datatype: "json",
        colModel: [
            {label: '订单号', name: 'out_trade_no', index: 'out_trade_no', width: 50, key: true, hidden: false},
            {label: '支付类型', name: 'payType', index: 'payType', width: 50},
            {label: '支付名称', name: 'payName', index: 'payName', width: 120},
            {label: '支付状态', name: 'subject', index: 'subject', width: 50},
            {label: '交易凭证号', name: 'trade_no', index: 'trade_no', width: 120},
            {label: '支付金额', name: 'total_amount', index: 'total_amount', width: 100},
            {label: '用户ID', name: 'adminUserId', index: 'adminUserId', width: 100},
            {label: '付款时间', name: 'createTime', index: 'createTime', width: 100},
        ],
        height: 560,
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });
});

/**
 * jqGrid重新加载
 */
function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

function PayAdd() {
    reset();
    $('.modal-title').html('支付记录添加');
    $('#payModal').modal('show');
}

//绑定modal上的保存按钮
$('#saveButton').click(function () {
    var out_trade_no = $("#out_trade_no").val();
    var payType = $("#payType").val();
    var payName = $("#payName").val();
    var subject = $("#subject").val();
    var trade_no = $("#trade_no").val();
    var total_amount = $("#total_amount").val();
    var adminUserId = $("#adminUserId").val();
    if (!validCN_ENString2_18(payName)) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("请输入符合规范的名称！");
        return;
    }

    var params = $("#payForm").serialize();
    var url = '/admin/pays/save';
    // url = '/admin/pays/update';
    // if (out_trade_no != null && out_trade_no > 0) {
    //     url = '/admin/pays/update';
    // }
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: params,
        success: function (result) {
            if (result.resultCode == 200 && result.data) {
                $('#payModal').modal('hide');
                swal("保存成功", {
                    icon: "success",
                });
                reload();
            }
            else {
                $('#payModal').modal('hide');
                swal("保存失败", {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });

});

//绑定modal2上的保存按钮
$('#saveButton2').click(function () {
    var out_trade_no = $("#out_trade_no2").val();
    var payType = $("#payType2").val();
    var payName = $("#payName2").val();
    var subject = $("#subject2").val();
    var trade_no = $("#trade_no2").val();
    var total_amount = $("#total_amount2").val();
    var adminUserId = $("#adminUserId2").val();
    if (!validCN_ENString2_18(payName)) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("请输入符合规范的名称！");
        return;
    }


    var params = $("#payForm2").serialize();
    url = '/admin/pays/update';
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        data: params,
        success: function (result) {
            if (result.resultCode == 200 && result.data) {
                $('#payModal2').modal('hide');
                swal("保存成功", {
                    icon: "success",
                });
                reload();
            }
            else {
                $('#payModal2').modal('hide');
                swal("保存失败", {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });

});


function PayEdit() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    reset();
    //请求数据
    $.get("/admin/pays/info/" + id, function (r) {
        if (r.resultCode == 200 && r.data != null) {
            //填充数据至modal
            $("#payName2").val(r.data.payName);
            $("#subject2").val(r.data.subject);
            $("#trade_no2").val(r.data.trade_no);
            $("#total_amount2").val(r.data.total_amount);
            $("#adminUserId2").val(r.data.adminUserId);
            //根据原linkType值设置select选择器为选中状态
            if (r.data.payType == 1) {
                $("#payType2 option:eq(1)").prop("selected", 'selected');
            }
            if (r.data.payType == 2) {
                $("#payType2 option:eq(2)").prop("selected", 'selected');
            }
        }
    });
    $('.modal-title').html('支付记录修改');
    $('#payModal2').modal('show');
    $("#out_trade_no2").val(id);
}

function deletePay() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要删除数据吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/pays/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.resultCode == 200) {
                            swal("删除成功", {
                                icon: "success",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            swal(r.message, {
                                icon: "error",
                            });
                        }
                    }
                });
            }
        }
    );
}

function reset() {
    $("#out_trade_no").val('');
    $("#payType").val('');
    $("#payName").val('');
    $("#subject").val('');
    $("#trade_no").val(0);
    $("#total_amount").val(0);
    $("#adminUserId").val('');
    $('#edit-error-msg').css("display", "none");
    $("#payType option:first").prop("selected", 'selected');
}