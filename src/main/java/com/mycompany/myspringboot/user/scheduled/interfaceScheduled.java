package com.mycompany.myspringboot.user.scheduled;

import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
@EnableScheduling
public class interfaceScheduled implements SchedulingConfigurer {

    @Mapper
    public interface CronMapper{
        @Select("SELECT cron FROM cron LIMIT 1")
        public String getCron();
    }

    @Autowired
    CronMapper cronMapper;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                // 1. 添加定时任务内容(Runnable)
                () -> System.out.println("执行动态定时任务：" + LocalDateTime.now().toLocalDate()),
                // 2. 设置执行周期(Trigger)
                triggerContext -> {
                    // 2.1 从数据库获取执行周期
                    String cron = cronMapper.getCron();
                    // 2.2 合法性校验
                    if (StringUtils.isEmpty(cron)){
                        // 默认的定时方式
                    }
                    // 2.3 返回执行周期
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
