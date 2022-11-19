package com.example.sceneserver.customer.controller;

import com.example.sceneserver.conf.RespVo;
import com.example.sceneserver.customer.service.BusinessService;
import com.example.sceneserver.manager.po.BusinessPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("getBusinessById")
    public RespVo getBusiness(@RequestParam Long id){
        return businessService.getBusinessById(id);
    }


}
