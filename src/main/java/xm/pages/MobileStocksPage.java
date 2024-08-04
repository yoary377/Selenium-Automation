package xm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import xm.utils.SeleniumUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MobileStocksPage extends StocksPage {

    @FindBy(how = How.XPATH, using = "//span[@class='dtr-data']/a[normalize-space()='Read More']")
    private WebElement mobileReadMoreButton;

    @FindBy(how = How.XPATH, using = "//span[@class='dtr-data']")
    private List<WebElement> mobileTdElements;

    /**
     * Constructs a MobileStocksPage with the specified WebDriver.
     * @param driver the WebDriver used for interacting with the web page.
     */
    public MobileStocksPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Unfolds additional stock information on the mobile version of the stocks page.
     */
    public void unfoldStocks() {
        try {
            SeleniumUtils.scrollToElement(driver, mobileUnfoldStocksButton);
            SeleniumUtils.clickWhenClickable(driver, mobileUnfoldStocksButton, SeleniumUtils.TIMEOUT);
        } catch (Exception e) {
            System.err.println("Failed to unfold stocks: " + e.getMessage());
        }
    }

    /**
     * Navigates to the Orkla page by clicking the 'Read More' button.
     * @return a new instance of OrklaPage.
     */
    public OrklaPage navigateToOrklaPage() {
        try {
            SeleniumUtils.clickWhenClickable(driver, mobileReadMoreButton, SeleniumUtils.TIMEOUT);
        } catch (Exception e) {
            System.err.println("Failed to navigate to Orkla page: " + e.getMessage());
        }
        return new OrklaPage(driver);
    }

    /**
     * Retrieves and sorts TD elements from both the inherited and mobile-specific lists.
     * @return a sorted list of text from TD elements.
     */
    public List<String> getTdElements() {
        List<String> numbers = new ArrayList<>(super.getSortedTdElements(2, 6));
        try {
            List<WebElement> sublist = mobileTdElements.subList(0, 2);
            for (WebElement td : sublist) {
                numbers.add(td.getText());
            }
            Collections.sort(numbers);
        } catch (Exception e) {
            System.err.println("Failed to retrieve or sort TD elements: " + e.getMessage());
        }
        return numbers;
    }
}