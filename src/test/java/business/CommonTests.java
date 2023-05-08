package business;

import core.Config;
import core.Properties;
import org.testng.annotations.Test;

import static core.Config.logger;

public class CommonTests {
    @Test
    public void iInvokeLoginPageByUrl() {
        logger.info("test starts");
        logger.info("Init config file");
        Config.initFile();
        String url = Properties.LOGIN_URL;
        logger.info("All ok");
    }
}
