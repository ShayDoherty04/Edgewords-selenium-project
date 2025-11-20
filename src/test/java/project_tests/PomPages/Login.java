package project_tests.PomPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
//import project_tests.utilities.TestBase;


public class Login {
    WebDriver driver;
    WebDriverWait wait;

    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    //define locators
    //username entry element
    By username = By.cssSelector("#username");

    //password entry element
    By password = By.cssSelector("#password");

    By loginBtn = By.cssSelector("button.woocommerce-form-login__submit");

    //dismiss btn locator
    By dismissLink = By.linkText("Dismiss");

    //constructor
    public Login(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //page actions
    //enter username
    public void enterUser (String user){
        driver.findElement(username).sendKeys(user);
    }
    //enter password
    public void enterpass (String pass){
        driver.findElement(password).sendKeys(pass);
    }

    //click login
    //enter password
    public void clickLogin (){
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    public void Login (String user, String pass){
        enterUser(user);
        enterpass(pass);
        wait.until(ExpectedConditions.elementToBeClickable(dismissLink)).click();
        clickLogin();
        logger.debug("Logged in");
    }




}
