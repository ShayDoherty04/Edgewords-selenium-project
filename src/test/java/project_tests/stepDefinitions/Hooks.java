package project_tests.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project_tests.utilities.EnvVariables;
import project_tests.PomPages.*;
//import project_tests.utilities.TestBase;
import test_flows.CleanUp;


public class Hooks {

    private static WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url = EnvVariables.get("URL");
        driver.get(url);
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
