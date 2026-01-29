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
options.addArguments("--headless=new");
options.addArguments("--no-sandbox");
options.addArguments("--disable-dev-shm-usage"); // uses /tmp instead of /dev/shm
options.addArguments("--disable-gpu");
options.addArguments("--disable-software-rasterizer");
options.addArguments("--disable-extensions");
options.addArguments("--remote-allow-origins=*");
options.addArguments("--window-size=1920,1080");


        // Create driver
        driver = new ChromeDriver(options);

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
