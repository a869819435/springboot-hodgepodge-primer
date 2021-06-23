package com.mycompany.myspringboot.config;

import com.google.common.collect.Maps;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
public class ResfulApiExceptionHandler {
    /**
     * 设置请求参数错误的时候返回的异常信息，这样处理http的状态码是200,不过返回信息是500
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public Map<String, Object> requestExceptionHandler(HttpServletRequest request,MissingServletRequestParameterException exception){
        Map<String, Object> error = Maps.newHashMap();
        error.put("status",500);
        error.put("messager","参数" + exception.getParameterName() + "错误");
        return error;
    }

    /**
     * 运行时异常
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Map<String, Object> requestExceptionHandler(HttpServletRequest request,RuntimeException exception){
        Map<String, Object> error = Maps.newHashMap();
        error.put("status",500);
        error.put("messager", exception.getMessage());
        return error;
    }

    /**
     * 找不到具体错误的，就返回这个异常信息
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> exceptionHandler(HttpServletRequest request,Exception exception){
        Map<String, Object> error = Maps.newHashMap();
        error.put("status",500);
        error.put("messager","系统错误,请联系管理员");
        return error;
    }
}
