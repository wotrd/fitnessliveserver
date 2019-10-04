package com.example.animalsalesserver.manager.intercepter;

import com.example.animalsalesserver.customer.qo.UserLoginQo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wkj_pc
 * @date 2017/5/20
 */
public class LoginIntercepter implements HandlerInterceptor{
    /**
     * 在未登录之前进行拦截，跳转到登录界面
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserLoginQo userLoginQo = (UserLoginQo) request.getSession().getAttribute("loginUser");
        if (null==userLoginQo){
            response.sendRedirect("/manager/login/toLogin");
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

}
