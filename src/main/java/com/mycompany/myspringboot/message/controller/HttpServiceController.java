package com.mycompany.myspringboot.message.controller;

import com.mycompany.myspringboot.message.handler.RequestTypeHandlerContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  采用策略模式，避免过多的if-else
 *  提高扩展性，添加新的功能直接添加新的类，根据type选择对应的实现
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/HttpService")
public class HttpServiceController {

    private final RequestTypeHandlerContext requestTypeHandlerContext;

    @RequestMapping(value = "/httpserver")
    @Transactional(rollbackFor = Exception.class)
    public void requestProcessor(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String type = request.getParameter("type");
        this.requestTypeHandlerContext.getHandlerInstance(type).handler(request,response);
    }
}
