package project_tests.steps;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import project_tests.PomPages.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class ProceedToCheckoutStep {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProceedToCheckoutStep(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void navigateToCheckout() {

        WebElement checkoutButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.checkout-button")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkoutButton);
        checkoutButton.click();
    }
}
