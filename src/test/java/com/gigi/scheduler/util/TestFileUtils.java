package com.gigi.scheduler.util;

import org.junit.Test;

/**
 * Created by coco on 29-Jan-18.
 */
public class TestFileUtils {

    @Test
    public void TestLoadJsonFromFile() throws Exception {

        String path = "C:\\local\\conf\\flash.conf.json";

        Config config = FileUtils.readJsonFromFile(path, Config.class);

        System.out.println(config.getHeaders());
    }
}
