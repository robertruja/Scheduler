package com.gigi.scheduler.task;

import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by coco on 30-Jan-18.
 */
public class TestTask implements Task {

    private Logger log = Logger.getLogger(TestTask.class);

    @Override
    public void execute(Map<String, String> properties) {

       log.info("Running test task");

       if(properties != null && !properties.isEmpty()) {
           log.info("Found task properties: " + properties);
       } else {
           log.warn("no propertis found for task");
       }
    }
}
