package project_tests.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import project_tests.utilities.*;

public class CheckPriceStepDefinitions {
    UtilMethods utilMethods = new UtilMethods(Hooks.getDriver());

    @Then("total should equal subtotal minus discount plus shipping")
    public void setCheckPriceStep (){
        utilMethods.checkPriceWithDiscount();
    }
}
