package com.example.animalsalesserver.manager.controller;

import com.example.animalsalesserver.manager.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author tengj
 * @date 2017/3/13
 */
@Slf4j
@Controller
@RequestMapping("/manager/businessmanager")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @RequestMapping("business-manager")
    public String businessManager(){
        return "business-manager";
    }

    @RequestMapping(value = "/getBusinesses",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void queryBusinessList(HttpServletRequest request ,HttpServletResponse response){
        businessService.queryBusinessList(request, response);
    }

    @RequestMapping(value = "/addBusiness",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void addBusiness(HttpServletRequest request , HttpServletResponse response){
        businessService.addBusiness(request, response);

    }

    @RequestMapping(value = "/update",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void update(HttpServletRequest request , HttpServletResponse response){
        businessService.updateBusiness(request, response);

    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void delete(HttpServletRequest request , HttpServletResponse response){
        businessService.delete(request, response);

    }



}