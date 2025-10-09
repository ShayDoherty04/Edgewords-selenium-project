package project_tests.PomPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Home {
    WebDriver driver;
    WebDriverWait wait;

    By shop = By.cssSelector("#menu-item-43 > a");

    public Home(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
    }

    public void goToShop(){
        wait.until(ExpectedConditions.elementToBeClickable(shop)).click();
    }




}
