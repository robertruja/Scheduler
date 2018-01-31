package com.gigi.scheduler.task;

import java.util.Map;

/**
 * Created by coco on 30-Jan-18.
 */
public class OtherTest implements Task {
    @Override
    public void execute(Map<String,String> properties) {
        System.out.println("Other task " + this);

        System.out.println("Properties: " + properties);
    }
}
