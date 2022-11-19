package com.example.sceneserver.listener;

import com.example.sceneserver.customer.qo.UserLoginQo;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wkj_pc
 * @date 2017/5/20
 */
@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //存放登录用户信息
        Map<UserLoginQo,HttpSession> userLoginQo=new HashMap<>(16);
        //存放直播用户和观众会话
        sce.getServletContext().setAttribute("userLoginQo",userLoginQo);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
