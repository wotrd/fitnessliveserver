package com.example.hospital.controller;

import com.example.hospital.service.CaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("caseInfo")
public class CaseInfoController {

    @Autowired
    private CaseInfoService caseInfoService;


    @RequestMapping("getCaseInfo")
    public Map<String, Object> getOrderInfo(@RequestBody Map<String, Object> param) {
        return caseInfoService.getCaseInfo(param);
    }

    @RequestMapping("getCaseInfoByBqz")
    public Map<String, Object> getCaseInfoByBqz(@RequestBody Map<String, Object> param) {
        return caseInfoService.getCaseInfoByBqz(param);
    }


}
