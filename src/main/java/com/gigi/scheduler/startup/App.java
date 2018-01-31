package com.gigi.scheduler.startup;

import com.gigi.scheduler.config.TaskConfig;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by coco on 29-Jan-18.
 */
public class App {

    private static Logger LOG = Logger.getLogger(App.class);

    public static void main(String[] args) throws Exception {

        try {
            Scheduler scheduler = Scheduler.getDefault();
            List<TaskConfig> list = new Loader().load();
            list.forEach(conf -> scheduler.schedule(conf));
            scheduler.start();
            while(scheduler.isStarted()) {
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception ex) {
            LOG.error("A fatal error occured while trying to execute scheduler: " + ex);
        }
    }
}
