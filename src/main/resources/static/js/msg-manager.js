/**
 * Created by tengj on 2017/4/10.
 */
var grid_selector = "#jqGrid";
var pager_selector = "#jqGridPager";
var rowNum = 10; 			//每页显示记录数
var task = null;			//任务（新增或编辑）
var loading;

/*//展示消息，弹出展示窗口
function showSysMsg(base) {
    var rowData = $(grid_selector).jqGrid('getRowData',$(base).parent().parent().attr("id"));
    $("#avatar").attr('src',"/images/avatars/profile-pic.jpg");
    if (rowData.amatar.length>0){
        $("#avatar").attr('src',rowData.amatar);
    }
    $("#fansnum").text(rowData.fansnum);
    $("#attentionsnum").text(rowData.attentionnum);
    $("#showaccount").text(rowData.account);
    $("#showname").text(rowData.name);
    $("#shownickname").text(rowData.nickname);
    $("#showborndata").text(rowData.borndata);
    $("#showsex").text(rowData.gender);
    $("#showemail").text(rowData.email);
    $("#showidcard").text(rowData.idcard);
    $("#showmobilenum").text(rowData.phonenum);
    $("#showpersonalsign").text(rowData.personalsign);
    $("#showgrade").text(rowData.grade);
    $("#showcreatetime").text(rowData.createtime);
    $("#showarole").text(rowData.role);
    $("#userProfileModal").modal({
        keyboard : false,
        show : true,
        backdrop : "static"
    });
}*/
$(function(){
    $(window).resize(function(){
        $(grid_selector).setGridWidth($(window).width()*0.95);
    });

    $(grid_selector).jqGrid({
        url:"/mamager/sysmessage/queryUserList",
        datatype: "json",
        mtype: 'POST',
        height:window.screen.height-550,
        colModel: [
            { label: 'id', name: 'sm_id', width: 75,hidden:true},
            { label: '标题', name: 'sm_title', width: 100 },
            { label: '内容', name: 'sm_content', width: 100 },
            { label: '发件人', name: 'sm_from', width: 100 },
            { label: '收件人', name: 'sm_to', width: 120 },
            { label: '', name: 'owner', width: 130 ,hidden:true},
            { label: '', name: 'intent', width: 150 ,hidden:true},
            { label: '', name: 'isRead', width: 120 ,hidden:true},
            { label: '发送时间', name: 'time', width: 100 },
            { label: '发送状态', name: 'result', width: 100 },
            { label: '用户id', name: 'uid', width: 100 ,hidden:true},
          /*  { label: '查看', name: 'watch', width: 100,
                formatter: function(cellvalue, options, cell){
                    return '<a class="btn btn-purple btn-sm " target="_blank" onclick="showSysMsg(this);"><i class="fa fa-cog  fa-spin" aria-hidden="true"></i>点我</a>';
                }},
            { label: '', name: '', width: 0.01}*/
        ],
        pager: pager_selector,
        rowNum:rowNum,
        rowList:[10,30,45], //可调整每页显示的记录数
        viewrecords: true,//是否显示行数
        altRows: true,  //设置表格 zebra-striped 值
        gridview: true, //加速显示
        multiselect: true,//是否支持多选
        multiselectWidth: 40, //设置多选列宽度
        multiboxonly: true,
        shrinkToFit:true, //此属性用来说明当初始化列宽度时候的计算类型，如果为ture，则按比例初始化列宽度。如果为false，则列宽度使用colModel指定的宽度
        forceFit:true, //当为ture时，调整列宽度不会改变表格的宽度。当shrinkToFit为false时，此属性会被忽略
        autowidth: true,
        loadComplete : function() {
            var table = this;
            setTimeout(function(){
                updatePagerIcons(table);
            }, 0);
        },
        gridComplete: function () {
            // 防止水平方向上出现滚动条
            removeHorizontalScrollBar();
        },
        jsonReader: {//jsonReader来跟服务器端返回的数据做对应
            root: "rows",   //包含实际数据的数组
            total: "total", //总页数
            records:"records", //查询出的记录数
            repeatitems : false //指明每行的数据是可以重复的，如果设为false，则会从返回的数据中按名字来搜索元素，这个名字就是colModel中的名字
        },
        emptyrecords: '没有记录!',
        loadtext: '正在查询服务器数据...'
    });

    //设置分页按钮组
    $(grid_selector).jqGrid('navGrid',pager_selector,
        {
            edit: false,
            // edittitle:'修改',
            // edittext:'修改',
            // editicon : 'icon-pencil blue',
            // editfunc :editUser,
            add: false,
            // addtitle:'新增',
            // addtext:'新增',
            // addicon : 'icon-plus-sign purple',
            // addfunc :addUser,
            del: false,
            // deltitle:'删除',
            // deltext:'删除',
            // delicon : 'icon-trash red',
            // delfunc:delUser,
            refresh: true,
            refreshicon : 'icon-refresh green',
            beforeRefresh:refreshData,
            search: false,
            view: false,
            alertcap:"警告",
            alerttext : "请选择需要操作的用户!"
        }
    );

    //查询点击事件
    $("#queryMsgBtn").click(function(){
        var qryTitle=$("#qryTitle").val();
        var qryTo=$("#qryTo").val();
        $(grid_selector).jqGrid('setGridParam',{
            postData:{title:qryTitle,to:qryTo},
            //search: true,
            page:1
        }).trigger("reloadGrid");
    });

    //发送消息，弹出新增窗口
    $("#sendMessageBtn").click(function () {
       task = "add";
        initSendData();
        $("#sendMessageModal").modal({
            keyboard : false,
            show : true,
            backdrop : "static"
        });
    });
    //初始化发送消息数据
    function initSendData(){
        $("input[name='type']").get(0).checked=true;
        $("#type").attr("checked","checked");
        $('#sendto').val("");
        $('#sendto').removeAttr("disabled")
        $('#sendcontent').val("");
        $('#sendtitle').val("");
    }
    //保存修改消息
    $('#updateMsgBtn').click(function(){
        saveUpdateMsg();
    });
    //保存发送消息
    $('#sendMsgBtn').click(function(){
        saveSendMsg();
    });
    /**
     * 保存发送消息
     */
    function saveSendMsg(){
        var sendtitle = $('#sendtitle').val();
        var sendcontent = $('#sendcontent').val();
        // var sendfrom = $('#sendfrom').val();
        var sendto = $('#sendto').val();
        // var senduid = $('#senduid').val();
        $.ajax({
            url: "/mamager/sysmessage/add",
            cache: false,
            data : {
                "sendtitle":sendtitle,
                "sendcontent":sendcontent,
                "sendto":sendto
            },
            type : 'post',
            beforeSend: function () {
                //alert("beforeSend");
                // 禁用按钮防止重复提交
                $('#sendMsgBtn').attr({ disabled: "disabled"});
            },
            success: function(result){
                if(result.flag == true){
                    $.messager.alert('温馨提示',result.message);
                    refreshData();
                    $('#sendMsgBtn').removeAttr("disabled");
                    $("#sendMessageModal").modal('hide');
                }else{
                    $.messager.alert('温馨提示',result.message);
                }
            },
            complete: function () {
                 $('#sendMsgBtn').removeAttr("disabled");
            },
            error: function (data) {
                console.info("error: " + data.responseText);
            }
        });
    }
    //修改消息，弹出修改窗
    $("#modifyMsgBtn").click(function () {
        var rows=$(grid_selector).getGridParam('selarrrow');
        if(rows==0){
            // $.messager.alert("温馨提示","请选择一行记录！");
            layer.msg('请选择一行记录！', {icon: 7,time: 2000}); //2秒关闭（如果不配置，默认是3秒）
            return;
        }else if(rows.length>1){
            // $.messager.alert("温馨提示","不能同时修改多条记录！");
            layer.msg('不能同时修改多条记录！', {icon: 7,time: 2000}); //2秒关闭（如果不配置，默认是3秒）
            return;
        }else{
            initUpdateData();
            $("#updateSysMsgModal").modal({
                keyboard : false,
                show : true,
                backdrop : "static"
            });
        }
    });

    //删除消息方法 选择多个的话，行id用逗号隔开比如 3,4
    $("#deleteMsgBtn").click(function () {
        var rows = $(grid_selector).jqGrid("getGridParam", "selarrrow");
        var uids=new Array(rows.length);
        for(var i=0;i<rows.length;i++){
            //行数据
            var rowData = $(grid_selector).jqGrid('getRowData',rows[i]);
            //colModel为uid
            uids[i] = rowData.sm_id;
            // alert(uids[i]);
        }
        if(rows.length>0){
            $.messager.confirm("温馨提示", "是否确定删除所选记录？", function() {
                $.ajax({
                    url:"/mamager/sysmessage/delete",
                    cache: false,
                    type:"post",
                    data:{"ids": uids.join(",")},
                    beforeSend : function(){
                        loading=layer.load("正在删除中...");
                    },
                    success:function(result){
                        $.messager.alert(result.message);
                        refreshData();
                    },error:function(){
                        $.messager.alert("温馨提示","请求错误!");
                    },
                    complete : function(){
                        layer.close(loading);
                    }
                });
            });
        }else{
            //两种风格的提示,layer或者messager自己选择一种用即可。
            // $.messager.alert("温馨提示","至少选择一行记录！");
            layer.msg('至少选中一行记录！', {icon: 7,time: 2000}); //2秒关闭（如果不配置，默认是3秒）
        }
    })
    $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
    $('input:radio[name="type"]').change(function(){
        if($('input:radio[name="type"]:first').is(":checked")){
            $("#sendto").removeAttr("disabled");
        }else{
            $('#sendto').attr("disabled",true);
            $('#sendto').val("all");
        }
    });
});

function removeHorizontalScrollBar() {
    $("div.ui-state-default.ui-jqgrid-hdiv.ui-corner-top").css("width", parseInt($("div.ui-state-default.ui-jqgrid-hdiv.ui-corner-top").css("width")) + 1 + "px");
    $(grid_selector).closest(".ui-jqgrid-bdiv").css("width", parseInt($(grid_selector).closest(".ui-jqgrid-bdiv").css("width")) + 1 + "px");
}

//发送消息对话框取消点击事件
$('#cancelSendMsgBtn').click(function(){
    $("#sendMessageModal").modal('hide');
});


//修改消息对话框取消点击事件
$('#cancelUpdateMsg').click(function(){
    $("#updateSysMsgModal").modal('hide');
});

//初始化修改用户数据
function initUpdateData(){
    var rows=$(grid_selector).getGridParam('selarrrow');
    var data = $(grid_selector).jqGrid('getRowData', rows[0]);
    $("#smid").val(data.sm_id);
    $('#title').val(data.sm_title);
    $('#content').val(data.sm_content);
    $('#from').val(data.sm_from);
    $('#to').val(data.sm_to);
    $('#time').val(data.time);
    $('#result').val(data.result);
    $('#uid').val(data.uid);

}

/**
 * 修改消息
 */
function saveUpdateMsg(){
    var submit_option= {
        url: "/mamager/sysmessage/update",//默认是form action
        method:"POST",
        success: function (result) {
            if(result.flag == true) {
                $.messager.alert('温馨提示', result.message);
                refreshData();
                $("#updateSysMsgModal").modal('hide');
            }else {
                $.messager.alert('温馨提示',result.message);
                $("#updateSysMsgModal").modal('hide');
            }
        },error:function(){
            $.messager.alert('服务器繁忙，请稍后再试...');
            $("#updateSysMsgModal").modal('hide');
        }
    }
    $("#updateSysMsgForm").ajaxSubmit(submit_option);
}

function refreshData(){
    $(grid_selector).jqGrid('setGridParam',{
        postData:{title:null,to:null},
        page:1
    }).trigger("reloadGrid");
}



//这个是分页图标，必须添加
function updatePagerIcons(table) {
    var replacement =
        {
            'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
            'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
            'ui-icon-seek-next' : 'icon-angle-right bigger-140',
            'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
        };
    $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
        var icon = $(this);
        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
        console.info($class);
        if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
    });
}
