package com.speechrecognition.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置类
 */
@Configuration
public class ThreadConfig {
    @Value("${thread.corePoolSize}")
    private   int corePoolSize ;
    @Value("${thread.maxPoolSize}")
    private   int maxPoolSize ;
    @Value("${thread.queueCapacity}")
    private   int queueCapacity ;

    @Bean(name="asyncTaskExecutor")
    public Executor executor(){

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        /**
         * ThreadPoolExecutor.AbortPolicy()  抛出java.util.concurrent.RejectedExecutionException异常
         * ThreadPoolExecutor.CallerRunsPolicy() 重试添加当前的任务，他会自动重复调用execute()方法
         * ThreadPoolExecutor.DiscardOldestPolicy() 抛弃旧的任务
         * ThreadPoolExecutor.DiscardPolicy() 抛弃当前的任务
         */
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());

        //等待任务在关机时完成--表明等待所有线程执行完
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return  taskExecutor;
    }


}
