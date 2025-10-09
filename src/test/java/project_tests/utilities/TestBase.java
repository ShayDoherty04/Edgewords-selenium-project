package project_tests.utilities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project_tests.PomPages.Login;
import test_flows.CleanUp;

public class TestBase {
    protected WebDriver driver;

    private static final Logger logger = LoggerFactory.getLogger(TestBase.class);


    @BeforeEach
    public void setUp(TestInfo testInfo){ //Runs before each and every test in the class - setting up a new (clean) browser
        driver = new ChromeDriver(); //Opens Chrome and puts a reference to it in the (generic) WebDriver field
        logger.info("Starting test: " + testInfo.getDisplayName());
        String url = EnvVariables.get("URL");
        driver.get(url);
        CleanUp cleanUp = new CleanUp(driver);
        cleanUp.run();
    }




    @AfterEach
    public void tearDown(TestInfo testInfo){ //Runs each time a test finishes
        //driver.close(); //Closes the current tab
        driver.quit(); //Quits the browser - and the driver server
        logger.info("Ending test: " + testInfo.getDisplayName());
    }

}