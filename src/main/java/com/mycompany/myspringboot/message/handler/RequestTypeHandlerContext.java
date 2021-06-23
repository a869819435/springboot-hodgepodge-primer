package com.mycompany.myspringboot.message.handler;

import com.mycompany.myspringboot.message.annotation.RequestTypeHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ywq
 * @data 2020-06-24
 * 注解的请求类型处理器
 * 注解的类型和对应实现类放入map[注册到Bean工厂]来处理选择用哪个实现类
 */
@Slf4j
@Component
public class RequestTypeHandlerContext implements ApplicationContextAware {
    @Autowired
    ApplicationContext applicationContext;

    private static final Map<String, Class> handlerMap = new HashMap<>(10);

    /**
     * 获取Bean是那个实现类
     * @param type
     * @return
     */
    public AbstractRequestTypeHandler getHandlerInstance(String type){
        //获取Bean的对象
        Class clazz = handlerMap.get(type);
        if (clazz == null){
            log.error("本次业务编码对应接口未找到:{}",type);
        }
        return (AbstractRequestTypeHandler) applicationContext.getBean(clazz);
    }

    /**
     * 设置Bean，注册Bean，就是对应的Type给出对应的实现类
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
        Map<String,Object> beans = applicationContext.getBeansWithAnnotation(RequestTypeHandler.class);
        if (beans != null && beans.size() > 0){
            for (Object serviceBean : beans.values()){
                String payType = serviceBean.getClass().getAnnotation(RequestTypeHandler.class).value();
                handlerMap.put(payType,serviceBean.getClass());
            }
        }
    }

}
