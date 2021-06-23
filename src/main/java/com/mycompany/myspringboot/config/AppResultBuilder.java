package com.mycompany.myspringboot.config;

public class AppResultBuilder {

    public static <T> AppResult<T> successNoData(ResultCode code){
        AppResult<T> result = new AppResult<T>();
        result.setCode(code.getCode());
        result.setMessage(code.getMessage());
        return result;
    }

    public static <T> AppResult<T> success(T t,ResultCode code){
        AppResult<T> result = new AppResult<T>();
        result.setCode(code.getCode());
        result.setMessage(code.getMessage());
        result.setData(t);
        return result;
    }

    public static <T> AppResult<T> fail(ResultCode code,String error){
        AppResult<T> result = new AppResult<T>();
        result.setCode(code.getCode());
        result.setMessage(code.getMessage() + ": " + error);
        return result;
    }
}
