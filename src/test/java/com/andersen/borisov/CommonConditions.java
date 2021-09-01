package com.andersen.borisov;

import com.andersen.borisov.driver.DriverSingleton;
import com.andersen.borisov.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class CommonConditions {
    protected static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public static void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public static void stopBrowser() {
        DriverSingleton.closeWebDriver();
    }
}
