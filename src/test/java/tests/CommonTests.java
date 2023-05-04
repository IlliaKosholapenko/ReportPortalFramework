package tests;

import core.Config;
import business.pages.DashboardsPage;
import business.pages.LaunchesPage;
import business.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static core.Config.logger;

public class CommonTests {
    public static WebDriver driver;
    LoginPage loginPage = new LoginPage();
    LaunchesPage launchesPage = new LaunchesPage();
    DashboardsPage dashboardsPage = new DashboardsPage();
    List<String> valuesToCheck = new ArrayList<>(Arrays.asList("Product Bug"));

    @BeforeTest
    public void setupConfigs() {
        logger.info("Starting precondition");
        Config.initFile();
        driver = Config.initDriver();
        loginPage.openPage("Login Page");
        loginPage.waitUntilPageLoaded();
        loginPage.loginWithCreds(Config.properties.getProperty("username"), Config.properties.getProperty("password"));
        dashboardsPage.waitUntilPageLoaded();
    }

    @Test
    public void iCheckThatLaunchHasExpectedData() {
        logger.info("test starts");
        dashboardsPage.clickOnIconInSideMenu("Launches");
        launchesPage.waitUntilPageLoaded();
        launchesPage.isDataPresentInRow("Demo Api Tests", "3", valuesToCheck);
    }

    @Test
    public void iCheckTableSorting(){
        logger.info("Sorting test starts");
        dashboardsPage.clickOnIconInSideMenu("Launches");
        launchesPage.waitUntilPageLoaded();
        launchesPage.sortByColumn("Skipped", "desc");
        Assert.assertTrue(launchesPage.isColumnSortingCorrect("Skipped","desc"), "Column is not sorted correct");
    }

    @Test
    public void iOpenEditLaunchModal(){
        logger.info("Edit Launch Modal test starts");
        dashboardsPage.clickOnIconInSideMenu("Launches");
        launchesPage.waitUntilPageLoaded();
        launchesPage.invokeEditLaunchModal("Demo Api Tests","3");
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Edit launch']")).isDisplayed());
    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
    }
}
