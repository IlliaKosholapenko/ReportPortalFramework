package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static Properties properties;
    private static String configPath = "src/test/resources/config/reportPortal.properties";
    public static ILogger logger = new Log4jLogger();
    public static IReporter reporter = AllureReporter.getInstance();

    public static void initFile() {
        logger.info("Init config file");
        properties = new Properties();
        try {
            logger.info("Trying to load config file");
            reporter.reportLog("Trying to load config file");
            InputStream inputFile = new FileInputStream(configPath);
            properties.load(inputFile);
            logger.info(String.format("Config File: %s is successfully loaded", configPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WebDriver initDriver(){
        logger.info("Init browser driver");
        String driverName = Config.properties.getProperty("browserName");
        if (driverName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.msedge.driver", "src/test/resources/drivers/msedgedriver.exe");
            return new EdgeDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
            return new ChromeDriver();
        }
    }

    private String getSystemProperty(String key) {
        return System.getProperty(key);
    }

    private String getPropertyValue(String key) {
        return this.properties.getProperty(key);
    }

}
