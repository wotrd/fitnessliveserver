package com.example.wotrd.fitnessliveserver.manager.service;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.wotrd.fitnessliveserver.manager.dao.SysMsgDao;
import com.example.wotrd.fitnessliveserver.manager.domain.SysMessage;
import com.example.wotrd.fitnessliveserver.manager.domain.User;
import com.example.wotrd.fitnessliveserver.tools.JPushTools;
import com.example.wotrd.fitnessliveserver.tools.Page;
import com.example.wotrd.fitnessliveserver.tools.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysMessageService {

    @Autowired
    SysMsgDao sysMsgDao;
    @Autowired
    JPushTools jPushTools;

    public void add(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("sendtitle");
        String content = request.getParameter("sendcontent");
        String to = request.getParameter("sendto");
        JSONObject result = new JSONObject();
        if (null==title||title.equals("")){
            result.put("message","请输入标题!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if (null==content||content.equals("")){
            result.put("message","请输入内容!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if (null==to||to.equals("")){
            result.put("message","请输入收件人!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if(!getUserByAccount(to)){
            result.put("message","收件人账户不存在!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        User loginUser= (User) request.getSession().getAttribute("loginUser");
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
        SysMessage msg=new SysMessage();
        msg.setTitle(title);
        msg.setContent(content);
        msg.setTo(to);
        msg.setFrom(loginUser.getAccount());
        msg.setTime(format.format(new Date()));
        msg.setUid(loginUser.getUid());
        msg.setResult("success");
        PushResult pushResult = sendMsgResult(msg);
        if(pushResult.statusCode!=0){
            msg.setResult("failed");
            result.put("message","用户消息发送失败!");
            result.put("flag",false);
            msg.setResult("failed");
            sysMsgDao.add(msg);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if(sysMsgDao.add(msg)){
            result.put("message","消息发送成功!");
            result.put("flag",true);
        }else {
            result.put("message","用户消息发送失败!");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }

    private boolean getUserByAccount(String to) {
        if (to.equals("all"))
            return true;
        return sysMsgDao.getUserByAccount(to);
    }
    /**
     * 通过极光接口，发送消息
     *@param msg
     */
    private PushResult sendMsgResult(SysMessage msg)  {
        JPushClient jpushClient = jPushTools.getJpushClient();
        PushPayload pushPayload;
        PushResult pushResult=null;
        if (msg.getTo().equals("all")){
            pushPayload = JPushTools.buildAndroidAllMessage(JSON.toJSONString(msg));
        }else {
            pushPayload = JPushTools.buildAndroidAllMessageWithAlias(JSON.toJSONString(msg), msg.getTo());
        }
        try {
            pushResult = jpushClient.sendPush(pushPayload);
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        return pushResult;
    }

    public void update(HttpServletRequest request, HttpServletResponse response) {
        String uid = request.getParameter("uid");
        String smid = request.getParameter("smid");
        String time = request.getParameter("time");
        String from = request.getParameter("from");
        String status = request.getParameter("result");
        String content = request.getParameter("content");
        String to = request.getParameter("to");
        String title = request.getParameter("title");
        JSONObject result = new JSONObject();
        //修改操作
        SysMessage msg=new SysMessage();
        msg.setUid(Integer.parseInt(uid));
        msg.setSmid(Integer.parseInt(smid));
        msg.setTime(time);
        msg.setFrom(from);
        msg.setResult(status);
        msg.setContent(content);
        msg.setTo(to);
        msg.setTitle(title);
        msg.setOwner(to);
        if(sysMsgDao.updateMsgByUid(msg)){
            result.put("message","消息修改成功!");
            result.put("flag",true);
        }else {
            result.put("message","消息修改失败!");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }
    public void queryUserList(HttpServletRequest request, HttpServletResponse response) {

        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        String title = request.getParameter("title");
        String to = request.getParameter("to");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page", page);
        params.put("rows", rows);
        params.put("title", title);
        params.put("to", to);
        Page pageObj= sysMsgDao.queryMsgList(params);
        List<Map<String, Object>> msgList=pageObj.getResultList();
        JSONObject jo=new JSONObject();
        jo.put("rows", msgList);
        jo.put("total", pageObj.getTotalPages());
        jo.put("records", pageObj.getTotalRows());
        ServletUtil.createSuccessResponse(200, jo, response);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("ids");
        JSONObject result = new JSONObject();
        //删除操作
        int index = sysMsgDao.deleteByIds(ids);
        if(index>0){
            result.put("message","用户信息删除成功!");
            result.put("flag",true);
        }else{
            result.put("message","用户信息删除失败!");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }
}
