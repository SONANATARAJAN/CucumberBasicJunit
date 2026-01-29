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
        options.addArguments("--headless=new");           // Chrome 109+ uses new headless mode
        options.addArguments("--no-sandbox");             // Required in Linux
        options.addArguments("--disable-dev-shm-usage");  // Avoid /dev/shm crash
        options.addArguments("--disable-gpu");           // Optional
        options.addArguments("--window-size=1920,1080"); // Required for full rendering
        options.addArguments("--remote-allow-origins=*"); // Solve ChromeDriver CORS issue 4.27+

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
