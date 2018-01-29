/**
 * Created by tengj on 2017/4/10.
 */
var grid_selector = "#jqGrid";
var pager_selector = "#jqGridPager";
var rowNum = 10; 			//每页显示记录数
var task = null;			//任务（新增或编辑）
var loading;
$(function(){
    $(window).resize(function(){
        $(grid_selector).setGridWidth($(window).width()*0.95);
    });
    $(grid_selector).jqGrid({
        url:"/manager/livemanager/queryFansList",
        datatype: "json",
        mtype: 'POST',
        height:window.screen.height-550,
        colModel: [
            { label: 'uid', name: 'uid', width: 75},
            { label: '用户账户', name: 'account', width: 200 },
            { label: '粉丝账户', name: 'fs_account', width: 200 },
            { label: '粉丝昵称', name: 'fs_nickname', width: 200},
            { name: 'dsource_alarm', index: 'dsource_alarm', width: 30,
                align: "center", sortable: false, editable: false, formatter: alarmFormatter },
            { label: '取关', name: 'opt', width: 200,formatter: function(cellvalue, options, cell){
                return '<a class="btn btn-purple btn-sm" onclick="cancelFans(this);"' +
                    'target="_blank">' + '<i class="fa fa-cog  fa-spin" aria-hidden="true"></i>取关</a>';
            }},
            { label: '', name: '', width: 0.1},
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
    $("#queryFansBtn").click(function(){
        var qryAccount=$("#qryAccount").val();
        var qryId=$("#qryId").val();
        $(grid_selector).jqGrid('setGridParam',{
            postData:{account:qryAccount,uid:qryId},
            //search: true,
            page:1
        }).trigger("reloadGrid");
    });
    //新增粉丝，弹出新增窗口
    $("#addFansBtn").click(function () {
        initData();
        $("#addFansModal").modal({
            keyboard : false,
            show : true,
            backdrop : "static"
        });

    });
    //编辑对话框取消点击事件
    $('#candelSaveFansBtn').click(function(){
        $("#addFansModal").modal('hide');
    });
    //保存教程
    $('#saveFansBtn').click(function(){
        saveFans();
    });
});
//自定义报警列格式
function alarmFormatter(cellvalue, options, rowdata)
{
    if (cellvalue != "0")
        return '<img class="alarmimg" src="http://127.0.0.1:8080/fitnesslive/img/amatar/100000d84f5cb1-db9c-4ff1-b49b-607772e84409.jpg" alt="' + cellvalue + '" />';
    else
        return '<img class="alarmimg" src="http://127.0.0.1:8080/fitnesslive/img/amatar/100000d84f5cb1-db9c-4ff1-b49b-607772e84409.jpg" alt="' + cellvalue + '" />';
}
/**
 *取消关注
 */
function cancelFans(base){
    var rowData = $(grid_selector).jqGrid('getRowData',$(base).parent().parent().attr("id"));
    $.messager.confirm("温馨提示", "是否确定取消关注？", function() {
        $.ajax({
            url:"/manager/livemanager/delete",
            cache: false,
            type:"post",
            data:{
                "useraccount":rowData.account,
                "fansaccount":rowData.fs_account,
            },
            beforeSend : function(){
                loading=layer.load("取消关注中...");
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
}
function removeHorizontalScrollBar() {
    $("div.ui-state-default.ui-jqgrid-hdiv.ui-corner-top").css("width", parseInt($("div.ui-state-default.ui-jqgrid-hdiv.ui-corner-top").css("width")) + 1 + "px");
    $(grid_selector).closest(".ui-jqgrid-bdiv").css("width", parseInt($(grid_selector).closest(".ui-jqgrid-bdiv").css("width")) + 1 + "px");
}
//初始化数据
function initData(){
    $('#useraccount').val("");
    $('#fansaccount').val("");
}
/**
 * 保存粉丝
 */
function saveFans(){
    var useraccount = $('#useraccount').val();
    var fansaccount = $('#fansaccount').val();
    $.ajax({
        url: "/manager/livemanager/add",
        cache: false,
        dataType:'json',
        data : {
            "useraccount":useraccount,
            "fansaccount": fansaccount,
        },
        type : 'post',
        beforeSend: function () {
            // 禁用按钮防止重复提交
            $('#saveFansBtn').attr({ disabled: "disabled"});
        },
        success: function(result){
            if(result.flag == true){
                $.messager.alert('温馨提示',result.message);
                $("#addFansModal").modal('hide');
                refreshData();
            }else{
                $.messager.alert('温馨提示',result.message);
            }
        },
        complete: function () {
            $('#saveFansBtn').removeAttr("disabled");
        },
        error: function (data) {
            console.info("error: " + data.responseText);
        }
    });
}
function refreshData(){
    $(grid_selector).jqGrid('setGridParam',{
        postData:{account:null,uid:null},
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
