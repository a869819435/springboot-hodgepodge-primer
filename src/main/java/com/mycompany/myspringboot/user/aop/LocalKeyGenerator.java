package com.mycompany.myspringboot.user.aop;

import com.alibaba.druid.util.StringUtils;
import com.mycompany.myspringboot.user.annotation.CacheParam;
import com.mycompany.myspringboot.user.annotation.RedisLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Component
public class LocalKeyGenerator {
    /**
     *
     * @param proceedingJoinPoint
     * @return
     */
    public String getLockKey(ProceedingJoinPoint proceedingJoinPoint){
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        final Object[] args = proceedingJoinPoint.getArgs();
        final Parameter[] parameters = method.getParameters();
        StringBuilder builder = new StringBuilder();
        //获取RedisLock里面指定的参数
        for (int i = 0; i < parameters.length ; i++){
            final CacheParam annotation = parameters[i].getAnnotation(CacheParam.class);
            if (annotation == null){
                continue;
            }
            builder.append(":").append(args[i]);
        }
        //获取实体里面包含的RedisLock里面指定的参数
        if (StringUtils.isEmpty(builder.toString())){
            final Annotation[][] parameterAnnotation = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotation.length ;i++){
                final Object object = args[i];
                final Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields){
                    final CacheParam annotation = field.getAnnotation(CacheParam.class);
                    if (annotation == null){
                        continue;
                    }
                    field.setAccessible(true);
                    builder.append(":").append(ReflectionUtils.getField(field,object));
                }
            }
        }
        return redisLock.perfix() + builder.toString();
    }
}
