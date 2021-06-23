package com.mycompany.myspringboot.user.aop;

import com.alibaba.druid.util.StringUtils;
import com.mycompany.myspringboot.user.annotation.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Configuration
@Slf4j
public class RedisMethodInterceptor {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private LocalKeyGenerator localKeyGenerator;

    @Around("execution(public * *(..)) && @annotation(com.mycompany.myspringboot.user.annotation.RedisLock)")
    public Object interceptor(ProceedingJoinPoint proceedingJoinPoint){
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
//        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
//        Method method = signature.getMethod();
//        RedisLock lock = method.getAnnotation(RedisLock.class);
        String key = localKeyGenerator.getLockKey(proceedingJoinPoint);
        if (!StringUtils.isEmpty(key)){
            if (opsForValue.get(key) != null){
                log.error("表单重复提交了");
                throw new RuntimeException("请勿重复提交");
            }
            //设置键为key,值为key,时间为50,单位为秒
            opsForValue.set(key,key,50, TimeUnit.SECONDS);
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
