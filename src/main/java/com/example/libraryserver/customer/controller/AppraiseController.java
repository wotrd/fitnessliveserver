package com.example.libraryserver.customer.controller;

import com.example.libraryserver.conf.RespVo;
import com.example.libraryserver.customer.service.AppraiseService;
import com.example.libraryserver.manager.po.AppraisePo;
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
