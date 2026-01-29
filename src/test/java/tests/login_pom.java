package tests;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import stepsforHooks.Hook;

public class login_pom {

    WebDriver driver;

    @Given("user is on login page")
    public void userIsOnLoginPage() {
        driver = Hook.driver; // ✅ Hook already executed
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @When("^user enters (.*) and (.*)$")
    public void userEntersUsernameAndPassword(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("click on logic button")
    public void clickOnLogicButton() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.click();
    }

    @Then("user is navigated to home page")
    public void userIsNavigatedToHomePage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.flg_afterLoginCheck();
    }
}
