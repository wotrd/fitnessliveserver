package com.example.animalsalesserver.customer.service;

import com.example.animalsalesserver.conf.RespVo;
import com.example.animalsalesserver.manager.mapper.OrderMapper;
import com.example.animalsalesserver.manager.po.OrderPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangkaijin
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public RespVo getOrders(Long buyerId) {
        return RespVo.builder().code("200").msg("success").data(orderMapper.queryAll(buyerId)).build();
    }

    public RespVo deleteOrder(Long id) {
        int deleteCount = orderMapper.deleteById(id);
        if (deleteCount < 1) {
            return RespVo.builder().code("201").msg("系统开小差了，请稍后再试").build();
        }
        return RespVo.builder().code("200").msg("success").build();
    }

    public RespVo addOrder(OrderPo orderPo) {
        int insertCount = orderMapper.insert(orderPo);
        if (insertCount < 1) {
            return RespVo.builder().code("201").msg("系统开小差了，请稍后再试").build();
        }
        return RespVo.builder().code("200").msg("success").build();
    }

}
