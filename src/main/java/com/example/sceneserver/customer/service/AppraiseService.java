package com.example.sceneserver.customer.service;

import com.example.sceneserver.conf.RespVo;
import com.example.sceneserver.manager.mapper.AppraiseMapper;
import com.example.sceneserver.manager.po.AppraisePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkaijin
 */
@Service
public class AppraiseService {


    @Autowired
    private AppraiseMapper appraiseMapper;

    public RespVo addAppraise(AppraisePo appraisePo) {
        int insert = appraiseMapper.insert(appraisePo);
        if (insert>0){
            return RespVo.builder().code("200").msg("success").build();
        }
        return RespVo.builder().code("201").msg("系统开小差了，请稍后再试").build();
    }

    public RespVo getAppraisesByName(String bName) {
        List<AppraisePo> appraisePos = appraiseMapper.queryByName(bName);
        return RespVo.builder().code("200").msg("success")
                .data(null ==appraisePos?new ArrayList<>():appraisePos)
                .build();
    }

}
