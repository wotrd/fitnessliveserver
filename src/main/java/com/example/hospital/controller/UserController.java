package com.example.hospital.controller;

import com.example.hospital.domain.Patient;
import com.example.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("register")
    public Map<String, Object> register(@RequestBody Patient patient) {
        return userService.register(patient);
    }

    @RequestMapping("login")
    public Map<String, Object> login(@RequestBody Patient patient) {
        return userService.login(patient);
    }

    @RequestMapping("quitLogin")
    public Map<String, Object> quitLogin(@RequestBody Patient patient) {
        return userService.quitLogin(patient);
    }

    @RequestMapping("bindInfo")
    public Map<String, Object> bindInfo(@RequestBody Patient patient) {
        return userService.bindInfo(patient);
    }

}
