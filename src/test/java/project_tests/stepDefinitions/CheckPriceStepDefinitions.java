package project_tests.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import project_tests.steps.ApplyCodeStep;
import project_tests.steps.*;
import project_tests.steps.*;
import project_tests.steps.*;
import project_tests.utilities.*;

public class CheckPriceStepDefinitions {
    CheckPriceStep checkPriceStep = new CheckPriceStep(Hooks.getDriver());

    @Then("total should equal subtotal minus discount plus shipping")
    public void setCheckPriceStep (){
        checkPriceStep.checkPriceWithDiscount();
    }
}
