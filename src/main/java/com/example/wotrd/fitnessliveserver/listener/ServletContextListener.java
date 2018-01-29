package com.example.wotrd.fitnessliveserver.listener;

import com.example.wotrd.fitnessliveserver.manager.domain.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wkj_pc on 2017/5/20.
 */
@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //存放登录用户信息
        Map<User,HttpSession> userMap=new HashMap<>();
        //存放直播用户和观众会话
        sce.getServletContext().setAttribute("userMap",userMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
