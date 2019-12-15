package com.example.libraryserver.customer.controller;

import com.example.libraryserver.conf.RespVo;
import com.example.libraryserver.customer.service.OrderService;
import com.example.libraryserver.manager.po.OrderPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("getOrdersByStatus")
    public RespVo getOrders(@RequestParam("userId") Long userId, @RequestParam("status") Integer status) {
        return orderService.getOrdersByType(userId, status);
    }

    @GetMapping("getCart")
    public RespVo getCart(@RequestParam Long id){
        return orderService.getCart(id);
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
