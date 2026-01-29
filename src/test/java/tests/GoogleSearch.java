//package tests;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class GoogleSearch {
//    public WebDriver driver ;
//
//    @Given("browser is open")
//    public void browser_is_open() {
//        driver = new ChromeDriver();
//        driver.get("https://www.google.com/");
//      }
//
//    @When("user Click search Feild")
//    public void user_click_search_feild() {
//    driver.findElement(By.xpath("//textarea[@id='APjFqb']")).sendKeys("Automation step by step");
//
//
//     }
//
//    @And("hit enter")
//    public void hit_enter() {
//        // Write code here that turns the phrase above into concrete actions
//        driver.findElement(By.xpath("//textarea[@id='APjFqb']")).sendKeys(Keys.ENTER);
//    }
//
//    @Then("user is navigate to search result")
//    public void user_is_navigate_to_search_result() {
//        driver.getPageSource().contains("designed every topic to start from scratch and take you up Step-by-Step.");
//        System.out.println("enter sucessfully");
//    }}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
