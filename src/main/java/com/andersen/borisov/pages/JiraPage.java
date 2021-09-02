package com.andersen.borisov.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class JiraPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    public final String BASE_URL_JIRA = "https://jira.andersenlab.com/secure/Dashboard.jspa";

    protected JiraPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
        logger.info("---> Jira page opened");
    }

    @Override
    protected JiraPage openPage() {
        driver.navigate().to(BASE_URL_JIRA);
        return this;
    }

    @Override
    public String getUrl() {
        return super.getUrl();
    }
}
