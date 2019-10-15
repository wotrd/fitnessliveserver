package com.example.animalsalesserver.manager.service;


import com.alibaba.fastjson.JSONObject;
import com.example.animalsalesserver.customer.qo.UserLoginQo;
import com.example.animalsalesserver.manager.mapper.AppraiseMapper;
import com.example.animalsalesserver.manager.mapper.BusinessMapper;
import com.example.animalsalesserver.manager.po.Appraise;
import com.example.animalsalesserver.manager.po.BusinessPo;
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
public class AppraiseManagerService {

    @Autowired
    private AppraiseMapper appraiseMapper;

    /**
     * 获取评价列表
     *
     * @param request
     * @param response
     * @return
     */
    public void queryAppraiseList(HttpServletRequest request, HttpServletResponse response) {
        // 取得当前页数,注意这是jqgrid自身的参数
        int pageNum = Integer.parseInt(request.getParameter("page"));
        // 取得每页显示行数，,注意这是jqgrid自身的参数
        int pageSize = Integer.parseInt(request.getParameter("rows"));

        String name = request.getParameter("name");

        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Appraise> appraises = appraiseMapper.queryLikeName(name);

        JSONObject jo = new JSONObject();
        jo.put("rows", appraises);
        jo.put("total", page.getPages());
        jo.put("records", page.getTotal());
        ServletUtil.createSuccessResponse(200, jo, response);

    }


    /**
     * 删除评价
     *
     * @param request
     * @param response
     * @return
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String ids = request.getParameter("ids");

        int count = appraiseMapper.deleteByIds(ids.split(","));

        if (count > 0) {
            result.put("message", "评价删除成功!");
            result.put("flag", true);
        } else {
            result.put("message", "评价删除失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }



}
