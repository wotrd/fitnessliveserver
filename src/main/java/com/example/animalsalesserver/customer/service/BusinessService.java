package com.example.animalsalesserver.customer.service;

import com.example.animalsalesserver.conf.RespVo;
import com.example.animalsalesserver.manager.mapper.BusinessMapper;
import com.example.animalsalesserver.manager.po.BusinessPo;
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
    private BusinessMapper businessMapper;

    public RespVo getBusiness(BusinessPo businessPo) {
        List<BusinessPo> businessPos = businessMapper.queryAll(businessPo);
        return RespVo.builder().code("200").msg("success")
                .data(null ==businessPos?new ArrayList<>():businessPos)
                .build();
    }
}
