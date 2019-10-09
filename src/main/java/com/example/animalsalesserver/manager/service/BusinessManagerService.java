package com.example.animalsalesserver.manager.service;


import com.alibaba.fastjson.JSONObject;
import com.example.animalsalesserver.manager.po.BusinessPo;
import com.example.animalsalesserver.manager.mapper.BusinessMapper;
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
public class BusinessManagerService {

    @Autowired
    private BusinessMapper businessMapper;

    /**
     * 获取商品列表
     *
     * @param request
     * @param response
     * @return
     */
    public void queryBusinessList(HttpServletRequest request, HttpServletResponse response) {
        // 取得当前页数,注意这是jqgrid自身的参数
        int pageNum = Integer.parseInt(request.getParameter("page"));
        // 取得每页显示行数，,注意这是jqgrid自身的参数
        int pageSize = Integer.parseInt(request.getParameter("rows"));

        String name = request.getParameter("name");

        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<BusinessPo> businesses = businessMapper.queryLikeName(name);

        JSONObject jo = new JSONObject();
        jo.put("rows", businesses);
        jo.put("total", page.getPages());
        jo.put("records", page.getTotal());
        ServletUtil.createSuccessResponse(200, jo, response);

    }

    /**
     * 添加商品
     *
     * @param request
     * @param response
     * @return
     */
    public void addBusiness(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String price = request.getParameter("price");
        BusinessPo businessPo = businessMapper.queryByName(name);

        if (null != businessPo) {
            result.put("message", "该商品已经存在!");
            result.put("flag", false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        businessPo = BusinessPo.builder().bName(name).type(type).price(new BigDecimal(price)).build();

        int insert = businessMapper.insert(businessPo);

        if (insert > 0) {
            result.put("message", "商品添加成功!");
            result.put("flag", true);
        } else {
            result.put("message", "商品添加失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }

    /**
     * 修改商品
     *
     * @param request
     * @param response
     * @return
     */
    public void updateBusiness(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String id = request.getParameter("id");
        String name = request.getParameter("upname");
        String type = request.getParameter("uptype");
        String price = request.getParameter("upprice");

        BusinessPo businessPo = BusinessPo.builder().id(Long.parseLong(id))
                .bName(name).type(type)
                .price(new BigDecimal(price))
                .build();

        int insert = businessMapper.update(businessPo);

        if (insert > 0) {
            result.put("message", "商品修改成功!");
            result.put("flag", true);
        } else {
            result.put("message", "商品修改失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }

    /**
     * 删除商品
     *
     * @param request
     * @param response
     * @return
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String ids = request.getParameter("ids");

        int count = businessMapper.deleteByIds(ids.split(","));

        if (count > 0) {
            result.put("message", "商品删除成功!");
            result.put("flag", true);
        } else {
            result.put("message", "商品删除失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }



}
