package com.example.animalsalesserver.manager.controller;

import com.example.animalsalesserver.manager.service.ComplaintManagerService;
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
@RequestMapping("/manager/complaintmanager")
@Controller
public class ComplaintManagerController {

    @Autowired
    private ComplaintManagerService complaintManagerService;

    @RequestMapping("complaint-manager")
    public String complaintManager(){
        return "complaint-manager";
    }

    @RequestMapping(value = "/getComplaint",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void queryComplaintList(HttpServletRequest request , HttpServletResponse response){
        complaintManagerService.queryComplaintList(request, response);
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void delete(HttpServletRequest request , HttpServletResponse response){
        complaintManagerService.delete(request, response);

    }
}
