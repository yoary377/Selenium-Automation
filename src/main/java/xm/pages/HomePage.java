package xm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import xm.utils.SeleniumUtils;


public class HomePage {
    public static final String BASE_URL = "https://xm.com";

    protected WebDriver driver;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Trading')]")
    private WebElement tradeButton;

    @FindBy(how = How.XPATH, using = "//*[text()='ACCEPT ALL']")
    private WebElement acceptAllButton;

    @FindBy(how = How.XPATH, using = "//span[text()='Trading']")
    private WebElement tradingHeading;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Stocks")
    private WebElement stocksButton;

    /**
     * Constructs a HomePage with the specified WebDriver.
     * @param driver the WebDriver used to interact with the web page.
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigates to the base URL of the website.
     */
    public void getToBaseURL() {
        try {
            driver.get(BASE_URL);
        } catch (Exception e) {
            System.out.println("Error navigating to the base URL: " + e.getMessage());
        }
    }

    /**
     * Clicks the 'Trading' button on the home page.
     */
    public void clickTrading() {
        try {
            SeleniumUtils.clickWhenClickable(driver, tradeButton, SeleniumUtils.TIMEOUT);
        } catch (Exception e) {
            System.out.println("Error clicking on Trading button: " + e.getMessage());
        }
    }

    /**
     * Navigates to the Stocks page by clicking the 'Stocks' button.
     */
    public void navigateToStocks() {
        try {
            stocksButton.click();
        } catch (Exception e) {
            System.out.println("Error clicking on Stocks button: " + e.getMessage());
        }
    }

    /**
     * Retrieves an instance of the StocksPage.
     * @return a new instance of StocksPage initialized with the current WebDriver.
     */
    public StocksPage getStocksPage() {
        return new StocksPage(driver);
    }

    /**
     * Clicks the 'ACCEPT ALL' button to accept all cookies.
     */
    public void acceptCookies() {
        try {
            SeleniumUtils.clickWhenClickable(driver, acceptAllButton, SeleniumUtils.TIMEOUT);
        } catch (Exception e) {
            System.out.println("Error clicking on Accept All button: " + e.getMessage());
        }
    }

    /**
     * Checks if the 'Stocks' button is visible on the page.
     * @return true if the 'Stocks' button is visible, false otherwise.
     */
    public boolean stocksButtonIsVisible() {
        return SeleniumUtils.isElementVisible(driver, stocksButton, SeleniumUtils.TIMEOUT);
    }
}