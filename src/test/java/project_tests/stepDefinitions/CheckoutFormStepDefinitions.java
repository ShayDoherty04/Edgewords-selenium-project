package project_tests.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import project_tests.steps.CheckoutFormStep;
import project_tests.steps.*;
import project_tests.steps.*;
import project_tests.steps.*;
import project_tests.utilities.*;

public class CheckoutFormStepDefinitions {
    CheckoutFormStep checkoutFormStep = new CheckoutFormStep(Hooks.getDriver());

    @And("the user completes checkout form")
    public void completeCheckoutForm(){
        checkoutFormStep.completeCheckoutForm();
    }

}
