package com.example.animalsalesserver.customer.controller;

import com.example.animalsalesserver.conf.RespVo;
import com.example.animalsalesserver.customer.service.BusinessService;
import com.example.animalsalesserver.manager.po.BusinessPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangkaijin
 */
@RequestMapping("business")
@RestController
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping("getBusiness")
    public RespVo getBusiness(@RequestBody BusinessPo businessPo){
        return businessService.getBusiness(businessPo);
    }

}
