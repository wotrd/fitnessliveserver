package com.example.libraryserver.customer.controller;

import com.example.libraryserver.conf.RespVo;
import com.example.libraryserver.customer.service.BookService;
import com.example.libraryserver.manager.po.BookPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangkaijin
 */
@RequestMapping("book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("getBooks")
    public RespVo getBooks(@RequestBody BookPo bookPo){
        return bookService.getBook(bookPo);
    }

    @GetMapping("getBookById")
    public RespVo getBusiness(@RequestParam Long id){
        return bookService.getBookById(id);
    }


}
