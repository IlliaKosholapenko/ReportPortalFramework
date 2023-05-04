package business.pages;

import lombok.Getter;
import lombok.SneakyThrows;
import org.openqa.selenium.By;

import static tests.CommonTests.driver;
import static core.Config.logger;

@Getter
public class LoginPage extends BasePage {
    public static final String PAGE_IDENTIFIER = "Login Page";
    private final By USERNAME_INPUT = By.name("login");
    private final By PASSWORD_INPUT = By.name("password");
    private final By LOGIN_BUTTON = By.xpath("//button");
    private String pageUrl = "http://localhost:8080/ui/#login";

    @SneakyThrows
    public void loginWithCreds(String user, String password) {
        logger.info(String.format("Logging in with credentials: %s, %s", user, password));
        driver.findElement(USERNAME_INPUT).sendKeys(user);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }
}
