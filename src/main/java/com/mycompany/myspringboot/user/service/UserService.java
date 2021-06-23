package com.mycompany.myspringboot.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mycompany.myspringboot.user.dao.UserMapper;
import com.mycompany.myspringboot.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public PageInfo<String> queryUserNameList(Integer pageNum, Integer pageSize){
        //设置分页的页数和页码，一定要放在查询前,且仅对一条sql进行分页
        PageHelper.startPage(pageNum,pageSize);
        List<String> list = userMapper.queryUserNameList();
        //返回一些分页的信息
        PageInfo<String> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public List<User> getUserNameList(){
        List<User> list = userMapper.selectAll();
        return list;
    }

    public int addUser(User user){
        return userMapper.insert(user);
    }

    public int updateUser(User user){
        return userMapper.updateByPrimaryKey(user);
    }

    public int deleteUser(String userId){
        return userMapper.deleteByPrimaryKey(userId);
    }
}
