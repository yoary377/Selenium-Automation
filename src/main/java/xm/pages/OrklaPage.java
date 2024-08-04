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

public class OrklaPage {
    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//h2[text()='Orkla ASA (ORK.OL) Trading with XM']")
    private WebElement orklaHeader;

    @FindBy(how = How.XPATH, using = "//table[@class='table pull-right']//td[contains(@data-xm-qa-name, '__value')]")
    private List<WebElement> tdElements;

    @FindBy(how = How.XPATH, using = "//td[@data-xm-qa-name='limit_and_stop_levels__title' and @class='ltr']")
    private WebElement limitAndStop;

    @FindBy(how = How.XPATH, using = "//td[@data-xm-qa-name='margin_requirement__value']")
    private WebElement marginRequirement;

    /**
     * Constructs an OrklaPage with the specified WebDriver.
     *
     * @param driver the WebDriver used for interacting with the web page.
     */
    public OrklaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Checks if the Orkla header is displayed on the page.
     *
     * @return true if the Orkla header is visible, false otherwise.
     */
    public boolean orklaHeaderIsDisplayed() {
        return SeleniumUtils.isElementVisible(driver, orklaHeader, SeleniumUtils.TIMEOUT);
    }

    /**
     * Retrieves and sorts text from TD elements on the Orkla page.
     *
     * @return a sorted list of text from TD elements.
     */
    public List<String> getTdElements() {
        List<String> numbers = new ArrayList<>();
        try {
            for (WebElement td : tdElements) {
                numbers.add(td.getText());
            }
            numbers.add(marginRequirement.getText());
            numbers.add(limitAndStop.getText());
            Collections.sort(numbers);
        } catch (Exception e) {
            System.err.println("Failed to retrieve or sort TD elements: " + e.getMessage());
        }
        return numbers;
    }
}