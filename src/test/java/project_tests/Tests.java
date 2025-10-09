package project_tests;

import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import project_tests.utilities.*;
import project_tests.PomPages.*;


import java.math.BigDecimal;
import java.time.Duration;

import static java.math.RoundingMode.DOWN;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.*;

import org.slf4j.*;

@ExtendWith(AllureJunit5.class)
public class Tests extends TestBase {

    private static final Logger logger = LoggerFactory.getLogger(Tests.class);




    @Test
    @Epic("edgewords tests")
    @DisplayName("test discount prices")
    @Story("apply discount to item")
    @Severity(SeverityLevel.CRITICAL)
    @Description("check user can apply discount code and discount code gives correct price")
    public void case1 (){
        //instantiate webdriver wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        Allure.step("Navigating to site and logging in", () -> {
                    //save url in variable
                    String url = EnvVariables.get("URL");
                    //open website
                    driver.get(url);

                    //use login pom to login
                    Login login = new Login(driver);
                    // get email and password variables
                    String email = EnvVariables.get("EMAIL");
                    String password = EnvVariables.get("PASSWORD");
                    login.Login(email, password);
                });
        Allure.step("go to shop", () -> {
                    //click shop button

                    Home home = new Home(driver);
                    home.goToShop();
                });

        Allure.step("Clicking shop button and adding items to cart", () -> {
                    //use shop pom to add products to cart
                    Shop shop = new Shop(driver);
                    shop.Shop(
                            () -> shop.addItem(shop.beanie),
                            () -> shop.addItem(shop.belt),
                            () -> shop.addItem(shop.cap)
                    );
                });

        // call checkout pom to apply discount
        Cart checkout = new Cart(driver);
        checkout.applyCode();


        Allure.step("assert total = subtotal/discount + shipping", ()-> {
            // assert price is correct
            //find number stored in total element
            WebElement totalElement = driver.findElement(By.cssSelector("#post-5 > div > div > div.cart-collaterals > div > table > tbody > tr.order-total > td > strong > span > bdi"));
            String totalString = totalElement.getText().substring(1, totalElement.getText().length());

            BigDecimal totalNum = new BigDecimal(totalString);


            //divide cart price by discount
            //left prints commented to show thought process
            WebElement coupon = driver.findElement(By.cssSelector("#post-5 > div > div > div.cart-collaterals > div > table > tbody > tr.cart-discount.coupon-edgewords > th"));
            String couponText = coupon.getText().substring(8, coupon.getText().length());

            WebElement subTotalEl = driver.findElement(By.cssSelector("#post-5 > div > div > div.cart-collaterals > div > table > tbody > tr.cart-subtotal > td > span > bdi"));

            String subTotalString = subTotalEl.getText().substring(1, subTotalEl.getText().length());

            BigDecimal subTotal = new BigDecimal(subTotalString);

            BigDecimal shipp = new BigDecimal("3.95");
            BigDecimal result = BigDecimal.ZERO;
            UtilMethods util = new UtilMethods();

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

//
        Allure.step("log out", ()-> {
            LogOut logOut = new LogOut(driver);
            logOut.LogOut();
        });
    }

    @Test
    @Epic("edgewords tests")
    @DisplayName("test order numbers")
    @Story("checkout and check order numbers")
    @Severity(SeverityLevel.CRITICAL)
    @Description("ensure when a user checks out the order number is the same in checkout page and account page")
    public void case2(){
        //instantiate webdriver wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Allure.step("go to website and log in", ()-> {
                    //go to edge words
                    String url = EnvVariables.get("URL");
                    driver.get(url);

                    //use login pom to login
                    Login login = new Login(driver);
                    String email = EnvVariables.get("EMAIL");
                    String password = EnvVariables.get("PASSWORD");
                    login.Login(email, password);

                });

        //click shop button
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#menu-item-43 > a"))).click();

        Allure.step("add item to cart proceed to checkout", () -> {
                    //use shop pom to add products to cart
                    Shop shop = new Shop(driver);
                    shop.Shop(
                            () -> shop.addItem(shop.beanie)
                    );

                    //go to checkout
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#post-5 > div > div > div.cart-collaterals > div > div > a"))).click();
                });

        Allure.step("checkout form", ()-> {

            Checkout checkout = new Checkout(driver);
            checkout.cleanCheckout();
            checkout.enterCheckoutInfo();

                });

        Allure.step("assert order numbers are the same", ()-> {
                    //save order number in variable
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#post-6 > div > div > div > ul > li.woocommerce-order-overview__order.order")));
                    WebElement orderNumEl = driver.findElement(By.cssSelector("#post-6 > div > div > div > ul > li.woocommerce-order-overview__order.order"));
                    String orderNumStr = orderNumEl.getText();
                    String orderNumSlice = orderNumStr.substring(14);
                    int orderNum = Integer.parseInt(orderNumSlice);

                    //go to account page
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#menu-item-46 > a"))).click();

                    //go to order
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#post-7 > div > div > nav > ul > li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--orders > a"))).click();

                    //get order number from account page
                    WebElement firstOrderLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody > tr:first-of-type td.woocommerce-orders-table__cell-order-number a")));

                    String orderIntStr = firstOrderLink.getText().substring(1);

                    Integer orderInt = Integer.parseInt(orderIntStr);

                    assertEquals(orderNum, orderInt);
                });


        Allure.step("log out", ()-> {
            LogOut logOut = new LogOut(driver);
            logOut.LogOut();
        });



    }

}
