package com.example.animalsalesserver.customer.service;

import com.example.animalsalesserver.conf.RespVo;
import com.example.animalsalesserver.manager.mapper.BusinessMapper;
import com.example.animalsalesserver.manager.po.BusinessPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangkaijin
 */
@Service
public class BusinessService {


    @Autowired
    private BusinessMapper businessMapper;

    public RespVo getBusiness(BusinessPo businessPo) {
        return RespVo.builder().code("200").msg("success").data(businessMapper.queryAll(businessPo)).build();
    }
}
