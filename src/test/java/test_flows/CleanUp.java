package test_flows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import project_tests.PomPages.*;
import project_tests.utilities.*;


public class CleanUp {
    WebDriver driver;

    Login login;
    Cart cart;
    Checkout checkout;
    Shop shop;
    LogOut logOut;
    Home home;

    public CleanUp(WebDriver driver) {
        this.driver = driver;
        login = new Login(driver);
        cart = new Cart(driver);
        checkout = new Checkout(driver);
        shop = new Shop(driver);
        logOut = new LogOut(driver);
        home = new Home(driver);
    }

    public void run() {
        String email = EnvVariables.get("EMAIL");
        String password = EnvVariables.get("PASSWORD");
        login.Login(email, password);
        home.goToShop();
        By beanie = By.cssSelector("#main > ul > li.product.type-product.post-27.status-publish.first.instock.product_cat-accessories.has-post-thumbnail.sale.shipping-taxable.purchasable.product-type-simple > a.button.product_type_simple.add_to_cart_button.ajax_add_to_cart");
        shop.addItem(beanie);
        shop.goToCart();
        cart.removeCode();
        cart.clearCart();
        logOut.LogOut();
    }

}

