package stepsforHooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Hook {
    WebDriver driver;

    @Before
    public void browserSetup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
       // driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }
    @After
    public void browserShutDown(){
  driver.close();
    }
}
