package project_tests.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.Duration;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project_tests.PomPages.*;
import project_tests.steps.CheckPriceStep;

import static java.math.RoundingMode.DOWN;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilMethods {

    private static final Logger logger = LoggerFactory.getLogger(UtilMethods.class);
    private WebDriver driver;
    private Login login;
    private Shop shop;
    private Checkout checkout;
    private WebDriverWait wait;

    //constructor

    public UtilMethods (WebDriver driver){
        this.driver = driver;
        this.login = new Login(driver);
        this.shop = new Shop(driver);
        this.checkout = new Checkout(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public BigDecimal findDiscountForCode(String fileName, String targetCode) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            // Skip header
            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String code = parts[0].trim();

                if (code.equalsIgnoreCase(targetCode)) {
                    return new BigDecimal(parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // If not found or error occurs
        return null;
    }

    public int counter(WebDriver driver) {
        By cartCount = By.cssSelector(".count");

        String countText = driver.findElement(cartCount).getText();
        int currentCount = Integer.parseInt(countText.split(" ")[0]);

        return currentCount;
    }


    //steps for step definitions

    //login steps
    //go to login page
    public void navigateToLoginPage(){
        Allure.step("Navigating to the login page", () -> {
            String url = EnvVariables.get("URL"); // assuming you're using env variables
            driver.get(url);
        });
    }
    //enter login details
    public void enterLoginDetails(){
        Allure.step("Performing login with valid credentials", () -> {
            String email = EnvVariables.get("EMAIL");
            String password = EnvVariables.get("PASSWORD");
            login.Login(email, password);
        });
    }
    //click dismiss link
    public void dissmissLink(){
        Shop shop = new Shop(driver);
        shop.dissmissLink();
    }


    //home page steps
    //go to shop
    public void navigateToShop(){
        Home home = new Home(driver);
        home.goToShop();
    }

    //shop page steps
    //add items to cart
    public void addItemsToCart(){
        Allure.step("Clicking shop button and adding items to cart", () -> {
            //use shop pom to add products to cart
            shop.Shop(
                    () -> shop.addItem(shop.beanie),
                    () -> shop.addItem(shop.belt),
                    () -> shop.addItem(shop.cap)
            );
        });
    }

    public void goToCart(){
        shop.goToCart();
    }

    //add discount
    public void applyDiscountCode(){
        Cart checkout = new Cart(driver);
        checkout.applyCode();
    }

    //assert price with discount is correct
    public void checkPriceWithDiscount(){
        Allure.step("assert total = subtotal/discount + shipping", ()-> {
            // assert price is correct
            //find number stored in total element
            WebElement totalElement = driver.findElement(By.cssSelector("tr.order-total bdi"));
            String totalString = totalElement.getText().substring(1, totalElement.getText().length());

            BigDecimal totalNum = new BigDecimal(totalString);


            //divide cart price by discount
            //left prints commented to show thought process
            WebElement coupon = driver.findElement(By.cssSelector("tr.coupon-edgewords > th"));
            String couponText = coupon.getText().substring(8, coupon.getText().length());

            WebElement subTotalEl = driver.findElement(By.cssSelector("tr.cart-subtotal bdi"));

            String subTotalString = subTotalEl.getText().substring(1, subTotalEl.getText().length());

            BigDecimal subTotal = new BigDecimal(subTotalString);

            BigDecimal shipp = new BigDecimal("3.95");
            BigDecimal result = BigDecimal.ZERO;
            UtilMethods util = new UtilMethods(driver);

            if (couponText.equals("edgewords")) {
                BigDecimal rate = util.findDiscountForCode("discount.csv", "edgewords");
                result = subTotal.multiply(rate).add(shipp);

            } else if (couponText.equals("2idiscount")) {
                BigDecimal rate = util.findDiscountForCode("discount.csv", "2idiscount");
                result = subTotal.multiply(rate).add(shipp);

            }
            //assert price displayed in total = subtotal / discount + shipping fee
            assertEquals(result.setScale(2, DOWN), totalNum);
            logger.debug("asserted prices were equal");
        });
    }

    //go to checkout
    public void navigateToCheckout() {

        WebElement checkoutButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.checkout-button")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkoutButton);
        checkoutButton.click();
    }

    //complete checkout
    public void completeCheckoutForm(){
        Allure.step("checkout form", ()-> {
            checkout.cleanCheckout();
            checkout.enterCheckoutInfo();

        });
    }

    //assert order numbers are correct
    public void checkOrderNumbersMatch () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Allure.step("assert order numbers are the same", ()-> {
            //save order number in variable
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.woocommerce-order-overview__order.order")));
            WebElement orderNumEl = driver.findElement(By.cssSelector("li.woocommerce-order-overview__order.order"));
            String orderNumStr = orderNumEl.getText();
            String orderNumSlice = orderNumStr.substring(14);
            int orderNum = Integer.parseInt(orderNumSlice);

            //go to account page
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#menu-item-46 > a"))).click();

            //go to order
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.woocommerce-MyAccount-navigation-link--orders > a"))).click();

            //get order number from account page
            WebElement firstOrderLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody > tr:first-of-type td.woocommerce-orders-table__cell-order-number a")));

            String orderIntStr = firstOrderLink.getText().substring(1);

            Integer orderInt = Integer.parseInt(orderIntStr);

            assertEquals(orderNum, orderInt);
        });
    }

}
