package com.gigi.scheduler.task;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by coco on 30-Jan-18.
 */

public class TestQuartz {
    public static void main(String[] args) throws Exception {
        //JobDetail job = new JobDetail();
        //job.setName("dummyJobName");
        //job.setJobClass(HelloJob.class);


        //CronTrigger trigger = new CronTrigger();
        //trigger.setName("dummyTriggerName");
        //trigger.setCronExpression("0/5 * * * * ?");

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();

        //schedule it


        JobDetail job = JobBuilder.newJob(TesJob.class)
                .withIdentity("dummyJobName", "group1").build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

    public static class TesJob implements Job {

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println("in test job");
        }
    }
}
