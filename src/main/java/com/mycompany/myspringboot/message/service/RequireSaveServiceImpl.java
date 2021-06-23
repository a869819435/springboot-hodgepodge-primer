package com.mycompany.myspringboot.message.service;

import com.mycompany.myspringboot.message.annotation.RequestTypeHandler;
import com.mycompany.myspringboot.message.handler.AbstractRequestTypeHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequestTypeHandler("BIP2B341")
@Slf4j
public class RequireSaveServiceImpl extends AbstractRequestTypeHandler {
    @Override
    @ResponseBody
    public void handler(HttpServletRequest request, HttpServletResponse response) throws Exception{
        log.info("现在进入了报文下发接口");
    }
}
