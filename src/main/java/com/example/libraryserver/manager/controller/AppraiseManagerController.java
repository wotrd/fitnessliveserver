package com.example.libraryserver.manager.controller;

import com.example.libraryserver.manager.service.AppraiseManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangkaijin
 */
@RequestMapping("/manager/appraisemanager")
@Controller
public class AppraiseManagerController {

    @Autowired
    private AppraiseManagerService appraiseManagerService;

    @RequestMapping("appraise-manager")
    public String businessManager(){
        return "appraise-manager";
    }

    @RequestMapping(value = "/getAppraises",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void queryBusinessList(HttpServletRequest request , HttpServletResponse response){
        appraiseManagerService.queryAppraiseList(request, response);
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void delete(HttpServletRequest request , HttpServletResponse response){
        appraiseManagerService.delete(request, response);

    }
}
