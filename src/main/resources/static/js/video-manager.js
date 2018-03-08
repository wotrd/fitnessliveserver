/**
 * Created by tengj on 2017/4/10.
 */
var grid_selector = "#jqGrid";
var pager_selector = "#jqGridPager";
var rowNum = 10; 			//每页显示记录数
var loading;
$(function(){
    $(window).resize(function(){
        $(grid_selector).setGridWidth($(window).width()*0.95);
    });
    $(grid_selector).jqGrid({
        url:"/manager/videomanager/querySysVideoList",
        datatype: "json",
        mtype: 'POST',
        height:window.screen.height-550,
        colModel: [
            { label: 'sysid', name: 'uv_id', width: 75,hidden:true},
            { label: 'id', name: 'uid', width: 75,hidden:true},
            { label: '图像', name: 'uv_thumbnailurl', width: 60,formatter: alarmFormatter },
            { label: '标题', name: 'uv_title', width: 75},
            { label: '类型', name: 'uv_type', width: 75,formatter: function(cellvalue, options, cell){
                if (cellvalue==1){
                    return '<p>用户</p>';
                }else {
                    return '<p>系统</p>';
                }
            }},
            { label: '上传时间', name: 'uv_uploadtime', width: 75},
            { label: '观看', name: '', width: 100,
                formatter: function(cellvalue, options, cell){
                    return '<p onclick="showVideo(this);">观看</p>';
                }},
            { label: '', name: 'uv_videourl', width: 0.01}
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
    $("#querySysVideoBtn").click(function(){
        var qryTitle=$("#qryTitle").val();
        var qryType=$("#qryType").val();
        $(grid_selector).jqGrid('setGridParam',{
            postData:{title:qryTitle,type:qryType},
            page:1
        }).trigger("reloadGrid");
    });
    //删除视频方法 选择多个的话，行id用逗号隔开比如 3,4
    $("#deleteVideoBtn").click(function () {
        var rows = $(grid_selector).jqGrid("getGridParam", "selarrrow");
        var uv_ids=new Array(rows.length);
        for(var i=0;i<rows.length;i++){//行数据
            var rowData = $(grid_selector).jqGrid('getRowData',rows[i]);
            uv_ids[i] = rowData.uv_id;
        }
        if(rows.length>0){
            $.messager.confirm("温馨提示", "是否确定删除所选记录？", function() {
                $.ajax({
                    url:"/manager/videomanager/deleteVideo",
                    cache: false,
                    type:"post",
                    data:{"ids": uv_ids.join(",")},
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
    //上传视频，弹出新增窗口
    $("#uploadVideoBtn").click(function () {
        initAddData();
        $("#uploadVideoModal").modal({
            keyboard : false,
            show : true,
            backdrop : "static"
        });
    });
    //保存上传视频
    $('#uplodVideoSaveBtn').click(function(){
        uploadSysVideo();
    });
});
//上传视频对话框取消点击事件
$('#cancelUploadVideoSave').click(function(){
    $("#uploadVideoModal").modal('hide');
});
//初始化上传视频数据
function initAddData(){
    $("#progresswidth").attr("style","width:0%");
    $('#title').val("");
    $('#upload_file').val("");
    $('#upload_file_tmp').val("");
    $("#progress").attr("hidden","hidden");
}
/**
 * 上传视频
 */
function uploadSysVideo(){
    /*for (var i=0;i<100;i++){
        $("#progresswidth").attr("style","width:"+i+"%");
    }*/
    var title=$("#title").val();
    var file=$("#myUploadVideo");
    if (title==undefined||title==null||title==""||title.length==0){
        $.messager.alert('温馨提示',"请输入视频标题！");
        return;
    }
    var fileval=$("#upload_file_tmp").val();
    if(fileval==undefined||fileval==null||fileval==""||fileval.length==0){
        $.messager.alert('温馨提示',"请选择视频！");
        return;
    }
    $("#progress").removeAttr("hidden");
    /!*设置进度条*!/
    var progress=setInterval(function () {
        $.ajax({
            url: '/manager/videomanager/getUploadVideoProgress',
            type: 'POST',
            cache: false,
            processData: false,
            contentType: false
        }).done(function(res) {
            if(res.flag){
                $("#progress").attr("data-percent",res.progress);
                $("#progresswidth").attr("style","width:"+res.progress);
                if (res.progress=="100%"){
                    clearInterval(progress);
                }
            }
        }).fail(function(res) {});
    },300,2000);
    /!*上传视频*!/
    $.ajax({
        url: '/manager/videomanager/uploadSysVideo',
        type: 'POST',
        cache: false,
        data: new FormData($('#uploadVideoForm')[0]),
        processData: false,
        contentType: false
    }).done(function(res) {
        clearInterval(progress);
        if(res.flag){
            $.messager.alert("温馨提示",res.message);
        }else {
            $.messager.alert("温馨提示","服务器繁忙，请稍后...");
        }
        refreshData();
        $("#uploadVideoModal").modal('hide');
    }).fail(function(res) {
        $.messager.alert("温馨提示","服务器繁忙，请稍后...");
    });
}
/**
 * 文件上传隐藏默认
 */
function change(){
    document.getElementById("upload_file_tmp").value=document.getElementById("upload_file").value;
}
function refreshData(){
    $(grid_selector).jqGrid('setGridParam',{
        postData:{title:null,type:null},
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

$('#showVideoModal').on('hide.bs.modal', function () {
    // 执行一些动作...
    $("#video")[0].pause();
});

function showVideo(base) {
    var rowData = $(grid_selector).jqGrid('getRowData',$(base).parent().parent().attr("id"));
    $("#videotitle").text(rowData.uv_title);
    $("#my-video").attr("poster",rowData.uv_thumbnailurl);
    var video=$("<video></video>");
    video.attr("id","video");
    video.attr("width","750");
    video.attr("height","400");
    video.attr("controls","controls");
    video.attr("src",rowData.uv_videourl);
    $("#my-video").text("");
    video.appendTo($("#my-video"));
    /*var x = document.createElement("VIDEO");
    x.setAttribute("width", "320");
    x.setAttribute("height", "240");
    x.setAttribute("controls", "controls");
    x.setAttribute("src", rowData.uv_videourl);
    var video=document.getElementById("my-video");
    video.innerHTML="";
    video.appendChild(x);*/
    $("#showVideoModal").modal({
        keyboard : false,
        show : true,
        backdrop : "static"
    }).css({
        'width': '1400px',
        'height':'800px',
        'margin-top': '30px',
        'padding-right': '17px'
    });
}
//自定义图片的格式，可以根据rowdata自定义
function alarmFormatter(cellvalue, options, rowdata) {
    return ' <img src="'+cellvalue+'" id="img' + rowdata.Id + '"  style="width:80px;height:80px;" />';
}
function removeHorizontalScrollBar() {
    $("div.ui-state-default.ui-jqgrid-hdiv.ui-corner-top").css("width", parseInt($("div.ui-state-default.ui-jqgrid-hdiv.ui-corner-top").css("width")) + 1 + "px");
    $(grid_selector).closest(".ui-jqgrid-bdiv").css("width", parseInt($(grid_selector).closest(".ui-jqgrid-bdiv").css("width")) + 1 + "px");
}