package com.mycompany.myspringboot.message.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RequestTypeHandler {
    String value();
}
