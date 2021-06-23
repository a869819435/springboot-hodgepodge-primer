package com.mycompany.myspringboot.user.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {
    /**
     * 获取字段名字 为指定唯一
     */
    String key() default "";
}
