package com.mycompany.myspringboot.user.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@Slf4j
public class MyScheduled {
    @Scheduled(cron = "0/5 * * * * ?")
    public void task(){
        log.info("定时任务");
    }

}
