package com.mycompany.myspringboot.config;

import lombok.Data;

@Data
public class AppResult<T> {
    private int code;
    private String message;
    private T data;

}
