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
    CartStep cartStep = new CartStep(Hooks.getDriver());
    @Given("the user is on the cart page with an item in the cart")
    public void navigateToShop(){
        cartStep.navigateToLoginPage();
        cartStep.enterLoginDetails();
        cartStep.navigateToShop();
        cartStep.addItemsToCart();
        cartStep.goToCart();
    }
}
