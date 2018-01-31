package com.gigi.scheduler.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by coco on 29-Jan-18.
 */
public class FileUtils {

    public static <T> T readJsonFromFile(String path, Class<T> clazz) throws Exception {
        byte[] jsonData = Files.readAllBytes(Paths.get(path));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

        return objectMapper.readValue(jsonData, clazz);
    }
}
