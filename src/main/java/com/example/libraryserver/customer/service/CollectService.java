package com.example.libraryserver.customer.service;

import com.example.libraryserver.conf.RespVo;
import com.example.libraryserver.manager.mapper.AppraiseMapper;
import com.example.libraryserver.manager.mapper.BookMapper;
import com.example.libraryserver.manager.mapper.CollectMapper;
import com.example.libraryserver.manager.po.AppraisePo;
import com.example.libraryserver.manager.po.BookPo;
import com.example.libraryserver.manager.po.CollectPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.sun.org.apache.xerces.internal.impl.xpath.regex.CaseInsensitiveMap.get;

/**
 * @author wangkaijin
 */
@Service
public class CollectService {


    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private BookMapper bookMapper;

    public RespVo addCollect(CollectPo collectPo) {
        BookPo bookPo = bookMapper.queryById(collectPo.getId());
        collectPo.setAvatar(bookPo.getAvatar());
        collectPo.setName(bookPo.getName());
        collectPo.setPrice(bookPo.getPrice());
        collectPo.setType(bookPo.getType());
        int insert = collectMapper.insert(collectPo);
        if (insert > 0) {
            return RespVo.builder().code("200").msg("success").build();
        }
        return RespVo.builder().code("201").msg("系统开小差了，请稍后再试").build();

    }


    public RespVo getCollectByUserId(Long userId) {
        List<CollectPo> collectPos = collectMapper.queryByUserId(userId);
        collectPos.forEach(collectPo -> {
            List<BookPo> bookPos = bookMapper.queryByName(collectPo.getName());
            if (null != bookPos && bookPos.size() > 0) {
                BookPo bookPo = bookPos.get(0);
                if (null == bookPo || bookPo.getAvatar() == "") {
                    collectPo.setAvatar("");
                } else {
                    collectPo.setAvatar(bookPo.getAvatar());
                }
            }

        });
        return RespVo.builder().code("200").msg("success")
                .data(null == collectPos ? new ArrayList<>() : collectPos)
                .build();

    }

    public RespVo delete(Long id) {
        int insert = collectMapper.deleteById(id);
        return RespVo.builder().code("200").msg("success").build();

    }
}
