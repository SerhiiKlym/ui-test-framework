package com.epam.final_task.serhii_klymenko.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties;

    static {
        try {
            FileReader file = new FileReader("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not read config.properties file.");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
