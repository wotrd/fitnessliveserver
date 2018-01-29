/**
 * Created by tengj on 2017/4/10.
 */
var grid_selector = "#jqGrid";
var pager_selector = "#jqGridPager";
var rowNum = 10; 			//每页显示记录数
var task = null;			//任务（新增或编辑）
var loading;
//展示用户，弹出展示窗口
function showUserProfile(base) {
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
}
$(function(){
    $(window).resize(function(){
        $(grid_selector).setGridWidth($(window).width()*0.95);
    });

    $(grid_selector).jqGrid({
        url:"/manager/usermanager/queryUserList",
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
                    return '<a class="btn btn-purple btn-sm " target="_blank" onclick="showUserProfile(this);"><i class="fa fa-cog  fa-spin" aria-hidden="true"></i>点我</a>';
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
    $("#queryUserBtn").click(function(){
        var qryAccount=$("#useraccount").val();
        var qryNickname=$("#usernickname").val();
        $(grid_selector).jqGrid('setGridParam',{
            postData:{useraccount:qryAccount,usernickname:qryNickname},
            //search: true,
            page:1
        }).trigger("reloadGrid");
    });

    //新增教程，弹出新增窗口
    $("#addUserBtn").click(function () {
        // alert($(this).prop("tagName"));
       task = "add";
        initAddData();
        $("#addUserModal").modal({
            keyboard : false,
            show : true,
            backdrop : "static"
        });

    });
    //保存修改用户
    $('#updateUserSaveBtn').click(function(){
        saveUpdateUser();
    });
    //保存添加用户
    $('#saveAddUserBtn').click(function(){
        saveAddUser();
    });

    //修改用户，弹出修改窗
    $("#modifyUserBtn").click(function () {
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
            $("#updateUserModal").modal({
                keyboard : false,
                show : true,
                backdrop : "static"
            });
        }
    });

    //删除用户方法 选择多个的话，行id用逗号隔开比如 3,4
    $("#deleteUserBtn").click(function () {
        var rows = $(grid_selector).jqGrid("getGridParam", "selarrrow");
        var uids=new Array(rows.length);
        for(var i=0;i<rows.length;i++){
            //行数据
            var rowData = $(grid_selector).jqGrid('getRowData',rows[i]);
            //colModel为uid
            uids[i] = rowData.uid;
            // alert(uids[i]);
        }
        if(rows.length>0){
            $.messager.confirm("温馨提示", "是否确定删除所选记录？", function() {
                $.ajax({
                    url:"/manager/usermanager/delete",
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
    $('#amatar').ace_file_input({
        style:'well',
        btn_choose:'Drop files here or click to choose',
        btn_change:null,
        no_icon:'icon-cloud-upload',
        droppable:true,
        thumbnail:'small',
        preview_error : function(filename, error_code) {
        }
    }).on('change', function(){
    });
});
function removeHorizontalScrollBar() {
    $("div.ui-state-default.ui-jqgrid-hdiv.ui-corner-top").css("width", parseInt($("div.ui-state-default.ui-jqgrid-hdiv.ui-corner-top").css("width")) + 1 + "px");
    $(grid_selector).closest(".ui-jqgrid-bdiv").css("width", parseInt($(grid_selector).closest(".ui-jqgrid-bdiv").css("width")) + 1 + "px");
}
//添加用户对话框取消点击事件
$('#cancelAddSaveUser').click(function(){
    $("#addUserModal").modal('hide');
});
//编辑对话框取消点击事件
$('#updateUserResetBtn').click(function(){
    initUpdateData();

});

//初始化修改用户数据
function initUpdateData(){
    var rows=$(grid_selector).getGridParam('selarrrow');
    var data = $(grid_selector).jqGrid('getRowData', rows[0]);
    $("#uid").val(data.uid);
    $('#updateaccount').val(data.account);
    $('#updatename').val(data.name);
    $('#updatenickname').val(data.nickname);
    $('#borndate').val(data.borndata);
    $("#updategrade").val(data.grade);
    $("#updatepersonalsign").val(data.personalsign);
    $("#updateidcard").val(data.idcard);
    $('#updateemail').val(data.email);
    $('#updatemobilenum').val(data.phonenum);
    $('#password').val(data.password);
    $('#verifypassword').val(data.password);
    if(data.gender=="男"){
        $("input[name='updatesex']").get(0).checked=true;
    }else {
        $("input[name='updatesex']").get(1).checked=true;
    }
    if (data.role==1){
        $("#updaterole option:first").attr("selected",true);
    }else {
        $("#updaterole option:last").attr("selected",true);
    }
    //$('#amatar').val(data.amatar);
}

//初始化增加用户数据
function initAddData(){
    $('#userid').val("");
    $('#account').val("");
    $('#mobilenum').val("");
    $('#name').val("");
    $("input[name='sex']").get(0).checked=true;
    $("#sex").attr("checked","checked");
    $('#nickname').val("");
    $('#email').val("");
    $("#role option:first").attr("selected",true);
    $('#idcard').val("");
}
/**
 * 修改用户
 */
function saveUpdateUser(){
    var submit_option= {
        url: "/manager/usermanager/update",//默认是form action
        method:"POST",
        success: function (result) {
            if(result.flag == true) {
                $.messager.alert('温馨提示', result.message);
                $("#updateUserModal").modal('hide');
                refreshData();
            }else {
                $.messager.alert('温馨提示',result.message);
                $("#updateUserModal").modal('hide');
            }

        },error:function(){
            $.messager.alert('服务器繁忙，请稍后再试...');
            $("#updateUserModal").modal('hide');
        }
    }
    $("#updateUserProfileForm").ajaxSubmit(submit_option);
}
/**
 * 保存用户
 */
function saveAddUser(){
    var id = $('#userid').val();
    var account = $('#account').val();
    var mobilenum = $('#mobilenum').val();
    var name = $('#name').val();
    var sex = $('#sex').is(":checked");
    var nickname = $('#nickname').val();
    var email = $('#email').val();
    var role = $('#role').val();
    var idcard = $('#idcard').val();
    $.ajax({
        url: "/manager/usermanager/"+task,
        cache: false,
        data : {
            "id":id,
            "account":account,
            "mobilenum":mobilenum,
            "name": name,
            "nickname":nickname,
            "sex":(sex)?"男":"女",
            "email":email,
            "role":role,
            "idcard":idcard
        },
        type : 'post',
        beforeSend: function () {
            //alert("beforeSend");
            // 禁用按钮防止重复提交
            $('#updateUserSaveBtn').attr({ disabled: "disabled"});
        },
        success: function(result){
            if(result.flag == true){
                $.messager.alert('温馨提示',result.message);
                refreshData();
                $('#updateUserSaveBtn').removeAttr("disabled");
                $("#updateUserModal").modal('hide');
            }else{
                $.messager.alert('温馨提示',result.message);
            }
        },
        complete: function () {
             $('#updateUserSaveBtn').removeAttr("disabled");
        },
        error: function (data) {
            console.info("error: " + data.responseText);
        }
    });
}

function refreshData(){
    $(grid_selector).jqGrid('setGridParam',{
        postData:{useraccount:null,usernickname:null},
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
