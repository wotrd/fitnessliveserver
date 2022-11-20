package com.example.sceneserver.manager.service;


import com.alibaba.fastjson.JSONObject;
import com.example.sceneserver.manager.mapper.ProjectMapper;
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
public class ProjectManagerService {

    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 获取项目列表
     *
     * @param request
     * @param response
     * @return
     */
    public void queryProjectList(HttpServletRequest request, HttpServletResponse response) {
        // 取得当前页数,注意这是jqgrid自身的参数
        int pageNum = Integer.parseInt(request.getParameter("page"));
        // 取得每页显示行数，,注意这是jqgrid自身的参数
        int pageSize = Integer.parseInt(request.getParameter("rows"));

        String name = request.getParameter("name");

        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<ProjectDO> projectDOS = projectMapper.queryLikeName(name);

        JSONObject jo = new JSONObject();
        jo.put("rows", projectDOS);
        jo.put("total", page.getPages());
        jo.put("records", page.getTotal());
        ServletUtil.createSuccessResponse(200, jo, response);

    }

    /**
     * 添加项目
     *
     * @param request
     * @param response
     * @return
     */
    public void add(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String name = request.getParameter("projectName");
        String type = request.getParameter("type");
        String applyUnit = request.getParameter("applyUnit");
        String category = request.getParameter("category");
        String protectUnit = request.getParameter("protectUnit");
        String remark = request.getParameter("remark");
        String areaName = request.getParameter("areaName");

        if (StringUtils.isEmpty(name)) {
            result.put("message", "项目名字不能为空!");
            result.put("flag", false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }

        List<ProjectDO> list = projectMapper.queryByName(name);

        if (!CollectionUtils.isEmpty(list)) {
            result.put("message", "该项目已经存在!");
            result.put("flag", false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        ProjectDO projectDO = ProjectDO.builder().projectName(name).type(type).remark(remark)
                .category(category)
                .createTime(new Date())
                .protectUnit(protectUnit)
                .areaName(areaName)
                .applyUnit(applyUnit).build();

        int insert = projectMapper.insert(projectDO);

        if (insert > 0) {
            result.put("message", "项目添加成功!");
            result.put("flag", true);
        } else {
            result.put("message", "项目添加失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }

//    /**
//     * 修改商品
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    public void updateBusiness(HttpServletRequest request, HttpServletResponse response) {
//        JSONObject result = new JSONObject();
//        String id = request.getParameter("id");
//        String name = request.getParameter("upname");
//        String type = request.getParameter("uptype");
//        String price = request.getParameter("upprice");
//        String avatar = request.getParameter("upavatar");
//        BusinessPo businessPo = BusinessPo.builder().id(Long.parseLong(id))
//                .bName(name).type(type)
//                .price(new BigDecimal(price))
//                .avatar(avatar)
//                .build();
//
//        int insert = businessMapper.update(businessPo);
//
//        if (insert > 0) {
//            result.put("message", "商品修改成功!");
//            result.put("flag", true);
//        } else {
//            result.put("message", "商品修改失败!");
//            result.put("flag", false);
//        }
//        ServletUtil.createSuccessResponse(200, result, response);
//
//    }

    /**
     * 删除项目
     *
     * @param request
     * @param response
     * @return
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String ids = request.getParameter("ids");

        int count = projectMapper.deleteByIds(ids.split(","));

        if (count > 0) {
            result.put("message", "项目删除成功!");
            result.put("flag", true);
        } else {
            result.put("message", "项目删除失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }


}
