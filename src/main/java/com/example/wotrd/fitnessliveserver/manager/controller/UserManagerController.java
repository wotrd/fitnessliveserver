package com.example.wotrd.fitnessliveserver.manager.controller;

import com.example.wotrd.fitnessliveserver.manager.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *@author wotrd
 * 用户管理
 */
@Controller
@RequestMapping("/manager/usermanager")
public class UserManagerController {
    @Autowired
    RegisterService registerService;
    /**
     *用户管理主页显示
     */
    @RequestMapping("")
    public String userManagerIndex(){
        return "user-manager";
    }
    /**
     * 用户信息修改
     *
     */
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        registerService.updateUser(request,response);
    }
    /**
     * @param request
     * @param response
     * 获取用户列表
     */
    @RequestMapping(value = "/queryUserList",method = RequestMethod.POST
            ,produces="application/json;charset=UTF-8")
    public void queryUserList(HttpServletRequest request, HttpServletResponse response){
        registerService.queryUserList(request,response);
    }
    /**
     * 新添用户
     * @param request
     * @param response
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void addUser(HttpServletRequest request , HttpServletResponse response){
        registerService.addUser(request,response);
    }
    /**
     * 删除用户
     * @param request
     * @param response
     */
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    @ResponseBody
    public void deleteUser(HttpServletRequest request ,HttpServletResponse response){
        registerService.deleteUser(request,response);
    }

}
