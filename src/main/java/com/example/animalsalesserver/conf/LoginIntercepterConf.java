package com.example.animalsalesserver.conf;

import com.example.animalsalesserver.manager.intercepter.LoginIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author wkj_pc
 * @date 2017/5/20
 */
@Configuration
public class LoginIntercepterConf extends WebMvcConfigurerAdapter {

    /**
     * 添加拦截器，跳转到登录界面
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginIntercepter()).addPathPatterns("/**").
                excludePathPatterns("/login/*")
                .excludePathPatterns("/user/*")
                .excludePathPatterns("/manager/login/*")
                .excludePathPatterns("/manager/register/*");
        super.addInterceptors(registry);
    }

}
