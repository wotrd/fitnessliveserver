package com.example.libraryserver.customer.service;

import com.example.libraryserver.conf.RespVo;
import com.example.libraryserver.manager.mapper.AppraiseMapper;
import com.example.libraryserver.manager.mapper.BookMapper;
import com.example.libraryserver.manager.mapper.OrderMapper;
import com.example.libraryserver.manager.po.AppraisePo;
import com.example.libraryserver.manager.po.BookPo;
import com.example.libraryserver.manager.po.OrderPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkaijin
 */
@Service
public class AppraiseService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AppraiseMapper appraiseMapper;

    public RespVo addAppraise(AppraisePo appraisePo) {
        int insert = appraiseMapper.insert(appraisePo);
        if (insert > 0) {
            return RespVo.builder().code("200").msg("success").build();
        }
        return RespVo.builder().code("201").msg("系统开小差了，请稍后再试").build();
    }

    public RespVo getAppraisesByName(String bName) {
        List<AppraisePo> appraisePos = appraiseMapper.queryByName(bName);
        return RespVo.builder().code("200").msg("success")
                .data(null == appraisePos ? new ArrayList<>() : appraisePos)
                .build();
    }

    public RespVo getAppraisesById(Long id) {
        BookPo bookPo = bookMapper.queryById(id);
        List<AppraisePo> appraisePos = appraiseMapper.queryByName(bookPo.getName());
        return RespVo.builder().code("200").msg("success")
                .data(null == appraisePos ? new ArrayList<>() : appraisePos)
                .build();
    }

}
