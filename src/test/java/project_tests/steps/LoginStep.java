package project_tests.steps;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import project_tests.utilities.*;
import project_tests.PomPages.*;

import project_tests.utilities.EnvVariables;

public class LoginStep {
    private WebDriver driver;
    private Login login;

    public LoginStep(WebDriver driver) {
        this.driver = driver;
        this.login = new Login(driver);
    }

    public void navigateToLoginPage(){
        Allure.step("Navigating to the login page", () -> {
            String url = EnvVariables.get("URL"); // assuming you're using env variables
            driver.get(url);
        });
    }

    public void enterLoginDetails(){
        Allure.step("Performing login with valid credentials", () -> {
            String email = EnvVariables.get("EMAIL");
            String password = EnvVariables.get("PASSWORD");
            login.Login(email, password);
        });
    }
    public void dissmissLink(){
        Shop shop = new Shop(driver);
        shop.dissmissLink();
    }
}
