package xm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import xm.utils.SeleniumUtils;


public class MobileHomePage extends HomePage {

    @FindBy(how = How.XPATH, using = "//a[@href='#tradingMenu']")
    private WebElement mobileTradeButton;

    @FindBy(how = How.XPATH, using = "//button[@class='toggleLeftNav']")
    private WebElement mobileMenuButton;

    /**
     * Constructor that initializes the MobileHomePage with a WebDriver instance.
     * @param driver the WebDriver instance used for interacting with the web page.
     */
    public MobileHomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks the trading button on the mobile version of the home page.
     * Overrides the clickTrading method from HomePage to use the mobile-specific trading button.
     */
    @Override
    public void clickTrading() {
        try {
            SeleniumUtils.clickWhenClickable(driver, mobileTradeButton, SeleniumUtils.TIMEOUT);
        } catch (Exception e) {
            System.err.println("Failed to click on Mobile Trade Button: " + e.getMessage());
        }
    }

    /**
     * Opens the left navigation menu on the mobile version of the home page.
     */
    public void openLeftMenu() {
        try {
            SeleniumUtils.clickWhenClickable(driver, mobileMenuButton, SeleniumUtils.TIMEOUT);
        } catch (Exception e) {
            System.err.println("Failed to open left menu: " + e.getMessage());
        }
    }

    /**
     * Retrieves an instance of the MobileStocksPage.
     * Overrides the getStocksPage method from HomePage to return the mobile-specific StocksPage.
     * @return a new instance of MobileStocksPage.
     */
    @Override
    public MobileStocksPage getStocksPage() {
        return new MobileStocksPage(driver);
    }
}