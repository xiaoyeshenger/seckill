package com.jsxa.vapp.common.quartz;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class SchedulerConfig {

    @Autowired
    private MyJobFactory myJobFactory;

    /**
     *  配置SchedulerFactoryBean   //创建一个SchedulerFactoryBean 实例
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
         // Spring提供SchedulerFactoryBean为Scheduler提供配置信息，并被Spring容器管理其生命周期
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        // 延时启动（秒）
         schedulerFactoryBean.setStartupDelay(1);
         // 设置quartz的配置文件(没有就不配置，需要的话直接配置quartz.properties)
        //schedulerFactoryBean.setConfigLocation(new ClassPathResource("/quartz.properties"));
        // 设置自定义Job Factory，用于Spring管理Job Bean
        schedulerFactoryBean.setJobFactory(myJobFactory);
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler(){
        // 创建一个调度容器，并由Spring管理
        return schedulerFactoryBean().getScheduler();
    }



}