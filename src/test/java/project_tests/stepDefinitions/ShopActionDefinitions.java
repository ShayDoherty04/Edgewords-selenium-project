package project_tests.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import project_tests.steps.GoToShopStep;
import project_tests.steps.LoginStep;
import project_tests.steps.ShopActionsStep;
import project_tests.utilities.*;

public class ShopActionDefinitions {

    ShopActionsStep shopActionsStep = new ShopActionsStep(Hooks.getDriver());

    @And("the user clicks add to cart button")
    public void addItemsToCart(){
        shopActionsStep = new ShopActionsStep(Hooks.getDriver());
        shopActionsStep.addItemsToCart();
    }

}
