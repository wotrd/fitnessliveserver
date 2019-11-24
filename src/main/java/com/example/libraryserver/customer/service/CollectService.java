package com.example.libraryserver.customer.service;

import com.example.libraryserver.conf.RespVo;
import com.example.libraryserver.manager.mapper.AppraiseMapper;
import com.example.libraryserver.manager.mapper.CollectMapper;
import com.example.libraryserver.manager.po.AppraisePo;
import com.example.libraryserver.manager.po.CollectPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkaijin
 */
@Service
public class CollectService {


    @Autowired
    private CollectMapper collectMapper;

    public RespVo addCollect(CollectPo collectPo) {
        int insert = collectMapper.insert(collectPo);
        if (insert>0){
            return RespVo.builder().code("200").msg("success").build();
        }
        return RespVo.builder().code("201").msg("系统开小差了，请稍后再试").build();
    }

    public RespVo getCollectByName(String name) {
        List<CollectPo> collectPos = collectMapper.queryByName(name);
        return RespVo.builder().code("200").msg("success")
                .data(null ==collectPos?new ArrayList<>():collectPos)
                .build();
    }

}
