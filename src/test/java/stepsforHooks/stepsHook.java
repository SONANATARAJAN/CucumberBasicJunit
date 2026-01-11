package stepsforHooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
                import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class stepsHook {

 WebDriver driver;
    @Given("Open Login Page")
    public void openLoginPage() {

    }

    @When("Enter Username and password")
    public void enterUsernameAndPassword() {


    }

    @And("click enter")
    public void clickEnter() {

    }

    @Then("Opened Application")
    public void openedApplication() {
    }



    @Before
    public void browserSetup(){

        System.out.println("Hi !!!!!!!!!!");
      /*  driver = new ChromeDriver();
        driver.manage().window().maximize();*/
     }
    @After
    public void browserShutDown(){
        System.out.println("Bye!!!!!!!!!!!!!!!!");
//        driver.close();
    }
}
