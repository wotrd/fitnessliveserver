package com.example.libraryserver.customer.controller;

import com.example.libraryserver.conf.RespVo;
import com.example.libraryserver.customer.service.BusinessService;
import com.example.libraryserver.manager.po.BookPo;
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
    public RespVo getBusiness(@RequestBody BookPo businessPo){
        return businessService.getBusiness(businessPo);
    }

    @GetMapping("getBusinessById")
    public RespVo getBusiness(@RequestParam Long id){
        return businessService.getBusinessById(id);
    }


}
