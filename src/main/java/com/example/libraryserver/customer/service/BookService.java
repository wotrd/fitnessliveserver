package com.example.libraryserver.customer.service;

import com.example.libraryserver.conf.RespVo;
import com.example.libraryserver.manager.mapper.BookMapper;
import com.example.libraryserver.manager.po.BookPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkaijin
 */
@Service
public class BookService {


    @Autowired
    private BookMapper bookMapper;

    public RespVo getBook(BookPo bookPo) {
        List<BookPo> bookPos = bookMapper.queryAll(bookPo);
        return RespVo.builder().code("200").msg("success")
                .data(null ==bookPos?new ArrayList<>():bookPos)
                .build();
    }

    public RespVo getBookById(Long id) {
        BookPo bookPo = bookMapper.queryById(id);
        return RespVo.builder().code("200").msg("success")
                .data(bookPo)
                .build();
    }
}
