package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static Properties properties;
    private static String configPath = "src/test/resources/config/reportPortal.properties";

    public static void initFile() {
        properties = new Properties();
        try {
            Log4jLogger.info("Trying to load config file");
            InputStream inputFile = new FileInputStream(configPath);
            properties.load(inputFile);
            Log4jLogger.info(String.format("Config File: %s is successfully loaded", configPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getSystemProperty(String key) {
        return System.getProperty(key);
    }

    private String getPropertyValue(String key) {
        return this.properties.getProperty(key);
    }

}
