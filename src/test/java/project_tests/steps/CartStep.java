package project_tests.steps;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import project_tests.utilities.*;
import project_tests.PomPages.*;

import project_tests.utilities.EnvVariables;

public class CartStep {

    private WebDriver driver;
    private Login login;
    private Shop shop;

    //constructor
    public CartStep(WebDriver driver) {
        this.driver = driver;
        this.login = new Login(driver);
        this.shop = new Shop(driver);
    }

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


}
