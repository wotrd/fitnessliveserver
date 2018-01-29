package com.example.wotrd.fitnessliveserver.manager.controller;

import com.example.wotrd.fitnessliveserver.manager.service.FansAndAttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/manager/livemanager")
@Controller
public class UserFansController {

    @Autowired
    FansAndAttentionService fansAndAttentionService;
    /**
     * @param request
     * @param response
     * 删除粉丝列表
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response){
        fansAndAttentionService.delete(request,response);
    }
    @RequestMapping(value = "/fansmanager")
    public String fansManger(){
        return "user-fans";
    }
    /**
     * @param request
     * @param response
     * 获取粉丝列表
     */
    @RequestMapping(value = "/queryFansList",method = RequestMethod.POST
            ,produces="application/json;charset=UTF-8")
    public void queryFansList(HttpServletRequest request, HttpServletResponse response){
        fansAndAttentionService.queryFansList(request,response);
    }
    /**
     * @param request
     * @param response
     * 添加粉丝列表
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response){
        fansAndAttentionService.add(request,response);
    }
     /*
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(HttpServletRequest request, HttpServletResponse response){
        fansAndAttentionService.update(request,response);
    }*/
}
