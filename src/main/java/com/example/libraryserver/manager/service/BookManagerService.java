package com.example.libraryserver.manager.service;


import com.alibaba.fastjson.JSONObject;
import com.example.libraryserver.customer.qo.UserLoginQo;
import com.example.libraryserver.manager.po.BookPo;
import com.example.libraryserver.manager.mapper.BookMapper;
import com.example.libraryserver.tools.ServletUtil;
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
public class BookManagerService {

    @Autowired
    private BookMapper bookMapper;

    /**
     * 获取图书列表
     *
     * @param request
     * @param response
     * @return
     */
    public void queryBookList(HttpServletRequest request, HttpServletResponse response) {
        // 取得当前页数,注意这是jqgrid自身的参数
        int pageNum = Integer.parseInt(request.getParameter("page"));
        // 取得每页显示行数，,注意这是jqgrid自身的参数
        int pageSize = Integer.parseInt(request.getParameter("rows"));

        String name = request.getParameter("name");

        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<BookPo> businesses = bookMapper.queryLikeName(name);

        JSONObject jo = new JSONObject();
        jo.put("rows", businesses);
        jo.put("total", page.getPages());
        jo.put("records", page.getTotal());
        ServletUtil.createSuccessResponse(200, jo, response);

    }

    /**
     * 添加图书
     *
     * @param request
     * @param response
     * @return
     */
    public void addBook(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String price = request.getParameter("price");
        String remark = request.getParameter("remark");
        String avatar = request.getParameter("avatar");
        List<BookPo> bookPos = bookMapper.queryByName(name);
        if (null != bookPos && bookPos.size()>0) {
            result.put("message", "该图书已经存在!");
            result.put("flag", false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        BookPo businessPo = BookPo.builder().name(name).type(type).price(new BigDecimal(price)).build();
        UserLoginQo userLoginQo = (UserLoginQo) request.getSession().getAttribute("loginUser");
        businessPo.setSellerName(userLoginQo.getAccount());
        businessPo.setRemark(remark);
        businessPo.setAvatar(avatar);
        int insert = bookMapper.insert(businessPo);

        if (insert > 0) {
            result.put("message", "图书添加成功!");
            result.put("flag", true);
        } else {
            result.put("message", "图书添加失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }

    /**
     * 修改图书
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
        String remark = request.getParameter("upremark");
        String avatar = request.getParameter("upavatar");

        BookPo businessPo = BookPo.builder().id(Long.parseLong(id))
                .name(name).type(type)
                .price(new BigDecimal(price))
                .remark(remark)
                .avatar(avatar)
                .build();

        int insert = bookMapper.update(businessPo);

        if (insert > 0) {
            result.put("message", "图书修改成功!");
            result.put("flag", true);
        } else {
            result.put("message", "图书修改失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }

    /**
     * 删除图书
     *
     * @param request
     * @param response
     * @return
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String ids = request.getParameter("ids");

        int count = bookMapper.deleteByIds(ids.split(","));

        if (count > 0) {
            result.put("message", "图书删除成功!");
            result.put("flag", true);
        } else {
            result.put("message", "图书删除失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }



}
