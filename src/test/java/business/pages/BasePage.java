package business.pages;

import core.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

import static tests.CommonTests.driver;
import static core.Config.logger;

public abstract class BasePage {

    public void openPage(String identifier) {
        logger.info("Trying to open " + identifier + " page");
        switch (identifier) {
            case LoginPage.PAGE_IDENTIFIER:
                driver.get(new LoginPage().getPageUrl());
                break;
            case LaunchesPage.PAGE_IDENTIFIER:
                driver.get(new LaunchesPage().getPageUrl());
                break;
        }
    }

    public void clickOnIconInSideMenu(String icon){
       driver.findElement(By.xpath(String.format("//div//a[contains(@href,'%s')]/ancestor::div[contains(@class,'sidebar-btn')]",icon.toLowerCase(Locale.ROOT)))).click();
    }

    public void waitUntilElementIsVisible(By element){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
    }

    public void waitUntilPageLoaded(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(String.format(this.getPageUrl(), Config.properties.getProperty("projectname"))));
    }

    protected abstract String getPageUrl();
}
