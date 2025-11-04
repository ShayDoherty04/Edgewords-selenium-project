package project_tests.stepDefinitions;
import io.cucumber.java.en.Given;
import project_tests.utilities.*;

public class CartStepDefinitions {
    UtilMethods utilMethods = new UtilMethods(Hooks.getDriver());
    @Given("the user is on the cart page with an item in the cart")
    public void navigateToShop(){
        utilMethods.navigateToLoginPage();
        utilMethods.enterLoginDetails();
        utilMethods.navigateToShop();
        utilMethods.addItemsToCart();
        utilMethods.goToCart();
    }
}
