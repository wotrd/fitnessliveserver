package com.example.wotrd.fitnessliveserver.manager.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.wotrd.fitnessliveserver.manager.service.LiveService;
import com.example.wotrd.fitnessliveserver.tools.Page;
import com.example.wotrd.fitnessliveserver.tools.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tengj on 2017/3/13.
 */

@Controller
@RequestMapping("/manager/livemanager")
public class LiveUserController {
    @Autowired
    private LiveService liveService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/liveuser")
    public String liveuser(){
        return "live-user";
    }

    @RequestMapping(value = "/getLiveUsers",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void queryLiveUserList(HttpServletRequest request ,HttpServletResponse response){
        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        String account = request.getParameter("account");
        String nickname = request.getParameter("nickname");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page", page);
        params.put("rows", rows);
        params.put("account", account);
        params.put("nickname", nickname);
        Page pageObj =liveService.queryLiveUserList(params);
        List<Map<String, Object>> liveUserList=pageObj.getResultList();
        JSONObject jo=new JSONObject();
        jo.put("rows", liveUserList);
        jo.put("total", pageObj.getTotalPages());
        jo.put("records", pageObj.getTotalRows());
        ServletUtil.createSuccessResponse(200, jo, response);
    }

}