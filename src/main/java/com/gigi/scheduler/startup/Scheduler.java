package com.gigi.scheduler.startup;

import com.gigi.scheduler.config.TaskConfig;
import com.gigi.scheduler.task.Task;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by coco on 29-Jan-18.
 */
public class Scheduler {

    private Logger LOG = Logger.getLogger(getClass());
    private ExecutorService exec;

    private org.quartz.Scheduler scheduler;

    private boolean started;

    public void start() {

        if(started) {
            LOG.warn("Scheduler already started!");
            return;
        }
        if(scheduler == null) {

        }

        if(scheduler != null) {
            exec = Executors.newCachedThreadPool();
            exec.submit(() -> {
                try {
                    started = true;
                    scheduler.start();
                } catch (SchedulerException e) {
                    started = false;
                    LOG.error("Error while trying to start scheduler: ", e);
                }
            });
        }
    }

    public void stop() {
        try {
            scheduler.shutdown();
            exec.shutdown();
            while(exec.isTerminated()){
                Thread.currentThread().sleep(10);
            }
            started = false;
        } catch (Exception e) {
            LOG.warn("Error whily trying to stop scheduler: ", e);
        }
    }

    public void schedule(TaskConfig config) {

        if(scheduler != null) {
            try {
                scheduler.scheduleJob(JobBuilder.newJob(AbstractJob.class).usingJobData(new JobDataMap(){
                    {
                        put("task", config.getTask());
                        put("properties", config.getProperties());
                    }
                }).build(),
                        TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(config.getSchedule())).build());
            } catch (SchedulerException e) {
                throw new RuntimeException("Unable to schedule job. Exception was: " + e);
            }
        } else {
            throw new RuntimeException("The default scheduler was null, can not schedule job");
        }
    }

    public boolean isStarted() {
        return started;
    }

    private Scheduler() {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException e) {
            throw new RuntimeException("Error while trying to start scheduler: ", e);
        }
    }

    private static Scheduler defaultScheduler;
    public static Scheduler getDefault() {
        if(defaultScheduler == null) {
            synchronized (Scheduler.class) {
                if(defaultScheduler == null) {
                    defaultScheduler = new Scheduler();
                }
            }
        }
        return defaultScheduler;
    }

    public static class AbstractJob implements Job {

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            ((Task)jobExecutionContext.getMergedJobDataMap().get("task")).execute((Map<String, String>)jobExecutionContext.getMergedJobDataMap().get("properties"));
        }
    }
}
