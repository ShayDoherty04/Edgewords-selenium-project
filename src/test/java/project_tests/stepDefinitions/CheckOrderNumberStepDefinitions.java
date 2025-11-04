package project_tests.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import project_tests.steps.ApplyCodeStep;
import project_tests.steps.*;
import project_tests.steps.*;
import project_tests.steps.*;
import project_tests.utilities.*;

public class CheckOrderNumberStepDefinitions {

    UtilMethods utilMethods = new UtilMethods(Hooks.getDriver());

    @Then("order number in checkout should match order number in previous orders")
    public void setCheckPriceStep (){
        utilMethods.checkOrderNumbersMatch();
    }
}
