package com.example.libraryserver.manager.controller;

import com.example.libraryserver.manager.service.CollectManagerService;
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
@RequestMapping("/manager/collectmanager")
public class CollectManagerController {

    @Autowired
    private CollectManagerService collectManagerService;

    @RequestMapping("")
    public String bookManager(){
        return "collect-manager";
    }

    @RequestMapping(value = "/getCollects",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void queryCollectList(HttpServletRequest request ,HttpServletResponse response){
        collectManagerService.queryCollectList(request, response);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void delete(HttpServletRequest request , HttpServletResponse response){
        collectManagerService.delete(request, response);

    }



}