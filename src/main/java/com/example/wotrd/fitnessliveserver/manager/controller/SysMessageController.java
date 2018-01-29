package com.example.wotrd.fitnessliveserver.manager.controller;

import com.example.wotrd.fitnessliveserver.manager.service.SysMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/mamager/sysmessage")
public class SysMessageController {
    @Autowired
    SysMessageService sysMessageService;
    /**
     * 获取消息列表
     */
    @RequestMapping("")
    public String showSysMessage(){
        return "sys-message";
    }
    /**
     * @param request
     * @param response
     * 获取消息列表
     */
    @RequestMapping(value = "/queryUserList",method = RequestMethod.POST
            ,produces="application/json;charset=UTF-8")
    public void queryUserList(HttpServletRequest request, HttpServletResponse response){
        sysMessageService.queryUserList(request,response);
    }

    /**
     * @param request
     * @param response
     * 增加消息列表
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response){
        sysMessageService.add(request,response);
    }
    /**
     * @param request
     * @param response
     * 修改消息列表
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(HttpServletRequest request, HttpServletResponse response){
        sysMessageService.update(request,response);
    }
    /**
     * @param request
     * @param response
     * 删除消息列表
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response){
        sysMessageService.delete(request,response);
    }
}
