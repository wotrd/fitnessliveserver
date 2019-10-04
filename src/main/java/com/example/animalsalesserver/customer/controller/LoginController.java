package com.example.animalsalesserver.customer.controller;

import com.example.animalsalesserver.customer.qo.UserLoginQo;
import com.example.animalsalesserver.customer.qo.UserRegisterQo;
import com.example.animalsalesserver.customer.service.LoginService;
import com.example.animalsalesserver.conf.RespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author wkj_pc
 * @date 2017/6/10
 */
@Slf4j
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * @param request
     * @param userLoginQo
     * @return 执行登录操作
     */
    @ResponseBody
    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    public RespVo login(@RequestBody UserLoginQo userLoginQo, HttpServletRequest request) {

        return loginService.doLogin(userLoginQo, request);

    }

    /**
     * 退出登录
     *
     * @param userLoginQo
     * @param request
     */
    @RequestMapping(value = "quitLogin")
    @ResponseBody
    public RespVo quitLogin(@RequestBody UserLoginQo userLoginQo, HttpServletRequest request) {
        log.info("你开始退出了！");
        return loginService.quitLogin(userLoginQo, request);

    }

    /**
     * 返回客户端请求的验证码
     */
    @RequestMapping(value = "getVerifycode")
    @ResponseBody
    public RespVo getVerifycode() {
        return RespVo.builder().code("200").msg("success").data("1234").build();
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "registerUser")
    @ResponseBody
    public RespVo registerUser(@RequestBody UserRegisterQo userRegisterQo) {
        return loginService.registerUser(userRegisterQo);
    }

}
