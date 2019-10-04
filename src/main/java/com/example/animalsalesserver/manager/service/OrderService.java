package com.example.animalsalesserver.manager.service;

import com.alibaba.fastjson.JSONObject;
import com.example.animalsalesserver.tools.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
//    @Autowired
//    FansAndAttentionDao fansAndAttentionDao;
    /**
     * 取消关注
     * @param request
     * @param response
     */
//    public void cancelAttention(HttpServletRequest request, HttpServletResponse response) {
//        String useraccount = request.getParameter("useraccount");
//        String attentionsaccount = request.getParameter("attentionsaccount");
//        JSONObject result=new JSONObject();
//        if (fansAndAttentionDao.cancelAttention(useraccount,attentionsaccount)){
//            result.put("message","取消关注成功！");
//            result.put("flag",true);
//            ServletUtil.createSuccessResponse(200,result,response);
//            return;
//        }
//        result.put("message","服务器繁忙，请稍后...");
//        result.put("flag",false);
//        ServletUtil.createSuccessResponse(200,result,response);
//        return;
//    }
    /**
     * 删除粉丝
     */
//    public void delete(HttpServletRequest request, HttpServletResponse response) {
//        String useraccount = request.getParameter("useraccount");
//        String fansaccount = request.getParameter("fansaccount");
//        JSONObject result=new JSONObject();
//        if (fansAndAttentionDao.deleteFans(useraccount,fansaccount)){
//            result.put("message","删除粉丝成功!");
//            result.put("flag",true);
//            ServletUtil.createSuccessResponse(200, result, response);
//            return;
//        }
//        result.put("message","服务器异常，请稍后...");
//        result.put("flag",false);
//        ServletUtil.createSuccessResponse(200,result,response);
//    }
    /**
     * 关注
     * @param response
     * @param request
     */
//    public void attention(HttpServletRequest request, HttpServletResponse response) {
//        String useraccount= request.getParameter("useraccount");
//        String attentionaccount= request.getParameter("attentionaccount");
//        JSONObject result=new JSONObject();
//        if (verifyAttentions(response, useraccount, attentionaccount, result)) return;
//        if(null==fansAndAttentionDao.queryUserByAccount(attentionaccount)){
//            result.put("message","关注账户不存在!");
//            result.put("flag",false);
//            ServletUtil.createSuccessResponse(200, result, response);
//            return;
//        }
//        if (fansAndAttentionDao.getAttentionsByAccount(useraccount,attentionaccount)){
//            result.put("message","已关注该粉丝!");
//            result.put("flag",false);
//            ServletUtil.createSuccessResponse(200, result, response);
//            return;
//        }
//        if (fansAndAttentionDao.attention(useraccount,attentionaccount)){
//            result.put("message","关注用户成功!");
//            result.put("flag",true);
//            ServletUtil.createSuccessResponse(200, result, response);
//            return;
//        }
//        result.put("message","服务器繁忙，请稍后...");
//        result.put("flag",false);
//        ServletUtil.createSuccessResponse(200, result, response);
//    }
    /**
     * 添加粉丝
     */
//    public void add(HttpServletRequest request, HttpServletResponse response) {
//        String useraccount= request.getParameter("useraccount");
//        String fansaccount= request.getParameter("fansaccount");
//        JSONObject result=new JSONObject();
//        if (verifyAttentions(response, useraccount, fansaccount, result)) return;
//        if(null==fansAndAttentionDao.queryUserByAccount(fansaccount)){
//            result.put("message","粉丝账户不存在!");
//            result.put("flag",false);
//            ServletUtil.createSuccessResponse(200, result, response);
//            return;
//        }
//        if (fansAndAttentionDao.getFansByAccount(useraccount,fansaccount)){
//            result.put("message","该粉丝已经存在!");
//            result.put("flag",false);
//            ServletUtil.createSuccessResponse(200, result, response);
//            return;
//        }
//        if (fansAndAttentionDao.addFans(useraccount,fansaccount)){
//            result.put("message","粉丝账户添加成功!");
//            result.put("flag",true);
//            ServletUtil.createSuccessResponse(200, result, response);
//            return;
//        }
//        result.put("message","服务器繁忙，请稍后...");
//        result.put("flag",false);
//        ServletUtil.createSuccessResponse(200, result, response);
//    }
//
//    private boolean verifyAttentions(HttpServletResponse response, String useraccount, String fansaccount, JSONObject result) {
//        if(null==fansAndAttentionDao.queryUserByAccount(useraccount)){
//            result.put("message","用户账户不存在!");
//            result.put("flag",false);
//            ServletUtil.createSuccessResponse(200, result, response);
//            return true;
//        }
//        if(useraccount.equals(fansaccount)){
//            result.put("message","用户不能关注自己!");
//            result.put("flag",false);
//            ServletUtil.createSuccessResponse(200, result, response);
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 获取关注列表
//     */
//    public void queryAttentionsList(HttpServletRequest request, HttpServletResponse response) {
//
//        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
//        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
//        String account = request.getParameter("account");
//        String uid = request.getParameter("uid");
//        Map<String,Object> params = new HashMap<String,Object>();
//        params.put("page", page);
//        params.put("rows", rows);
//        params.put("account", account);
//        params.put("uid", uid);
//        Page pageObj= fansAndAttentionDao.queryAttentionList(params);
//        List<Map<String, Object>> msgList=pageObj.getResultList();
//        JSONObject jo=new JSONObject();
//        jo.put("rows", msgList);
//        jo.put("total", pageObj.getTotalPages());
//        jo.put("records", pageObj.getTotalRows());
//        ServletUtil.createSuccessResponse(200, jo, response);
//    }
//    /**
//     * 获取粉丝列表
//     */
//    public void queryFansList(HttpServletRequest request, HttpServletResponse response) {
//
//        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
//        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
//        String account = request.getParameter("account");
//        String uid = request.getParameter("uid");
//        Map<String,Object> params = new HashMap<String,Object>();
//        params.put("page", page);
//        params.put("rows", rows);
//        params.put("account", account);
//        params.put("uid", uid);
//        Page pageObj= fansAndAttentionDao.queryFansList(params);
//        List<Map<String, Object>> msgList=pageObj.getResultList();
//        JSONObject jo=new JSONObject();
//        jo.put("rows", msgList);
//        jo.put("total", pageObj.getTotalPages());
//        jo.put("records", pageObj.getTotalRows());
//        ServletUtil.createSuccessResponse(200, jo, response);
//    }
}
