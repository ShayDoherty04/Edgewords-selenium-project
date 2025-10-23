package project_tests.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import project_tests.steps.LoginStep;
import project_tests.steps.LogoutStep;
import project_tests.utilities.*;

public class LogoutStepDefinitions {
    LogoutStep logoutStep = new LogoutStep(Hooks.getDriver());

    @When("user clicks logout")
    public void logout (){
        logoutStep.LogOut();
    }
}
