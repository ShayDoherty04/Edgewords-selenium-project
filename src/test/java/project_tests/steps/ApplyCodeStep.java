package project_tests.steps;

import org.openqa.selenium.WebDriver;
import project_tests.PomPages.*;

public class ApplyCodeStep {
    private WebDriver driver;
    private Cart cart;

    public ApplyCodeStep(WebDriver driver){
        this.driver = driver;
        this.cart = new Cart(driver);
    }

    public void applyDiscountCode(){
        Cart checkout = new Cart(driver);
        checkout.applyCode();
    }
}
