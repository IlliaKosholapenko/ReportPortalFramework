package business.pages;

import com.google.common.collect.Ordering;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static core.Config.logger;
import static tests.CommonTests.driver;

@Getter
public class LaunchesPage extends BasePage {
    public static final String PAGE_IDENTIFIER = "Launches";
    private static String TABLE_HEADER = "//span[text()='%s']/ancestor::div[contains(@class,'sortable')]";
    private static String TABLE_ROW = "//td//div//span[text()='%s']/ancestor::span/preceding-sibling::a//span[text()='%s']//ancestor::td";
    private static String TABLE_COLUMN_VALUE = "div[contains(@class,'%s-col')]//a";
    private String EDIT_LAUNCH_ICON = "//span[contains(@class,'edit-icon')]";
    private String pageUrl = "http://localhost:8080/ui/#%s/launches/all";

    public void isDataPresentInRow(String rowName, String rowNumber, List<String> columns) {
        SoftAssert softAssert = new SoftAssert();
        columns.forEach(column -> {
            logger.info("Trying get value of column " + column);
            if (!column.contains(" ")) {
                By element = By.xpath(String.format(TABLE_ROW, rowNumber, rowName) + "//following-sibling::" + String.format(TABLE_COLUMN_VALUE, column.toLowerCase(Locale.ROOT)));
                softAssert.assertFalse(driver.findElement(element).getText() == null, "Column " + column + " is empty");
            } else {
                String columnInitials = Arrays.stream(column.toLowerCase(Locale.ROOT).split(" ")).map(word -> word.substring(0, 1)).collect(Collectors.joining());
                By element = By.xpath(String.format(TABLE_ROW, rowNumber, rowName) + "//following-sibling::" + String.format(TABLE_COLUMN_VALUE, columnInitials));
                softAssert.assertFalse(driver.findElement(element).getText() == null, "Column " + column + " is empty");
            }
            softAssert.assertAll();
        });
    }

    public void sortByColumn(String column, String order) {
        driver.findElement(By.xpath(String.format(TABLE_HEADER, column.toLowerCase()))).click();
        this.waitUntilPageLoaded();
        logger.info(String.format("Sorted by %s column in ASC order", column));
        if (order.equalsIgnoreCase("desc")) {
            driver.findElement(By.xpath(String.format(TABLE_HEADER, column.toLowerCase()))).click();
            this.waitUntilPageLoaded();
            logger.info(String.format("Sorted by %s column in DESC order", column));
        }
    }

    public boolean isColumnSortingCorrect(String column, String order) {
        By element = By.xpath("//" + String.format(TABLE_COLUMN_VALUE, column.toLowerCase()));
        List sortedValues = driver.findElements(element).stream().map(item -> Integer.parseInt(item.getText().trim())).collect(Collectors.toList());
        return order.equalsIgnoreCase("asc") ? Ordering.<Integer>natural().isOrdered(sortedValues) : Ordering.<Integer>natural().reverse().isOrdered(sortedValues);
    }

    public void invokeEditLaunchModal(String rowName, String rowNumber) {
        Actions actions = new Actions(driver);
        String rowXpath = String.format(TABLE_ROW, rowNumber, rowName);
        actions.moveToElement(driver.findElement(By.xpath(rowXpath))).perform();
        driver.findElement(By.xpath(rowXpath + EDIT_LAUNCH_ICON)).click();
    }
}
