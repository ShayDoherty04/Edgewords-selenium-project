package project_tests.PomPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import project_tests.utilities.*;

import java.time.Duration;

public class Checkout {
    WebDriver driver;
    WebDriverWait wait;

    By firstName = By.cssSelector("#billing_first_name");

    By secondName = By.cssSelector("#billing_last_name");

    By street = By.cssSelector("#billing_address_1");

    By city = By.cssSelector("#billing_city");

    By postCode = By.cssSelector("#billing_postcode");

    By phone = By.cssSelector("#billing_phone");

    By email = By.cssSelector("#billing_email");

    By clearCart = By.cssSelector("td.product-remove > a");

    By account = By.cssSelector("#menu-item-46 > a");

    By countryDropDown = By.cssSelector("#select2-billing_country-container");
    By UK = By.xpath("//li[contains(@class, 'select2-results__option') and text()='United Kingdom (UK)']");

    By placeOrder = By.cssSelector("#place_order");

    //constructor
    public Checkout(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
    }

    //page actions
    //clear first name
    public void clearFirstName(){
        driver.findElement(firstName).clear();
    }

    //clear second name
    public void clearSecondName(){
        driver.findElement(secondName).clear();
    }

    //clear street
    public void clearStreet(){
        driver.findElement(street).clear();
    }

    //clear city
    public void clearCity(){
        driver.findElement(city).clear();
    }

    //clear postcode
    public void clearPostCode(){
        driver.findElement(postCode).clear();
    }

    //clear phone
    public void clearPhone(){
        driver.findElement(phone).clear();
    }

    //clear email
    public void clearEmail(){
        driver.findElement(email).clear();
    }

    //go to account
    public void goToAccount(){
        driver.findElement(account).click();
    }

    //page actions for filling in checkout details
    //enter first name
    public void enterFirstName(){
        String name = EnvVariables.get("FIRST_NAME");
        driver.findElement(firstName).sendKeys(name);
    }

    //enter second name
    public void enterSecondName(){
        String name = EnvVariables.get("SECOND_NAME");
        driver.findElement(secondName).sendKeys(name);
    }

    //enter country
    public void enterCountry(){
        driver.findElement(countryDropDown).click();
        driver.findElement(UK).click();

    }

    //enter street
    public void enterStreet (){
        String streetName = EnvVariables.get("STREET");
        driver.findElement(street).sendKeys(streetName);
    }

    //enter city
    public void enterCity(){
        driver.findElement(city).sendKeys("Manchester");
    }

    //enter postcode
    public void enterPostCode(){
        String code = EnvVariables.get("POST_CODE");
        driver.findElement(postCode).sendKeys(code);
    }

    //enter phone number
    public void enterPhoneNumber(){
        String number = EnvVariables.get("PHONE");
        driver.findElement(phone).sendKeys(number);
    }

    //enter email
    public void enterEmail(){
        String emailString = EnvVariables.get("EMAIL");
        driver.findElement(email).sendKeys(emailString);
    }

    //place order button
    public void clickPlaceOrder(){
        WebElement placeOrderButton = driver.findElement(placeOrder);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrderButton);
    }


    public void enterCheckoutInfo(){
        enterFirstName();
        enterSecondName();
        enterCountry();
        enterStreet();
        enterCity();
        enterPostCode();
        enterPhoneNumber();
        enterEmail();
        clickPlaceOrder();
    }


    public void cleanCheckout(){
        clearFirstName();
        clearSecondName();
        clearStreet();
        clearCity();
        clearPostCode();
        clearPhone();
        clearEmail();
        //clickPlaceOrder();
    }
    public void waitForCheckoutFields() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(firstName));
        wait.until(ExpectedConditions.elementToBeClickable(secondName));
        wait.until(ExpectedConditions.elementToBeClickable(street));
        wait.until(ExpectedConditions.elementToBeClickable(city));
        wait.until(ExpectedConditions.elementToBeClickable(city));
        wait.until(ExpectedConditions.elementToBeClickable(postCode));
        wait.until(ExpectedConditions.elementToBeClickable(phone));
        wait.until(ExpectedConditions.elementToBeClickable(email));
    }


    public void goToAccPage(){
        goToAccount();
    }
}
