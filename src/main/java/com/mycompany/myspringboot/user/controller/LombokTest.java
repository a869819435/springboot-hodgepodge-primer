package com.mycompany.myspringboot.user.controller;

import com.mycompany.myspringboot.user.service.UserService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/lombokTest")
public class LombokTest {

    @GetMapping("/query")
    //参数前可以有多个注解
    public String query(@NonNull @RequestParam("name") String name){
        log.info("用户姓名是" + name);
        return log.toString();
    }
}
