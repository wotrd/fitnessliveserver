package com.example.libraryserver.customer.service;

import com.example.libraryserver.conf.RespVo;
import com.example.libraryserver.manager.mapper.BookMapper;
import com.example.libraryserver.manager.po.BookPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkaijin
 */
@Service
public class BusinessService {


    @Autowired
    private BookMapper businessMapper;

    public RespVo getBusiness(BookPo businessPo) {
        List<BookPo> businessPos = businessMapper.queryAll(businessPo);
        return RespVo.builder().code("200").msg("success")
                .data(null ==businessPos?new ArrayList<>():businessPos)
                .build();
    }

    public RespVo getBusinessById(Long id) {
        BookPo businessPo = businessMapper.queryById(id);
        return RespVo.builder().code("200").msg("success")
                .data(businessPo)
                .build();
    }
}
