package stepsforHooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hook {

    public static WebDriver driver; // Make static if used in other classes

    @Before
    public void browserSetup() {
        // ChromeOptions for Linux headless
ChromeOptions options = new ChromeOptions();
options.addArguments(
    "--headless=new",
    "--no-sandbox",
    "--disable-dev-shm-usage"
);

WebDriver driver = new RemoteWebDriver(
    new URL("http://localhost:4444/wd/hub"),
    options
);


        // Optional timeouts
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @After
    public void browserShutDown() {
        if (driver != null) {
            driver.quit(); // Close all windows
        }
    }
}
