package com.mycompany.myspringboot.user.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableAsync
public class AsyncTask {
    @Async
    public void sendMessage() {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        log.info("选择进行短信群发");
    }
}
