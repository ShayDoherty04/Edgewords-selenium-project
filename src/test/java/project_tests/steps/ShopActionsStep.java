package project_tests.steps;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import project_tests.PomPages.*;

public class ShopActionsStep {
    private WebDriver driver;
    private Shop shop;


    public ShopActionsStep(WebDriver driver) {
        this.driver = driver;
        this.shop = new Shop(driver);
    }

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

}
