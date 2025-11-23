package project_tests.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project_tests.utilities.EnvVariables;
import project_tests.PomPages.*;
//import project_tests.utilities.TestBase;
import test_flows.CleanUp;

import java.net.URL;


public class Hooks {

    private static WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        try {
            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"),
                    options
            );
        }
        catch (Exception e){
            System.err.println(e);
        }

        if (driver == null) {
            throw new RuntimeException("Cannot connect to Selenium Hub");
        }

        String url = EnvVariables.get("URL");
        driver.get(url);
        driver.manage().window().maximize();
        CleanUp cleanUp = new CleanUp(driver);
        cleanUp.run();
    }

    @After
    public void tearDown() {

        if (driver != null) {
            try {
                LogOut logOut = new LogOut(driver);
                logOut.LogOut();
            } catch (Exception e) {
                System.out.println("LogOut failed: " + e.getMessage());
            } finally {
                driver.quit();
                driver = null;
            }
        }
    }





    public static WebDriver getDriver() {
        return driver;
    }
}
