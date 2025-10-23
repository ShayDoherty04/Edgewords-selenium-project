package project_tests.steps;

import org.openqa.selenium.WebDriver;
import project_tests.PomPages.*;

public class GoToShopStep {
    private WebDriver driver;


    public GoToShopStep(WebDriver driver) {
        this.driver = driver;

    }

    public void navigateToShop(){
        Home home = new Home(driver);
        home.goToShop();
    }
}
