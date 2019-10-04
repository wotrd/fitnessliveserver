package com.example.animalsalesserver.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.ServletException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
