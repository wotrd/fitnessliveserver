package com.example.animalsalesserver.manager.service;


import com.alibaba.fastjson.JSONObject;
import com.example.animalsalesserver.manager.mapper.OrderMapper;
import com.example.animalsalesserver.manager.po.OrderPo;
import com.example.animalsalesserver.tools.ServletUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;


/**
 * @author wangkaijin
 */
@Service
public class OrderManagerService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 获取订单列表
     *
     * @param request
     * @param response
     * @return
     */
    public void queryOrderList(HttpServletRequest request, HttpServletResponse response) {
        // 取得当前页数,注意这是jqgrid自身的参数
        int pageNum = Integer.parseInt(request.getParameter("page"));
        // 取得每页显示行数，,注意这是jqgrid自身的参数
        int pageSize = Integer.parseInt(request.getParameter("rows"));

        String bName = request.getParameter("bName");
        String buyerName = request.getParameter("buyerName");
        String sellName = request.getParameter("sellName");

        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<OrderPo> orderPos = orderMapper.queryLikeNameAndBuyerAndSell(bName, buyerName, sellName);

        JSONObject jo = new JSONObject();
        jo.put("rows", orderPos);
        jo.put("total", page.getPages());
        jo.put("records", page.getTotal());
        ServletUtil.createSuccessResponse(200, jo, response);

    }

    /**
     * 添加订单
     *
     * @param request
     * @param response
     * @return
     */
    public void addOrder(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String bName = request.getParameter("bName");
        String bCount = request.getParameter("bCount");
        String bPrice = request.getParameter("bPrice");
        String buyerName = request.getParameter("buyerName");
        String sellName = request.getParameter("sellName");
        int count = Integer.parseInt(bCount);
        BigDecimal price = new BigDecimal(bPrice);
        BigDecimal totalPrice = price.multiply(new BigDecimal(count));
        OrderPo orderPo = OrderPo.builder().bName(bName).bCount(count)
                .bPrice(price)
                .buyerName(buyerName).sellName(sellName)
                .totalPrice(totalPrice)
                .build();

        int insert = orderMapper.insert(orderPo);

        if (insert > 0) {
            result.put("message", "订单添加成功!");
            result.put("flag", true);
        } else {
            result.put("message", "订单添加失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }

    /**
     * 修改订单
     *
     * @param request
     * @param response
     * @return
     */
    public void updateOrder(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String id = request.getParameter("id");
        String bName = request.getParameter("upBname");
        String bCount = request.getParameter("upBcount");
        String upBprice = request.getParameter("upBprice");
        String buyerName = request.getParameter("upBuyerName");
        String sellName = request.getParameter("upSellName");

        int count = Integer.parseInt(bCount);
        BigDecimal price = new BigDecimal(upBprice);
        BigDecimal totalPrice = price.multiply(new BigDecimal(count));

        OrderPo orderPo = OrderPo.builder().id(Long.parseLong(id)).bName(bName).bCount(count)
                .bPrice(price)
                .buyerName(buyerName).sellName(sellName)
                .totalPrice(totalPrice)
                .build();

        int upCount = orderMapper.update(orderPo);

        if (upCount > 0) {
            result.put("message", "订单修改成功!");
            result.put("flag", true);
        } else {
            result.put("message", "订单修改失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }

    /**
     * 删除订单
     *
     * @param request
     * @param response
     * @return
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String ids = request.getParameter("ids");

        int count = orderMapper.deleteByIds(ids.split(","));

        if (count > 0) {
            result.put("message", "订单删除成功!");
            result.put("flag", true);
        } else {
            result.put("message", "订单删除失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }

}
