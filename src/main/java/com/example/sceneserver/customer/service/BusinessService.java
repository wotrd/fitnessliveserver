package com.example.sceneserver.customer.service;

import com.example.sceneserver.conf.RespVo;
import com.example.sceneserver.manager.mapper.BusinessMapper;
import com.example.sceneserver.manager.po.BusinessPo;
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

    public RespVo getBusinessById(Long id) {
        BusinessPo businessPo = businessMapper.queryById(id);
        return RespVo.builder().code("200").msg("success")
                .data(businessPo)
                .build();
    }
}
