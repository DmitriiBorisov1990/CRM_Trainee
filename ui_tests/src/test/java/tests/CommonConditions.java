package tests;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import util.TestListener;

@Listeners({TestListener.class})
public class CommonConditions {

    protected static WebDriver driver;
    protected static final String PASSWORD = "159753CFThn";
    protected static final String EMAIL = "huntflow-test-16@andersenlab.com";

    @BeforeMethod(alwaysRun = true)
    public static void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public static void stopBrowser() {
        DriverSingleton.closeWebDriver();
    }
}
