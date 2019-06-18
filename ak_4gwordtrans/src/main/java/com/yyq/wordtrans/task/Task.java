package com.yyq.wordtrans.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {

    @Scheduled(cron="0/5 * * * * ? ")
    public void create(){
        System.out.println("11111111111111");
    }
}
