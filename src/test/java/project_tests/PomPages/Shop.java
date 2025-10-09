package project_tests.PomPages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project_tests.utilities.TestBase;


public class Shop {
    WebDriver driver;
    WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(TestBase.class);


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

    //counter
    //counter needed to wait for correct text in span element so i know product is added to cart
    public  int counter() {
        //store span element in string variable
        String countText = driver.findElement(cartCount).getText();
        //split into [0, "item"] store 0
        int currentCount = Integer.parseInt(countText.split(" ")[0]);
        return currentCount;
    }

    //page actions
//    //add beanie
//    public void addBeanie(){
//        //driver.findElement(beanie).click();
//        wait.until(ExpectedConditions.elementToBeClickable(beanie)).click();
//        //add one to counter after item added
//        int currentCount = counter();
//        currentCount++;
//        //conditional if current count singular item plural items
//        String expectedText = currentCount + (currentCount < 2 ? " item" : " items");
//        //wait until span recognises item added to cart
//        wait.until(ExpectedConditions.textToBePresentInElementLocated(cartCount, expectedText));
//
//
//    }
//
//    //add belt
//    public void addBelt(){
//        //driver.findElement(belt).click();
//        wait.until(ExpectedConditions.elementToBeClickable(belt)).click();
//        //add one to counter after item added
//        int currentCount = counter();
//        currentCount++;
//        //conditional if current count singular item plural items
//        String expectedText = currentCount + (currentCount < 2 ? " item" : " items");
//        //wait until span recognises item added to cart
//        wait.until(ExpectedConditions.textToBePresentInElementLocated(cartCount, expectedText));
//    }
//
//    //add Cap
//    public void addCap(){
//        //driver.findElement(cap).click();
//        wait.until(ExpectedConditions.elementToBeClickable(cap)).click();
//        //add one to counter after item added
//        int currentCount = counter();
//        currentCount++;
//        //conditional if current count singular item plural items
//        String expectedText = currentCount + (currentCount < 2 ? " item" : " items");
//        //wait until span recognises item added to cart
//        wait.until(ExpectedConditions.textToBePresentInElementLocated(cartCount, expectedText));
//    }
    //add items in more scaleable way
    public void addItem(By item){

        WebElement clickableItem = wait.until(ExpectedConditions.presenceOfElementLocated(item));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickableItem);

        int currentCount = counter();
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
            //dismiss link not present safe to proceed
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
