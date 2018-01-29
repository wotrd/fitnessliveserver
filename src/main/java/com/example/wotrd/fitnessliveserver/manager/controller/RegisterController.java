package com.example.wotrd.fitnessliveserver.manager.controller;

import com.example.wotrd.fitnessliveserver.manager.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wkj_pc on 2017/6/5.
 */
@Controller
@RequestMapping("/manager/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    /**
     * 模板跳转到注册页面
     */
    @RequestMapping("/toRegister")
    public String  toRegister(){
        return "register";
    }
    /**
     *  开始进行注册，接收为json数据
     *  注册成功后跳转到登录页面
     */
    @ResponseBody
    @RequestMapping(value = "/checkAccount")
    public Map<String, Object> checkAccount(@RequestParam(name = "account",required =false ) String account){
        return registerService.checkAccount(account);
    }
    @RequestMapping(value = "/destroyCode")
    public String destroyCode(HttpServletRequest request){
        request.getSession().setAttribute("validation",100000);
        return null;
    }
    @ResponseBody
    @RequestMapping("/checkPassword")
    public String checkPassword(){
        return null;
    }
    @ResponseBody
    @RequestMapping("/checkIdcard")
    public Map<String, Object> checkIdcard(@RequestParam(name = "idcard",required = false) String idcard){
        return registerService.checkIdcard(idcard);
    }
    @ResponseBody
    @RequestMapping("/checkValidation")
    public Map<String, Object> checkValidation(HttpServletRequest request,
                @RequestParam(name = "validation",required = false) String validation){
        return registerService.checkValidation(request,validation);
    }
    @ResponseBody
    @RequestMapping("/checkEmail")
    public Map<String, Object> checkEmail(@RequestParam(name = "email",required = false) String email){
        return registerService.checkEmail(email);
    }
    @ResponseBody
    @RequestMapping("/checkPhonenum")
    public Map<String, Object> checkPhonenum(@RequestParam(name = "phonenum",required = false) String phonenum){
        return registerService.checkPhonenum(phonenum);
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