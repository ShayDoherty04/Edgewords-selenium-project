package project_tests.steps;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import project_tests.PomPages.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckOrderNumberStep {
    private WebDriver driver;
    private WebElement wait;

    public CheckOrderNumberStep(WebDriver driver){
        this.driver = driver;
    }

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
