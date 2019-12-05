package com.example.animalsalesserver.customer.controller;

import com.example.animalsalesserver.conf.RespVo;
import com.example.animalsalesserver.customer.service.ComplaintService;
import com.example.animalsalesserver.manager.po.ComplaintPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangkaijin
 */
@RequestMapping("complaint")
@RestController
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping("addComplaint")
    public RespVo addBusiness(@RequestBody ComplaintPo complaintPo){
        return complaintService.addComplaint(complaintPo);
    }

    @GetMapping("getComplaintByName")
    public RespVo getAppraisesByName(@RequestParam String name){
        return complaintService.getComplaintByName(name);
    }


}
