package stepsforHooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Hook {

    public static WebDriver driver;

    @Before
    public void browserSetup() throws Exception {

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--headless=new",
                "--no-sandbox",
                "--disable-dev-shm-usage"
        );

        // 🔥 IMPORTANT:
        // Use container name, NOT localhost
        driver = new RemoteWebDriver(
                new URL("http://selenium:4444/wd/hub"),
                options
        );

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @After
    public void browserShutDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
