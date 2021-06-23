package com.mycompany.myspringboot.user.dao;


import com.mycompany.myspringboot.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {

    List<String> queryUserNameList();

}
