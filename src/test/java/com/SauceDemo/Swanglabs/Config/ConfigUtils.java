package com.SauceDemo.Swanglabs.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtils {

    private Properties properties;

    // ميثود ديناميكية بتقرأ كائن الملف اللي مبعوت لها في اللوب حالياً
    public void loadProperties(File file) {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + file.getAbsolutePath(), e);
        }
    }

    public String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    public String getUserName() {
        return properties.getProperty("USERNAME"); // كابيتال بناءً على ملفاتك
    }

    public String getPassword() {
        return properties.getProperty("PASSWORD"); // كابيتال بناءً على ملفاتك
    }
}