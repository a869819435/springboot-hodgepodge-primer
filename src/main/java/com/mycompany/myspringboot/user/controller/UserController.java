package com.mycompany.myspringboot.user.controller;

import com.github.pagehelper.PageInfo;
import com.mycompany.myspringboot.user.entity.User;
import com.mycompany.myspringboot.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
@Api(tags = "用户信息模块")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Cacheable(value = "mycache",key = "#root.methodName")
    @GetMapping("/queryUserNameList")
    @ApiOperation(value = "用户名查询", notes = "如果不传任何分页参数就不分页,分页必须传pageNum,pageSize",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "第几页", dataType = "int",defaultValue = "0"),
            @ApiImplicitParam(name = "pageSize",value = "一页几条", dataType = "int",defaultValue = "0")
    })
    public PageInfo<String> queryUserNameList(@RequestParam(defaultValue = "0") Integer pageNum, @RequestParam(defaultValue = "0") Integer pageSize){
        System.out.println("进入查询+++++++++++++++++++++++++++++++++++++++++++++++++");
        return userService.queryUserNameList(pageNum,pageSize);
    }

    /**
     * 测试删除缓存操作
     * @return
     */
    @CacheEvict(value = "mycache", allEntries = true, beforeInvocation = true)
    @GetMapping("/deleteCache")
    public String deleteCache(){
        System.out.println("进入删除++++++++++++++++");
        return "删除成功";
    }

    /**
     * 使用通用mapper的查询
     * @return
     */
    @GetMapping("/getUserNameList")
    public List<User> getUserNameList(){
        return userService.getUserNameList();
    }

    @GetMapping("/testLog")
    public void testLog(){
        logger.error("我是错误日志");
        logger.info("我是详细日志");
    }
}
