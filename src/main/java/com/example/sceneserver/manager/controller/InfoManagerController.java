package com.example.sceneserver.manager.controller;

import com.example.sceneserver.manager.service.InfoManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RequestMapping("/manager/infomanager")
@Controller
public class InfoManagerController {

    @Autowired
    private InfoManagerService infoManagerService;

    @RequestMapping("info-manager")
    public String infoManager(){
        return "info-manager";
    }

    @RequestMapping("info-add")
    public String infoAdd(){
        return "info-manager";
    }

    @RequestMapping(value = "/info/list",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void queryBusinessList(HttpServletRequest request , HttpServletResponse response){
        infoManagerService.queryInfoList(request, response);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void addOrder(HttpServletRequest request, HttpServletResponse response) {
        infoManagerService.add(request, response);

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        infoManagerService.update(request, response);

    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void delete(HttpServletRequest request , HttpServletResponse response){
        infoManagerService.delete(request, response);

    }
}
