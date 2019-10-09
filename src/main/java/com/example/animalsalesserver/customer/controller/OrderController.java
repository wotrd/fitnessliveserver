package com.example.animalsalesserver.customer.controller;

import com.example.animalsalesserver.conf.RespVo;
import com.example.animalsalesserver.customer.service.OrderService;
import com.example.animalsalesserver.manager.po.OrderPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangkaijin
 */
@RequestMapping("order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("getOrders")
    public RespVo getOrders(@RequestParam("userId") Long userId) {
        return orderService.getOrders(userId);
    }

    @RequestMapping("deleteOrder")
    public RespVo deleteOrder(@RequestBody Long id) {
        return orderService.deleteOrder(id);

    }

    @RequestMapping("addOrder")
    public RespVo addOrder(@RequestBody OrderPo orderPo) {
        return orderService.addOrder(orderPo);

    }

}
