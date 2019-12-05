package com.example.animalsalesserver.manager.service;


import com.alibaba.fastjson.JSONObject;
import com.example.animalsalesserver.manager.mapper.ComplaintMapper;
import com.example.animalsalesserver.manager.po.ComplaintPo;
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
public class ComplaintManagerService {

    @Autowired
    private ComplaintMapper complaintMapper;

    /**
     * 获取评价列表
     *
     * @param request
     * @param response
     * @return
     */
    public void queryComplaintList(HttpServletRequest request, HttpServletResponse response) {
        // 取得当前页数,注意这是jqgrid自身的参数
        int pageNum = Integer.parseInt(request.getParameter("page"));
        // 取得每页显示行数，,注意这是jqgrid自身的参数
        int pageSize = Integer.parseInt(request.getParameter("rows"));

        String name = request.getParameter("name");

        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<ComplaintPo> appraises = complaintMapper.queryLikeName(name);

        JSONObject jo = new JSONObject();
        jo.put("rows", appraises);
        jo.put("total", page.getPages());
        jo.put("records", page.getTotal());
        ServletUtil.createSuccessResponse(200, jo, response);

    }


    /**
     * 删除举报
     *
     * @param request
     * @param response
     * @return
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String ids = request.getParameter("ids");

        int count = complaintMapper.deleteByIds(ids.split(","));

        if (count > 0) {
            result.put("message", "举报删除成功!");
            result.put("flag", true);
        } else {
            result.put("message", "举报删除失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }



}
