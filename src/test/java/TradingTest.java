import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import xm.pages.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TradingTest extends BaseTest {
    static boolean enabledForDesktop() {
        String resolution = System.getProperty("resolution", "maximum");
        return !resolution.equals("800x600");
    }

    static boolean enabledForMobile() {
        String resolution = System.getProperty("resolution", "maximum");
        return resolution.equals("800x600");
    }
    @Tag("desktop")
    @Test
    @EnabledIf("enabledForDesktop")
    public void tradingTest() {
        HomePage homePage = new HomePage(driver);
        homePage.getToBaseURL();
        homePage.acceptCookies();
        homePage.clickTrading();

        assertThat(homePage.stocksButtonIsVisible())
                .as("Check if trading heading is visible")
                .isTrue();

        homePage.navigateToStocks();
        StocksPage stocksPage = homePage.getStocksPage();
        assertThat(stocksPage.isStocksHeaderDisplayed())
                .as("Check if stocks header is displayed")
                .isTrue();

        stocksPage.inputTextIntoSearchField("Orkla");
        stocksPage.clickNorwayButton();

        assertThat(stocksPage.isOrklaDisplayed())
                .as("Check if Orkla stock is displayed after filtering")
                .isTrue();

        List<String> stocks = new ArrayList<>();
        stocks = stocksPage.getSortedTdElements(2,8);

        OrklaPage orklaPage = stocksPage.navigateToOrklaPage();
        assertThat(orklaPage.orklaHeaderIsDisplayed())
                .as("Check if Orkla page header is displayed")
                .isTrue();

        List<String> orklaStocks = orklaPage.getTdElements();
        assertThat(stocks)
                .as("Verify that stock details match between the Stocks page and Orkla page")
                .isEqualTo(orklaStocks);
    }

    @Tag("mobile")
    @Test
    @EnabledIf("enabledForMobile")
    public void mobileTradingTest() {
        MobileHomePage mobileHomePage = new MobileHomePage(driver);
        mobileHomePage.getToBaseURL();
        mobileHomePage.acceptCookies();
        mobileHomePage.openLeftMenu();
        mobileHomePage.clickTrading();

        assertThat(mobileHomePage.stocksButtonIsVisible())
                .as("Check if trading heading is visible")
                .isTrue();

        mobileHomePage.navigateToStocks();
        MobileStocksPage mobileStocksPage = mobileHomePage.getStocksPage();
        assertThat(mobileStocksPage.isStocksHeaderDisplayed())
                .as("Check if stocks header is displayed")
                .isTrue();

        mobileStocksPage.inputTextIntoSearchField("Orkla");
        mobileStocksPage.clickNorwayButton();

        assertThat(mobileStocksPage.isOrklaDisplayed())
                .as("Check if Orkla stock is displayed after filtering")
                .isTrue();

        mobileStocksPage.unfoldStocks();
        List<String> stocks = mobileStocksPage.getTdElements();

        OrklaPage orklaPage = mobileStocksPage.navigateToOrklaPage();
        assertThat(orklaPage.orklaHeaderIsDisplayed())
                .as("Check if Orkla page header is displayed")
                .isTrue();

        List<String> orklaStocks = orklaPage.getTdElements();
        assertThat(stocks)
                .as("Verify that stock details match between the Stocks page and Orkla page")
                .isEqualTo(orklaStocks);
    }
}
