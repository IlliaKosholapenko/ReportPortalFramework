package core.pages;

import core.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

import static business.CommonSteps.driver;
import static core.Config.logger;

public abstract class BasePage {

    public void openPage(String identifier) {
        logger.info("Trying to open " + identifier + " page");
        switch (identifier) {
            case LoginPage.PAGE_IDENTIFIER:
                driver.get(new LoginPage().getPageUrl());
            case LaunchesPage.PAGE_IDENTIFIER:
                driver.get(new LaunchesPage().getPageUrl());
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
                .until(ExpectedConditions.urlToBe(String.format(this.getPageUrl(), Config.properties.getProperty("projectname"))));
    }

    protected abstract String getPageUrl();
}
