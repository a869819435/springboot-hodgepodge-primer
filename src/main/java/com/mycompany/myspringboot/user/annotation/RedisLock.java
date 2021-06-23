package com.mycompany.myspringboot.user.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisLock {
    /**
     * redis锁的key  前缀
     */
    String perfix() default "";
}
