package core.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Locale;

import static business.CommonSteps.driver;
import static core.Config.logger;

@Getter
public class LaunchesPage extends BasePage {
    public static final String PAGE_IDENTIFIER = "Launches";
    private static String TABLE_ROW = "//td//div//span[text()='%s']/ancestor::span/preceding-sibling::a//span[text()='%s']//ancestor::td";
    private static String TABLE_COLUMN_VALUE = "div[contains(@class,'%s-col')]//a";
    private String pageUrl = "http://localhost:8080/ui/#%s/launches/all";

    public void isDataPresentInRow(String rowName, String rowNumber, List<String> columns) {
        SoftAssert softAssert = new SoftAssert();
        columns.forEach(column -> {
            if (!column.contains(" ")) {
                logger.info("Trying get value of column " + column);
                By element = By.xpath(String.format(TABLE_ROW, rowNumber, rowName) + "//following-sibling::" + String.format(TABLE_COLUMN_VALUE, column.toLowerCase(Locale.ROOT)));
                softAssert.assertFalse(driver.findElement(element).getText() == null);
            } else {
                logger.info("Ignoring column " + column);
            }
            softAssert.assertAll();
        });
    }
}
