package business;

import core.Config;
import core.pages.DashboardsPage;
import core.pages.LaunchesPage;
import core.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static core.Config.logger;

public class CommonSteps {
    public static WebDriver driver;
    LoginPage loginPage = new LoginPage();
    LaunchesPage launchesPage = new LaunchesPage();
    DashboardsPage dashboardsPage = new DashboardsPage();
    List<String> valuesToCheck = new ArrayList<>(Arrays.asList("Total", "Passed"));

    @BeforeTest
    public void setupConfigs() {
        logger.info("Starting precondition");
        Config.initFile();
        driver = Config.initDriver();
        loginPage.openPage("Login Page");
        loginPage.loginWithCreds(Config.properties.getProperty("username"), Config.properties.getProperty("password"));
        dashboardsPage.waitUntilPageLoaded();
    }

    @Test
    public void iCheckThatLaunchHasExpectedData() {
        logger.info("test starts");
        dashboardsPage.clickOnIconInSideMenu("Launches");
        launchesPage.waitUntilPageLoaded();
        launchesPage.isDataPresentInRow("Demo Api Tests", "5", valuesToCheck);
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }
}
