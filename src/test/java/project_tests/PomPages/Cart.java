package project_tests.PomPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import project_tests.utilities.*;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Cart {
    WebDriver driver;
    WebDriverWait wait;

    private static final Logger logger = LoggerFactory.getLogger(TestBase.class);
    //locators
    //coupon input
    By couponInput = By.cssSelector("#coupon_code");

    //coupon apply
    By apply = By.cssSelector("button[name='apply_coupon']");

    //total cost display
    By total = By.xpath("//th[text()='Total']");

    // coupon applied text box
    By couponApplied = By.xpath("//th[contains(text(),'Coupon:')]");

    //remove code
    By removeCoupon = By.cssSelector("a.woocommerce-remove-coupon");

    //checkout button
    By proceedCheckout = By.cssSelector("a.checkout-button");

    //dismiss link
    By dismissLink = By.linkText("Dismiss");

    //remove item button
    By removeItem = By.cssSelector("td.product-remove > a");

    //constructor
    public Cart(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
    }

    //page actions

    //enter input
    public void inputCode(){
        String discount1 = EnvVariables.get("DISCOUNT1");
        String discount2 = EnvVariables.get("DISCOUNT2");
        wait.until(ExpectedConditions.elementToBeClickable(couponInput)).sendKeys(discount1);
    }

    //click apply
    public void clickApplyCode(){
        wait.until(ExpectedConditions.elementToBeClickable(apply)).click();
    }

    //apply
    public void applyCode (){
        String text = driver.findElement(total).getText();
        inputCode();
        clickApplyCode();
        wait.until(ExpectedConditions.visibilityOfElementLocated(couponApplied));
        logger.debug("coupon applied");

    }

    //remove coupon code
    public void removeCode(){
        List<WebElement> removeCouponElements = driver.findElements(removeCoupon);
        if (!removeCouponElements.isEmpty()) {

            WebElement code = removeCouponElements.get(0);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", code);
        }
    }

    public void goToCheckout(){
        wait.until(ExpectedConditions.elementToBeClickable(dismissLink)).click();
        WebElement goToCheckout = wait.until(ExpectedConditions.presenceOfElementLocated(proceedCheckout));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", goToCheckout);
    }
    public void clearCart(){
        List<WebElement> removeButtons = driver.findElements(removeItem);
        driver.findElement(dismissLink).click();
        while (!removeButtons.isEmpty()){
            WebElement removeButton = removeButtons.get(0);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", removeButton);


            wait.until(ExpectedConditions.invisibilityOf(removeButtons.get(0)));

            removeButtons = driver.findElements(By.cssSelector("td.product-remove > a"));
        }

    }


}
