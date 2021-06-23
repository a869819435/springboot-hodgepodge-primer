package com.mycompany.myspringboot.user.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LocalLock {
    /**
     * 默认的key
     */
    String key() default "";
}
