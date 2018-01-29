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
public class UserAttentionController {

    @Autowired
    FansAndAttentionService fansAndAttentionService;
    /**
     * @param request
     * @param response
     * 取消关注列表
     */
    @RequestMapping("/cancelAttention")
    public void delete(HttpServletRequest request, HttpServletResponse response){
        fansAndAttentionService.cancelAttention(request,response);
    }
    @RequestMapping(value = "/attentionmanager")
    public String attentionManager(){
        return "user-attention";
    }
    /**
     * @param request
     * @param response
     * 增加关注列表
     */
    @RequestMapping("/attention")
    public void add(HttpServletRequest request, HttpServletResponse response)
    {
        fansAndAttentionService.attention(request,response);
    }
    /**
     * @param request
     * @param response
     * 获取关注列表
     */
    @RequestMapping(value = "/queryAttentionList",method = RequestMethod.POST
            ,produces="application/json;charset=UTF-8")
    public void queryAttentionList(HttpServletRequest request, HttpServletResponse response){
        fansAndAttentionService.queryAttentionsList(request,response);
    }
    /*
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(HttpServletRequest request, HttpServletResponse response){
        sysMessageService.update(request,response);
    }*/
}
