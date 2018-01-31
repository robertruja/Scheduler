package com.gigi.scheduler.task;

import java.util.Map;

/**
 * Created by coco on 29-Jan-18.
 */
public interface Task {

    void execute(Map<String,String> properties);
}
