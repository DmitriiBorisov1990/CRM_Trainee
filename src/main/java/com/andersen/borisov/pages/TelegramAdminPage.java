package com.andersen.borisov.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TelegramAdminPage extends AbstractPage{

    private final Logger logger = LogManager.getRootLogger();
    private final String TELEGRAM_ADMIN = "http://18.196.202.114/login";

    protected TelegramAdminPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
        logger.info("---> Telegram opened");
    }

    @Override
    protected AbstractPage openPage() {
        driver.navigate().to(TELEGRAM_ADMIN);
        return this;
    }

    @Override
    public String getUrl() {
        return super.getUrl();
    }
}
