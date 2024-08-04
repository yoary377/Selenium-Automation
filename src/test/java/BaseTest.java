import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

class BaseTest {
    WebDriver driver;
    private static final String RESOLUTION_KEY = "resolution";

    @BeforeEach
    void setup() {
        // Setting up WebDriver
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        // Set browser window size based on system property
        String resolution = System.getProperty(RESOLUTION_KEY, "maximum");
        switch (resolution) {
            case "1024x768":
                driver.manage().window().setSize(new Dimension(1024, 768));
                break;
            case "800x600":
                driver.manage().window().setSize(new Dimension(800, 600));
                break;
            case "maximum":
            default:
                driver.manage().window().maximize();
                break;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
