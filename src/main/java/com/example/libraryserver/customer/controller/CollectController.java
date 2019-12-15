package com.example.libraryserver.customer.controller;

import com.example.libraryserver.conf.RespVo;
import com.example.libraryserver.customer.service.AppraiseService;
import com.example.libraryserver.customer.service.CollectService;
import com.example.libraryserver.manager.po.AppraisePo;
import com.example.libraryserver.manager.po.CollectPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangkaijin
 */
@RequestMapping("collect")
@RestController
public class CollectController {

    @Autowired
    private CollectService collectService;

    @PostMapping("addCollect")
    public RespVo addBusiness(@RequestBody CollectPo collectPo){
        return collectService.addCollect(collectPo);
    }

    @GetMapping("getCollect")
    public RespVo getCollectByName(@RequestParam Long userId){
        return collectService.getCollectByUserId(userId);
    }

    @GetMapping("delete")
    public RespVo delete(@RequestParam Long id){
        return collectService.delete(id);
    }


}
