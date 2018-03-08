/**
 * Created by wotrd on 2017/4/10.
 */
var grid_selector = "#jqGrid";
var pager_selector = "#jqGridPager";
var rowNum = 10; 			//每页显示记录数
var loading;
var player;
var ws;
var timeInterval;
function playVideo(url) {
    player = cyberplayer("playercontainer").setup({
        width: 400,
        height: 550,
        file: url, // <—rtmp直播地址
        autostart: true,
        stretching: "uniform",
        volume: 100,
        controls: true,
        rtmp: {
            reconnecttime: 5, // rtmp直播的重连次数
            bufferlength: 1 // 缓冲多少秒之后开始播放 默认1秒
        },
        ak: "e98411fba665450d8ce194dae1d4d733" // 公有云平台注册即可获得accessKey
    });
};
//关闭直播modal也是取消轮训事件
$('#watchLiveModal').on('hide.bs.modal', function () {
    // 执行一些清理动作...
    $("#chatmessage").text("");
    clearTimeout(timeInterval);
    ws.close();
});
//发送系统消息
function sendMsg() {
    var msg={from:'server',content:$("#sendmsg").val(),intent:1};
    ws.send(JSON.stringify(msg));
    $("#sendmsg").val("");
}
//websocket连接
function wbConnect(url) {
    ws = new WebSocket(url);
    ws.onopen = function(evt) {
        console.log("Connection open ...");
        ws.send("Hello WebSockets!");
        timeInterval=setTimeout(function () {
            ws.send("");
        },0,2000);
    };
    ws.onmessage = function(evt) {
        try {
            var chatMsg=JSON.parse(evt.data);
            if (chatMsg.intent==1) {
                $("#chatmessage").append("<div><span style='background-color: #f1edff;padding-left:10px;padding-right:10px;border-radius:5px;margin: 5px;font-size: 26px'>"+chatMsg.from+ "</span>" +
                    "<span style='font-size: 26px'>:</span>&nbsp;&nbsp;" +
                    "<span style='background-color: #e1ddee;padding-left:5px;padding-right:5px;border-radius:5px;font-size: 22px'>"+chatMsg.content+ "</span></div>"
                );
                var content = document.getElementById('chatmessage');
                content.scrollTop = content.scrollHeight;
            }
        }
        catch(err){
            console.log(err);
        }
    };
    ws.onclose = function(evt) {
        console.log("Connection closed.");
        clearTimeout(timeInterval);
    };
}
//显示直播，弹出展示窗口
function showLiveVideo(base) {
    var rowData = $(grid_selector).jqGrid('getRowData',$(base).parent().parent().attr("id"));
    var url="rtmp://119.29.154.229:1935/live/"+rowData.account;
    playVideo(url);
    wbConnect("ws://localhost/websocket/"+rowData.account+"/admin/watchlive");
    $("#watchLiveModal").modal({
        keyboard : false,
        show : true,
        backdrop : "static"
    });
}
$(function(){
    $(window).resize(function(){
        $(grid_selector).setGridWidth($(window).width()*0.95);
    });
    $(grid_selector).jqGrid({
        url:"/manager/livemanager/getLiveUsers",
        datatype: "json",
        mtype: 'POST',
        height:window.screen.height-550,
        colModel: [
            { label: 'id', name: 'uid', width: 75,hidden:true},
            { label: 'amatar', name: 'amatar', width: 75,hidden:true},
            { label: 'password', name: 'password', width: 75,hidden:true},
            { label: 'borndata', name: 'borndata', width: 75,hidden:true},
            { label: 'fansnum', name: 'fansnum', width: 75,hidden:true},
            { label: 'attentionnum', name: 'attentionnum', width: 75,hidden:true},
            { label: 'borndata', name: 'borandata', width: 75,hidden:true},
            { label: 'personalsign', name: 'personalsign', width: 75,hidden:true},
            { label: 'grade', name: 'grade', width: 75,hidden:true},
            { label: 'createtime', name: 'createtime', width: 75,hidden:true},
            { label: 'id', name: 'uid', width: 75,hidden:true},
            { label: '账户', name: 'account', width: 100 },
            { label: '姓名', name: 'name', width: 100 },
            { label: '性别', name: 'gender', width: 100 },
            { label: '昵称', name: 'nickname', width: 120 },
            { label: '邮箱', name: 'email', width: 130 },
            { label: '身份证', name: 'idcard', width: 150 },
            { label: '手机号', name: 'phonenum', width: 120 },
            { label: '角色', name: 'role', width: 100,formatter:function (cellvalue,options,cell) {
                    if (cellvalue==1){
                        return '<p>管理员</p>';
                    }else{
                        return '<p>用户</p>';
                    }
                }},
            { label: '正在直播', name: 'islive', width: 100 ,formatter:function (cellvalue,options,cell) {
                    if (cellvalue==1){
                        return '<p>是</p>';
                    }else{
                        return '<p>否</p>';
                    }
                }},
            { label: '加入时间', name: 'createtime', width: 100 },
            { label: '查看', name: 'watch', width: 100,
                formatter: function(cellvalue, options, cell){
                    return '<a class="btn btn-purple btn-sm " target="_blank" onclick="showLiveVideo(this);"><i class="fa fa-cog  fa-spin" aria-hidden="true"></i>点我</a>';
                }},
            { label: '', name: '', width: 0.01}
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
            add: false,
            del: false,
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
    $("#queryLiveUserBtn").click(function(){
        var qryAccount=$("#qryAccount").val();
        var qryNickname=$("#qryNickname").val();
        $(grid_selector).jqGrid('setGridParam',{
            postData:{account:qryAccount,nickname:qryNickname},
            page:1
        }).trigger("reloadGrid");
    });
});

function removeHorizontalScrollBar() {
    $("div.ui-state-default.ui-jqgrid-hdiv.ui-corner-top").css("width", parseInt($("div.ui-state-default.ui-jqgrid-hdiv.ui-corner-top").css("width")) + 1 + "px");
    $(grid_selector).closest(".ui-jqgrid-bdiv").css("width", parseInt($(grid_selector).closest(".ui-jqgrid-bdiv").css("width")) + 1 + "px");
}

function refreshData(){
    $(grid_selector).jqGrid('setGridParam',{
        postData:{account:null,nickname:null},
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
