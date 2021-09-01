package com.andersen.borisov.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HelpCenterPage extends AbstractPage{

    private final Logger logger = LogManager.getRootLogger();
    public static String BASE_URL = "https://jsupport.andersenlab.com/servicedesk/customer/user/login?destination=portals";

    protected HelpCenterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        logger.info("---> Help center page opened");
    }

    @Override
    protected AbstractPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }
}
