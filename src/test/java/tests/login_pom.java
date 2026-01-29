package tests;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import stepsforHooks.Hook;

public class login_pom {

    WebDriver driver;

    // Locators
    By username = By.id("username");
    By password = By.id("password");
    By submit   = By.id("submit");
    By logout   = By.xpath("//a[text()='Log out']");

    @Given("user is on login page")
    public void userIsOnLoginPage() {
        driver = Hook.driver;   // ✅ Get driver from Hook
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @When("^user enters (.*) and (.*)$")
    public void userEntersUsernameAndPassword(String user, String pass) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
    }

    @And("click on logic button")
    public void clickOnLogicButton() {
        driver.findElement(submit).click();
    }

    @Then("user is navigated to home page")
    public void userIsNavigatedToHomePage() {
        boolean isLoggedIn = driver.findElement(logout).isDisplayed();
        System.out.println("Login success = " + isLoggedIn);
    }
}
