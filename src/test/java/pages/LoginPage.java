package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    public WebDriver driver ;

    public LoginPage(WebDriver driver){
        this.driver=driver;
    }
    By username = By.id("username");
    By password = By.id("password");
    By submit = By.id("submit");
    By visible = By.xpath("//a[text()='Log out']");


    public void enterUsername(String usrname){
        driver.findElement(username).sendKeys(usrname);
    }
    public void enterPassword(String pasword){
        driver.findElement(password).sendKeys(pasword);
    }
    public void click(){
        driver.findElement(submit).click();
    }
    public void flg_afterLoginCheck(){
       Boolean flag= driver.findElement(visible).isDisplayed();
        System.out.println(flag);
    }
}
