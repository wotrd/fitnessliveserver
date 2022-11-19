package com.example.sceneserver.manager.controller;

import com.example.sceneserver.manager.service.OrderManagerService;
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
@RequestMapping("/manager/ordermanager")
@Controller
public class OrderManagerController {

    @Autowired
    private OrderManagerService orderService;

    @RequestMapping("ordermanager")
    public String businessManager() {
        return "ordermanager";
    }

    @RequestMapping(value = "/getOrders", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void getOrders(HttpServletRequest request, HttpServletResponse response) {
        orderService.queryOrderList(request, response);
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void addOrder(HttpServletRequest request, HttpServletResponse response) {
        orderService.addOrder(request, response);

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        orderService.updateOrder(request, response);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        orderService.delete(request, response);

    }

}
