package tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

public class login_pom {
    public WebDriver driver ;

    @Given("user is on login page")
    public void userIsOnLoginPage() throws InterruptedException {
        driver=new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();
        Thread.sleep(4000);
     }
    @When("^user enters (.*) and (.*)$")
    public void userEntersUsernameAndPassword(String username , String password) throws InterruptedException {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

    }
    @And("click on logic button")
    public void clickOnLogicButton() throws InterruptedException {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.click();
    }


    @Then("user is navigated to home page")
    public void userIsNavigatedToHomePage() {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.flg_afterLoginCheck();
     }

}
