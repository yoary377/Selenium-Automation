package xm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import xm.utils.SeleniumUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StocksPage {
    protected WebDriver driver;

    @FindBy(how = How.XPATH, using = "//h1[contains(text(),'Stocks')]")
    private WebElement stocksHeader;

    @FindBy(how = How.XPATH, using = "//button[text()='Norway']")
    private WebElement norwayButton;

    @FindBy(how = How.XPATH, using = "//td[@data-xm-qa-name='symbolWithDescription' and text()='Orkla ASA (ORK.OL)']")
    private WebElement orklaRow;

    @FindBy(how = How.XPATH, using = "//tr[contains(., 'Orkla')]/td")
    private List<WebElement> tdElements;

    @FindBy(how = How.XPATH, using = "//input[@type='search']")
    private WebElement searchField;

    @FindBy(how = How.XPATH, using = "//td[@data-xm-qa-name='symbolWithDescription']")
    protected WebElement mobileUnfoldStocksButton;

    @FindBy(how = How.XPATH, using = "//span[@class='dtr-data']/a[normalize-space()='Read More']")
    private WebElement mobileReadMoreButton;

    public StocksPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Checks if the stocks header is displayed.
     *
     * @return true if visible, false otherwise.
     */
    public boolean isStocksHeaderDisplayed() {
        return SeleniumUtils.isElementVisible(driver, stocksHeader, SeleniumUtils.TIMEOUT);
    }

    /**
     * Clicks the Norway button.
     */
    public void clickNorwayButton() {
        try {
            SeleniumUtils.clickWhenClickable(driver, norwayButton, SeleniumUtils.TIMEOUT);
        } catch (Exception e) {
            System.err.println("Failed to click Norway button: " + e.getMessage());
        }
    }

    /**
     * Navigates to the Orkla page.
     *
     * @return a new instance of OrklaPage.
     */
    public OrklaPage navigateToOrklaPage() {
        try {
            if (SeleniumUtils.isM(driver)) {
                navigateForMobile();
            } else {
                clickOrklaReadMoreButton();
            }
        } catch (Exception e) {
            System.err.println("Navigation to Orkla page failed: " + e.getMessage());
        }
        return new OrklaPage(driver);
    }

    /**
     * Checks if Orkla row is displayed.
     *
     * @return true if visible, false otherwise.
     */
    public boolean isOrklaDisplayed() {
        return SeleniumUtils.isElementVisible(driver, orklaRow, SeleniumUtils.TIMEOUT);
    }

    /**
     * Retrieves and sorts TD elements between specified indices.
     *
     * @param start the starting index (inclusive).
     * @param end   the ending index (exclusive).
     * @return sorted list of text from TD elements.
     */
    public List<String> getSortedTdElements(int start, int end) {
        List<String> numbers = new ArrayList<>();
        try {
            List<WebElement> sublist = tdElements.subList(start, end);
            for (WebElement td : sublist) {
                numbers.add(td.getText());
            }
            Collections.sort(numbers);
        } catch (Exception e) {
            System.err.println("Failed to retrieve or sort TD elements: " + e.getMessage());
        }
        return numbers;
    }

    /**
     * Inputs text into the search field.
     *
     * @param text the text to input.
     */
    public void inputTextIntoSearchField(String text) {
        try {
            searchField.sendKeys(text);
        } catch (Exception e) {
            System.err.println("Failed to input text into search field: " + e.getMessage());
        }
    }

    private void clickOrklaReadMoreButton() {
        SeleniumUtils.clickWhenClickable(driver, tdElements.get(tdElements.size() - 1), SeleniumUtils.TIMEOUT);
    }

    private void navigateForMobile() {
        SeleniumUtils.scrollToElement(driver, mobileUnfoldStocksButton);
        SeleniumUtils.clickWhenClickable(driver, mobileUnfoldStocksButton, SeleniumUtils.TIMEOUT);
        SeleniumUtils.clickWhenClickable(driver, mobileReadMoreButton, SeleniumUtils.TIMEOUT);
    }
}