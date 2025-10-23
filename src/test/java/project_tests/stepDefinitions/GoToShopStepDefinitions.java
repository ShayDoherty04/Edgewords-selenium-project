package project_tests.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import project_tests.steps.GoToShopStep;
import project_tests.utilities.*;

public class GoToShopStepDefinitions {
    GoToShopStep goToShopStep = new GoToShopStep(Hooks.getDriver());

    @And("the user navigates to the shop")
    public void navigateToShop(){
        goToShopStep = new GoToShopStep(Hooks.getDriver());
        goToShopStep.navigateToShop();
    }


}
