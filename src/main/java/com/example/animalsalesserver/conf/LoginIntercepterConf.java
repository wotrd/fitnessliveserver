package com.example.animalsalesserver.conf;

import com.example.animalsalesserver.manager.intercepter.LoginIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by wkj_pc on 2017/5/20.
 */
@Configuration
public class LoginIntercepterConf extends WebMvcConfigurerAdapter {

    /**
     * 添加拦截器，跳转到登录界面
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginIntercepter()).addPathPatterns("/**").
                excludePathPatterns("/customer/login/*")
                .excludePathPatterns("/customer/live/*")
                .excludePathPatterns("/manager/login/*")
                .excludePathPatterns("/manager/register/*");
        super.addInterceptors(registry);
    }

}
