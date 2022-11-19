package com.example.sceneserver.manager.controller;

import com.example.sceneserver.manager.service.ProjectManagerService;
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
@RequestMapping("/manager/projectmanager")
@Controller
public class ProjectManagerController {

    @Autowired
    private ProjectManagerService projectManagerService;

    @RequestMapping("projectmanager")
    public String projectManager() {
        return "project-manager";
    }

    @RequestMapping(value = "/getProjectes", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void getProjectes(HttpServletRequest request, HttpServletResponse response) {
        projectManagerService.queryProjectList(request, response);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void addOrder(HttpServletRequest request, HttpServletResponse response) {
        projectManagerService.add(request, response);

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
//        projectManagerService.updateOrder(request, response);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        projectManagerService.delete(request, response);

    }

}
