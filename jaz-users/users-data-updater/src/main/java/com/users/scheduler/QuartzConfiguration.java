package com.users.scheduler;

import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
@RequiredArgsConstructor
public class QuartzConfiguration {


    @Bean
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(UpdaterJob.class);
        jobDetailFactory.setDescription("Invoke Sample Job service...");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean trigger(JobDetail job) {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(job);
        trigger.setRepeatInterval(10000);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return trigger;
    }

//    @Bean
//    public JobDetail updaterJobDetail() {
//        return JobBuilder.newJob(UpdaterJob.class)
//                .withIdentity("updaterJob")
//                .storeDurably()
//                .build();
//    }

    // Definiowanie wyzwalacza (Trigger)
//    @Bean
//    public Trigger updaterJobTrigger(JobDetail updaterJobDetail) {
//        return TriggerBuilder.newTrigger()
//                .forJob(updaterJobDetail)
//                .withIdentity("updaterTrigger")
//                .withSchedule(
//                        SimpleScheduleBuilder.simpleSchedule()
//                        .withIntervalInSeconds(10)
//                        .repeatForever())
//                .build();
//    }

//    Przykład wyrażenia cron:
//    0/15 * * * * ? – wykonuj co 15 sekund.
//    0 0/5 * * * ? – wykonuj co 5 minut.
//    0 10 12 * * ? – wykonuj codziennie o 12:00.
//    @Bean
//    public Trigger cronJobTrigger(JobDetail sampleJobDetail) {
//        return TriggerBuilder.newTrigger()
//                .forJob(sampleJobDetail)
//                .withIdentity("cronTrigger")
//                .withSchedule(CronScheduleBuilder.cronSchedule("0/15 * * * * ?"))
//                .build();
//    }
}
