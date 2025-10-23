package project_tests.steps;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import project_tests.utilities.*;
import project_tests.PomPages.*;

import project_tests.utilities.EnvVariables;

public class LogoutStep {
    private WebDriver driver;
    private LogOut logOut;

    public LogoutStep(WebDriver driver) {
        this.driver = driver;
        this.logOut = new LogOut(driver);
    }

    public void LogOut () {
        Allure.step("log out", ()-> {
            LogOut logOut = new LogOut(driver);
            logOut.LogOut();
        });
    }
}
