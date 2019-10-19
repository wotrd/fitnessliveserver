package com.example.libraryserver.customer.service;

import com.example.libraryserver.conf.RespVo;
import com.example.libraryserver.manager.mapper.BookMapper;
import com.example.libraryserver.manager.mapper.OrderMapper;
import com.example.libraryserver.manager.po.BookPo;
import com.example.libraryserver.manager.po.OrderPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author wangkaijin
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BookMapper bookMapper;

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

        BookPo bookPo = bookMapper.queryById(orderPo.getId());
        orderPo.setBName(bookPo.getName());
        orderPo.setBPrice(bookPo.getPrice());
        orderPo.setSellId(bookPo.getSellerId());
        orderPo.setSellName(bookPo.getSellerName());
        orderPo.setId(null);

        orderPo.setTotalPrice(orderPo.getBPrice().multiply(new BigDecimal(orderPo.getBCount())));

        int insertCount = orderMapper.insert(orderPo);
        if (insertCount < 1) {
            return RespVo.builder().code("201").msg("系统开小差了，请稍后再试").build();
        }
        return RespVo.builder().code("200").msg("success").build();
    }

    public RespVo updateOrder(OrderPo orderPo) {
        int update = orderMapper.update(orderPo);
        if (update>0){
            return RespVo.builder().code("200").msg("success").build();
        }
        return RespVo.builder().code("201").msg("系统开小差了，请稍后再试").build();

    }
}
