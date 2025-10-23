package project_tests.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import project_tests.steps.ApplyCodeStep;
import project_tests.steps.*;
import project_tests.steps.*;
import project_tests.steps.*;
import project_tests.utilities.*;

public class ApplyCodeStepDefinitions {
    ApplyCodeStep applyCodeStep = new ApplyCodeStep(Hooks.getDriver());

    @And("the user enters the discount code")
    public void navigateToShop(){
        applyCodeStep.applyDiscountCode();
    }
}
