package project_tests.PomPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project_tests.utilities.TestBase;


public class Login {
    WebDriver driver;

    private static final Logger logger = LoggerFactory.getLogger(TestBase.class);

    //define locators
    //username entry element
    By username = By.cssSelector("#username");

    //password entry element
    By password = By.cssSelector("#password");

    By loginBtn = By.cssSelector("button.woocommerce-form-login__submit");


    //constructor
    public Login(WebDriver driver){
        this.driver = driver;
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
        driver.findElement(loginBtn).click();
    }

    public void Login (String user, String pass){
        enterUser(user);
        enterpass(pass);
        clickLogin();
        logger.debug("Logged in");
    }




}
