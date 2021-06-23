package com.mycompany.myspringboot.user.controller;

import com.mycompany.myspringboot.config.AppResult;
import com.mycompany.myspringboot.config.AppResultBuilder;
import com.mycompany.myspringboot.config.ResultCode;
import com.mycompany.myspringboot.user.annotation.CacheParam;
import com.mycompany.myspringboot.user.annotation.LocalLock;
import com.mycompany.myspringboot.user.annotation.RedisLock;
import com.mycompany.myspringboot.user.async.AsyncTask;
import com.mycompany.myspringboot.user.entity.User;
import com.mycompany.myspringboot.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/userController")
public class ResfulUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AsyncTask asyncTask;

    @GetMapping(value = "/user")
    public List<User> findUser(){
        asyncTask.sendMessage();
        System.out.println("执行查询开始");
        return userService.getUserNameList();
    }

    @PostMapping(value = "/user")
//    @LocalLock(key = "book:arg[0]")
    @RedisLock(perfix = "redis")
    public int addUser(@CacheParam(key = "name") @RequestParam("name") String name){
        User user = new User();
        user.setUserId("202006241002203");
        user.setPhone("123123123");
        user.setUserName(name);
        return userService.addUser(user);
    }

    @PatchMapping(value = "/user/{userId}")
    public AppResult updateUser(@PathVariable("userId") String userId, @RequestParam("name") String name){
        User user = new User();
        user.setPhone("1231234564");
        user.setUserId(userId);
        user.setUserName(name);
        if (name.length() > 5){
//            int a = 1/0;
            return AppResultBuilder.fail(ResultCode.FAIL,"用户名长度大于5");
        }
        int cnt = userService.updateUser(user);
        return AppResultBuilder.success(cnt, ResultCode.SUCCESS);
    }

    @DeleteMapping(value = "/user/{userId}")
    public int deleteUser(@PathVariable("userId") String userId){
        return userService.deleteUser(userId);
    }
}
