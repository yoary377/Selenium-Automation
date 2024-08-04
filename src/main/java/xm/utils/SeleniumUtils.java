package xm.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumUtils {

    private static final int M_WIDTH_THRESHOLD = 1040;
    public static final int TIMEOUT = 10;

    public static boolean isElementVisible(WebDriver driver, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static void clickWhenClickable(WebDriver driver, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        clickableElement.click();
    }

    public static boolean isM(WebDriver driver) {
        return driver.manage().window().getSize().width <= M_WIDTH_THRESHOLD;
    }

    public static void scrollToElement(WebDriver driver,WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'start'});", element);
    }
}
