package com.example.wotrd.fitnessliveserver.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wkj_pc on 2017/5/20.
 */
@Controller
@RequestMapping("/manager")
public class IndexController {


    /** 跳转到首页*/
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    /** 加载main页面*/
    @RequestMapping("/home")
    public String main(){
        return "home";
    }

    /** 首页表单测试*/
    @RequestMapping(value = "/test")
    @ResponseBody
    public void test(@RequestParam(value = "test")MultipartFile file) throws IOException, ServletException {
        File file1=new File("F://a.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        byte[] bytes = file.getBytes();
            fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

}
