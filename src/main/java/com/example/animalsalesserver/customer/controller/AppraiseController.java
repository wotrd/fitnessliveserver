package com.example.animalsalesserver.customer.controller;

import com.example.animalsalesserver.conf.RespVo;
import com.example.animalsalesserver.customer.service.AppraiseService;
import com.example.animalsalesserver.manager.po.AppraisePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangkaijin
 */
@RequestMapping("appraise")
@RestController
public class AppraiseController {

    @Autowired
    private AppraiseService appraiseService;

    @PostMapping("addAppraise")
    public RespVo addBusiness(@RequestBody AppraisePo appraisePo){
        return appraiseService.addAppraise(appraisePo);
    }

    @GetMapping("getAppraiseByName")
    public RespVo getAppraisesByName(@RequestParam String bName){
        return appraiseService.getAppraisesByName(bName);
    }


}
