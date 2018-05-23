package com.example.wotrd.fitnessliveserver.manager.controller;

import com.example.wotrd.fitnessliveserver.manager.domain.User;
import com.example.wotrd.fitnessliveserver.manager.service.LoginService;
import com.example.wotrd.fitnessliveserver.tools.KaptchaUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by wkj_pc on 2017/5/20.
 */
@Controller
@RequestMapping("/manager/login")
public class LoginController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private LoginService loginService;

    private Map<String, Object> verifyMap=null;
    /*
       * 链接到登录界面
       * */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    /**
     *验证用户是否还存在
     */
    @RequestMapping("/checkLogin")
    @ResponseBody
    public Map<String,Object> checkLogin(HttpServletRequest request,HttpServletResponse response){
        return loginService.checkLogin(request);
    }

   /**
   * 接收到前台页面传过来的值进行验证登录
   */
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>  doLogin(HttpServletRequest request,HttpServletResponse response){
        String userName = request.getParameter("userName");
        String password=request.getParameter("password");
        User loginUser=new User();
        loginUser.setAccount(userName);
        loginUser.setPassword(password);
        return loginService.doLogin(request, loginUser);

    }
    /**
     *  验证输入的验证码是否正确
     */
   @ResponseBody
   @RequestMapping("/toVerifyCode")
   public Map<String, Object> toVerifyCode(HttpServletRequest request,HttpServletResponse response){
       return loginService.toVerifyCode(request);
   }
    /*
    * 获取登录验证码
    * */
    @RequestMapping("/getVerifyCode")
    public void getVerifyCodeByKaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        KaptchaUtils.createKaptcha(request,response,defaultKaptcha);
    }
    /**
     * 退出登录
     * @param request
     */
    @RequestMapping(value = "/quitLogin")
    @ResponseBody
    public String quitLogin(HttpServletRequest request){
        System.out.println("你开始退出了！");
        loginService.quitLogin(request);
        return "";
    }
}
