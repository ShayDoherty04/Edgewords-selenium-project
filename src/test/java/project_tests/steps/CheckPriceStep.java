package project_tests.steps;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project_tests.PomPages.*;

import project_tests.utilities.UtilMethods;

import java.math.BigDecimal;

import static java.math.RoundingMode.DOWN;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckPriceStep {

    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(CheckPriceStep.class);

    public CheckPriceStep(WebDriver driver){
        this.driver = driver;
    }

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
    }

}
