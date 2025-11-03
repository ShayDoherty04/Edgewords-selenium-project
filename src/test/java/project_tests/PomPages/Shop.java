package project_tests.PomPages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import project_tests.utilities.TestBase;
import project_tests.utilities.UtilMethods;


public class Shop {
    WebDriver driver;
    WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(Shop.class);


    //define locators
    //add beanie to cart
    public By beanie = By.cssSelector("li.product a[aria-label='Add “Cap” to your cart']");

    //add belt to cart
    public By belt = By.cssSelector("li.product a[aria-label='Add “Belt” to your cart']");

    //add cap to cart
    public By cap = By.cssSelector("li.product a[aria-label='Add “Beanie” to your cart']");


    //dismiss button
    By dismiss = By.linkText("Dismiss");

    //view cart
    By cart = By.linkText("Cart");

    //count span
    By cartCount = By.cssSelector(".count");




    //constructor
    public Shop (WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
    }




    //add items in more scaleable way
    public void addItem(By item){

        WebElement clickableItem = wait.until(ExpectedConditions.presenceOfElementLocated(item));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickableItem);

        UtilMethods util = new UtilMethods();
        int currentCount = util.counter(driver);
        currentCount++;

        String expectedText = currentCount + (currentCount < 2 ? " item" : " items");

        wait.until(ExpectedConditions.textToBePresentInElementLocated(cartCount, expectedText));

    }

    //dismiss link
    public void dissmissLink(){
        try {
            WebElement dismissLink = wait.until(ExpectedConditions.presenceOfElementLocated(dismiss));

            if (dismissLink.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dismissLink);
                wait.until(ExpectedConditions.invisibilityOf(dismissLink));
            }
        } catch (TimeoutException | NoSuchElementException e) {

        }
    }

    //go to cart
    public void goToCart(){
        driver.findElement(cart).click();
    }


    //method to call any page action passed to it
    public void Shop(Runnable... products){
//        dissmissLink();
        for (Runnable product : products) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            //dissmissLink();
            product.run();
        }
        logger.debug("Added items to cart");
        goToCart();
    }

}
