package com.example.libraryserver.manager.controller;

import com.example.libraryserver.manager.service.ManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 * @author wkj_pc
 * @date 2017/6/5
 */
@Controller
@RequestMapping("/manager/register")
public class RegisterController {

    @Autowired
    private ManagerUserService registerService;

    /**
     * 模板跳转到注册页面
     */
    @RequestMapping("/toRegister")
    public String  toRegister(){
        return "register";
    }

    @RequestMapping(value = "/destroyCode")
    public String destroyCode(HttpServletRequest request){
        request.getSession().setAttribute("validation",100000);
        return null;
    }

    @ResponseBody
    @RequestMapping("/checkValidation")
    public Map<String, Object> checkValidation(HttpServletRequest request,
                @RequestParam(name = "validation",required = false) String validation){
        return registerService.checkValidation(request,validation);
    }

    @ResponseBody
    @RequestMapping("/sendMessage")
    public Map<String, Object> sendMessage(HttpServletRequest request,
                                            @RequestParam(name = "phonenum",required = false) String phonenum){
        return registerService.sendMessage(request,phonenum);
    }
    @ResponseBody
    @RequestMapping("/doRegister")
    public Map<String, Object> doRegister(HttpServletRequest request){
        return registerService.doRegister(request);
    }


}