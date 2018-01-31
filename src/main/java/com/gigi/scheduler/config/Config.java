package com.gigi.scheduler.config;

import java.util.Map;

/**
 * Created by coco on 29-Jan-18.
 */
public class Config {

    public static final String CLASS = "class";
    public static final String SCHEDULE = "schedule";
    public static final String PROPERTIES = "properties";

    Map<String, Map<String,Object>> tasks;

    public Map<String, Map<String, Object>> getTasks() {
        return tasks;
    }

    public void setTasks(Map<String, Map<String, Object>> tasks) {
        this.tasks = tasks;
    }
}
