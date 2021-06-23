package com.mycompany.myspringboot.message.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractRequestTypeHandler {

    abstract public void handler(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
