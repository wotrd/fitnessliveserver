package com.example.animalsalesserver.listener;


import com.example.animalsalesserver.customer.po.UserPo;
import com.example.animalsalesserver.customer.qo.UserLoginQo;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;

/**
 *
 * @author wkj_pc
 * @date 2017/8/15
 */
@WebListener
public class SessionDestroyListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session destroyed");
        Map<UserLoginQo,HttpSession> userLoginQo = (Map<UserLoginQo, HttpSession>) se.getSession().getServletContext().
                getAttribute("userLoginQo");
        UserPo loginUser = (UserPo) se.getSession().getAttribute("userLoginQo");
        if (userLoginQo.containsKey(loginUser)){
            userLoginQo.remove(loginUser);
        }
    }
}
