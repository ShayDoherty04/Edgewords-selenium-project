package project_tests.PomPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogOut {
    WebDriver driver;
    WebDriverWait wait;

    //locators

    By removeCouponBtn = By.cssSelector("a.woocommerce-remove-coupon");

    By accountPage = By.cssSelector("#menu-item-46 > a");

    By dismiss = By.cssSelector("h1.entry-title");

    By logoutText = By.linkText("Log out");


    // constructor

    public LogOut(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(7));
    }

    //page actions

    //remove coupon
//    public void removeCoupon(){
//        WebElement removeCoupon = wait.until(ExpectedConditions.visibilityOfElementLocated(removeCouponBtn));
//
//        // Scroll it into view
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", removeCoupon);
//
//        // Wait until clickable
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", removeCoupon);
//    }

    public void clickAccountPage (){
        // Click on the account page link
        WebElement accPage = wait.until(ExpectedConditions.visibilityOfElementLocated(accountPage));

        // Scroll to it
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", accPage);



        // Use JS to click
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", accPage);

    }

    public void clickLogOut(){
        //wait.until(ExpectedConditions.textToBePresentInElementLocated(dismiss, "My account"));


        // Wait for the logout link to be present and clickable

        wait.until(ExpectedConditions.presenceOfElementLocated(logoutText));
        //wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("h1.entry-title"), 0));
        wait.until(ExpectedConditions.elementToBeClickable(logoutText));

        //find logout element
        WebElement logout = driver.findElement(logoutText);

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logout);

        // Click the logout element
        logout.click();
    }

    public void LogOut(){
        //removeCoupon();
        clickAccountPage();
        clickLogOut();


    }


}
