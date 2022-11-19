package com.example.sceneserver.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author wkj_pc
 * @date 2017/5/20
 */
@Controller
@RequestMapping("/manager")
public class IndexController {

    /**
     * 跳转到首页
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    /**
     * 加载main页面
     */
    @RequestMapping("/home")
    public String main(){
        return "home";
    }


}
