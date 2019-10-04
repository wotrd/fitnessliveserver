package com.example.animalsalesserver.manager.service;


import com.alibaba.fastjson.JSONObject;
import com.example.animalsalesserver.manager.domain.Business;
import com.example.animalsalesserver.manager.mapper.BusinessMapper;
import com.example.animalsalesserver.tools.ServletUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @author wangkaijin
 */
@Service
public class BusinessService {

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
        List<Business> businesses = businessMapper.queryLikeName(name);

        JSONObject jo = new JSONObject();
        jo.put("rows", businesses);
        jo.put("total", page.getPages());
        jo.put("records", page.getTotal());
        ServletUtil.createSuccessResponse(200, jo, response);

    }


}
