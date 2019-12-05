package com.example.animalsalesserver.customer.service;

import com.example.animalsalesserver.conf.RespVo;
import com.example.animalsalesserver.manager.mapper.ComplaintMapper;
import com.example.animalsalesserver.manager.po.ComplaintPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkaijin
 */
@Service
public class ComplaintService {


    @Autowired
    private ComplaintMapper complaintMapper;

    public RespVo addComplaint(ComplaintPo complaintPo) {
        int insert = complaintMapper.insert(complaintPo);
        if (insert>0){
            return RespVo.builder().code("200").msg("success").build();
        }
        return RespVo.builder().code("201").msg("系统开小差了，请稍后再试").build();
    }

    public RespVo getComplaintByName(String name) {
        List<ComplaintPo> complaintPos = complaintMapper.queryLikeName(name);
        return RespVo.builder().code("200").msg("success")
                .data(null ==complaintPos?new ArrayList<>():complaintPos)
                .build();
    }

}
