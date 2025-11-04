package project_tests.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import project_tests.utilities.*;

public class CheckoutFormStepDefinitions {
    UtilMethods utilMethods = new UtilMethods(Hooks.getDriver());

    @And("places an order")
    public void completeCheckoutForm(){
        utilMethods.completeCheckoutForm();
    }

}
