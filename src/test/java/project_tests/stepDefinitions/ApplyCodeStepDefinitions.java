package project_tests.stepDefinitions;


import io.cucumber.java.en.When;
import project_tests.utilities.*;

public class ApplyCodeStepDefinitions {
    UtilMethods utilMethods = new UtilMethods(Hooks.getDriver());

    @When("the user clicks apply coupon with a valid code")
    public void navigateToShop(){
        utilMethods.applyDiscountCode();
    }
}
