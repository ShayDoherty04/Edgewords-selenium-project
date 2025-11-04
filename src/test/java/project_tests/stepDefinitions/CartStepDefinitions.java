package project_tests.stepDefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import project_tests.steps.CartStep;
import project_tests.steps.*;
import project_tests.steps.*;
import project_tests.steps.*;
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
