package com.mycompany.myspringboot.user.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_sys_user")
@Setter
@Getter
public class User {
    @Id
    private String userId;

    private String userName;

    private String phone;
}
