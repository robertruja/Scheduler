package com.gigi.scheduler.config;

import com.gigi.scheduler.task.Task;

import java.util.Map;

/**
 * Created by coco on 30-Jan-18.
 */
public class TaskConfig {

    private Task task;
    private String schedule;
    private Map<String, String> properties;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}
