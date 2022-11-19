package com.example.sceneserver.customer.controller;

import com.example.sceneserver.conf.RespVo;
import com.example.sceneserver.customer.service.OrderService;
import com.example.sceneserver.manager.po.OrderPo;
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

    @RequestMapping("updateOrder")
    public RespVo updateOrder(@RequestBody OrderPo orderPo) {
        return orderService.updateOrder(orderPo);

    }

}
