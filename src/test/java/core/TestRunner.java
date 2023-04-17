package core;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", plugin = {
        "pretty", "html:target/cucumber-results/main/main.html", "json:target/cucumber-results/main/ReportPortal.json",
}, glue = "business")
public class TestRunner {

    @BeforeClass
    public static void beforeClass() throws Exception {
        Log4jLogger.info("Init config file");
        Config.initFile();
    }

    @AfterClass
    public static void afterClass() throws Exception {

    }
}
