package com.example.libraryserver.manager.controller;

import com.example.libraryserver.manager.service.BookManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author tengj
 * @date 2017/3/13
 */
@Slf4j
@Controller
@RequestMapping("/manager/bookmanager")
public class BookManagerController {

    @Autowired
    private BookManagerService bookManagerService;

    @RequestMapping("book-manager")
    public String bookManager(){
        return "book-manager";
    }

    @RequestMapping(value = "/getBookes",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void queryBookList(HttpServletRequest request ,HttpServletResponse response){
        bookManagerService.queryBookList(request, response);
    }

    @RequestMapping(value = "/addBook",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void addBook(HttpServletRequest request , HttpServletResponse response){
        bookManagerService.addBook(request, response);

    }

    @RequestMapping(value = "/update",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void update(HttpServletRequest request , HttpServletResponse response){
        bookManagerService.updateBusiness(request, response);

    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void delete(HttpServletRequest request , HttpServletResponse response){
        bookManagerService.delete(request, response);

    }



}