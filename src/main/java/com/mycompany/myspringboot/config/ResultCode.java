package com.mycompany.myspringboot.config;

public enum ResultCode {

    SUCCESS(200,"成功"),//成功
    FAIL(400,"失败"),//失败
    CHECK_FAIL(405,"数据检查异常"),//数据检查异常
    UNAUTHORIZED(401,"未认证（签名错误）"),//未认证（签名错误）
    NOT_FOUND(404,"接口不存在"),//接口不存在
    INTERNAL_SERVER_ERROR(500,"服务器内部错误"),//服务器内部错误
    OK(0,"成功"), //成功
    NOK(500,"失败"); //失败

    public int code;

    public String message;

    ResultCode(int code,String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
