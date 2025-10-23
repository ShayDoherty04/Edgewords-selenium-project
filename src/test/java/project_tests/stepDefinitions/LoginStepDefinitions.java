package project_tests.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import project_tests.steps.LoginStep;
import project_tests.utilities.*;

public class LoginStepDefinitions {
    LoginStep loginStep = new LoginStep(Hooks.getDriver());

    @Given("the user is on the login page")
    public void userOnLoginPage() {

        loginStep.navigateToLoginPage();
    }

    @When("the user enters valid log in credentials")
    public void enterDetails(){

        loginStep.enterLoginDetails();
    }

    @And("the user clicks dismiss button")
    public void dismissLink(){

        loginStep.dissmissLink();
    }

}
