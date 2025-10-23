package project_tests.steps;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import project_tests.PomPages.*;

public class CheckoutFormStep {

    private WebDriver driver;
    private Checkout checkout;

    public CheckoutFormStep (WebDriver driver) {
        this.driver = driver;
        this.checkout = new Checkout(driver);
    }

    public void completeCheckoutForm(){
        Allure.step("checkout form", ()-> {
            checkout.cleanCheckout();
            checkout.enterCheckoutInfo();

        });
    }
}
