package com.example.wotrd.fitnessliveserver.listener;


import com.example.wotrd.fitnessliveserver.manager.domain.User;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;

/**
 * Created by wkj_pc on 2017/8/15.
 */
@WebListener
public class SessionDestroyListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session destroyed");
        Map<User,HttpSession> userMap = (Map<User, HttpSession>) se.getSession().getServletContext().
                getAttribute("userMap");
        User loginUser = (User) se.getSession().getAttribute("loginUser");
        if (userMap.containsKey(loginUser)){
            userMap.remove(loginUser);
        }
    }
}
