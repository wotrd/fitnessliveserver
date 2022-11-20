package com.example.sceneserver.manager.service;


import com.alibaba.fastjson.JSONObject;
import com.example.sceneserver.manager.mapper.InfoMapper;
import com.example.sceneserver.manager.po.AppraisePo;
import com.example.sceneserver.manager.po.InfoDO;
import com.example.sceneserver.manager.po.ProjectDO;
import com.example.sceneserver.tools.ServletUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


/**
 * @author wangkaijin
 */
@Service
public class InfoManagerService {

    @Autowired
    private InfoMapper infoMapper;

    /**
     * 获取资讯列表
     *
     * @param request
     * @param response
     * @return
     */
    public void queryInfoList(HttpServletRequest request, HttpServletResponse response) {
        // 取得当前页数,注意这是jqgrid自身的参数
        int pageNum = Integer.parseInt(request.getParameter("page"));
        // 取得每页显示行数，,注意这是jqgrid自身的参数
        int pageSize = Integer.parseInt(request.getParameter("rows"));

        String name = request.getParameter("name");

        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<InfoDO> infoDOS = infoMapper.queryLikeName(name);

        JSONObject jo = new JSONObject();
        jo.put("rows", infoDOS);
        jo.put("total", page.getPages());
        jo.put("records", page.getTotal());
        ServletUtil.createSuccessResponse(200, jo, response);

    }

    /**
     * 添加
     *
     * @param request
     * @param response
     * @return
     */
    public void add(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String title = request.getParameter("title");
        String summary = request.getParameter("summary");
        String remark = request.getParameter("remark");

        if (StringUtils.isEmpty(title)) {
            result.put("message", "资讯简介不能为空!");
            result.put("flag", false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }

        List<InfoDO> list = infoMapper.queryByName(title);

        if (!CollectionUtils.isEmpty(list)) {
            result.put("message", "该资讯已经存在!");
            result.put("flag", false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        InfoDO infoDO = InfoDO.builder().title(title).summary(summary).content(remark)
                .createTime(new Date()).build();

        int insert = infoMapper.insert(infoDO);

        if (insert > 0) {
            result.put("message", "资讯添加成功!");
            result.put("flag", true);
        } else {
            result.put("message", "资讯添加失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }

    public void update(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String id = request.getParameter("id");
        String content = request.getParameter("upcontent");
        String summary = request.getParameter("upsummary");
        String title = request.getParameter("uptitle");
        InfoDO infoDO = InfoDO.builder().id(Long.parseLong(id))
                .content(content)
                .summary(summary)
                .title(title)
                .build();

        int insert = infoMapper.updateByPrimaryKey(infoDO);

        if (insert > 0) {
            result.put("message", "项目修改成功!");
            result.put("flag", true);
        } else {
            result.put("message", "项目修改失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }

    /**
     * 删除资讯
     *
     * @param request
     * @param response
     * @return
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String ids = request.getParameter("ids");

        int count = infoMapper.deleteByIds(ids.split(","));

        if (count > 0) {
            result.put("message", "资讯删除成功!");
            result.put("flag", true);
        } else {
            result.put("message", "资讯删除失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }



}
