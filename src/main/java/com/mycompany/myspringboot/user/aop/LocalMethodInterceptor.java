package com.mycompany.myspringboot.user.aop;

import com.alibaba.druid.util.StringUtils;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mycompany.myspringboot.user.annotation.LocalLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 配置拦截后动作内容
 * @author ywq
 */
@Aspect
@Configuration
public class LocalMethodInterceptor {
    /**
     * 创建缓存
     * maximumSize(180) //最大缓存个数
     * expireAfterWrite(5, TimeUnit.SECONDS) //缓存5秒过期
     */
    private static final Cache<String, Object> CACHE = CacheBuilder
            .newBuilder()
            .maximumSize(180)
            .expireAfterWrite(50, TimeUnit.SECONDS)
            .build();

    /**
     * Around表明是环绕增强,触发条件为任意的公共方法以及有对应[路径]的注解
     * @param proceedingJoinPoint
     * @return
     */
    @Around("execution(public * *(..)) && @annotation(com.mycompany.myspringboot.user.annotation.LocalLock)")
    public  Object interceptor(ProceedingJoinPoint proceedingJoinPoint){
        //获取切点
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获取拦截到的方法
        Method method = signature.getMethod();
        LocalLock localLock = method.getAnnotation(LocalLock.class);
        String key = getKey(localLock.key(),proceedingJoinPoint.getArgs());
        if (!StringUtils.isEmpty(key)){
            if (CACHE.getIfPresent(key) != null){
                //如果有这个key，则抛出不要重复提交
                throw new RuntimeException("请勿重复提交");
            }
            //放入缓存
            CACHE.put(key,key);
        }
        try{
            return proceedingJoinPoint.proceed();
        }catch (Throwable throwable){
            throwable.printStackTrace();
            throw new RuntimeException("服务器异常");
        }
    }

    /**
     * key的生成策略
     */
    private String getKey(String keyExpress,Object[] args){
        for (int i = 0; i < args.length ; i++ ){
            keyExpress = keyExpress.replace("arg[" + i + "]",args[i].toString());
        }
        return keyExpress;
    }
}
