/*
package tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MyStepdefs {
    public WebDriver driver ;

    @Given("user is on login page")
    public void userIsOnLoginPage() throws InterruptedException {
        driver=new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();
        Thread.sleep(4000);
     }
    @When("^user enters (.*) and (.*)$")
    public void userEntersUsernameAndPassword(String usename , String password) throws InterruptedException {
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(usename);
        Thread.sleep(4000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(password);
        Thread.sleep(4000);
        System.out.println("User on Hello World");

    }
    @And("click on logic button")
    public void clickOnLogicButton() throws InterruptedException {
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));

         WebElement element = driver.findElement(By.id("submit"));
       element.click();
        System.out.println("User on Hello World");
        Thread.sleep(2000);
    }


    @Then("user is navigated to home page")
    public void userIsNavigatedToHomePage() {
   Boolean flag=driver.findElement(By.xpath("//a[text()='Log out']")).isDisplayed();
        System.out.println(flag);
     }

}
*/
