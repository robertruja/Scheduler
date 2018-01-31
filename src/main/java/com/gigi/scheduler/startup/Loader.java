package com.gigi.scheduler.startup;

import com.gigi.scheduler.config.Config;
import com.gigi.scheduler.config.TaskConfig;
import com.gigi.scheduler.task.Task;
import com.gigi.scheduler.util.FileUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by coco on 30-Jan-18.
 */
public class Loader {

    private static Logger LOG = Logger.getLogger(App.class);

    public List<TaskConfig> load() throws Exception {

        String configLocation = System.getProperty("scheduler.config.location");

        if(configLocation == null) {
            LOG.error("Fatal: Unable to find config location");
            System.exit(-1);
        }
        Config config = FileUtils.readJsonFromFile(configLocation, Config.class);

        List<TaskConfig> taskList = new ArrayList<>();

        for(Map.Entry<String, Map<String,Object>> entry: config.getTasks().entrySet()) {

            Map<String, Object> taskJson = entry.getValue();
            String taskClassName = (String)taskJson.get(Config.CLASS);

            Task task;
            TaskConfig taskConfig = new TaskConfig();

            if(taskClassName != null) {
                task = tryInvoke(taskClassName);
                taskConfig.setTask(task);
            } else {
                throw new RuntimeException("No class defined for task: " + entry.getKey());
            }

            String schedule = (String) taskJson.get(Config.SCHEDULE);

            if(schedule == null) {
                LOG.warn("No schedule defined for task: " + entry.getKey() + ". It will never be executed");
            } else {
                taskConfig.setSchedule(schedule);
                taskList.add(taskConfig);
            }

            Map<String,String> taskProperties = (Map<String,String>)taskJson.get(Config.PROPERTIES);
            if(taskProperties != null) {
                taskConfig.setProperties(taskProperties);
            }
        }
        return taskList;
    }

    private Task tryInvoke(String className) {

        try {

            Class clazz = Class.forName(className);

            if(!Task.class.isAssignableFrom(clazz)) {
                throw new RuntimeException("Class " + className + " is not an implementation of com.gigi.scheduler.task.Task");
            }
            return (Task) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
