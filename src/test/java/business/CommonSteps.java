package business;

import core.Config;
import core.Log4jLogger;
import io.cucumber.java.en.Given;


public class CommonSteps {

@Given("I invoke login page by url")
    public void iInvokeLoginPageByUrl(){
    String url = Config.properties.getProperty("login.url");
    System.out.println("I run test with url "+ url);
    Log4jLogger.info("All ok");
}
}
