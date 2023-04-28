package business;

import core.Config;
import org.testng.annotations.Test;

import static core.Config.logger;

public class CommonSteps {
    @Test
    public void iInvokeLoginPageByUrl() {
        logger.info("test starts");
        logger.info("Init config file");
        Config.initFile();
        String url = Config.properties.getProperty("login.url");
        System.out.println("I run test with url " + url);
        logger.info("All ok");
    }
}
