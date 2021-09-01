package com.andersen.borisov.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage{

    private final Logger logger = LogManager.getRootLogger();
    private final String BASE_URL = "https://crm-trainee-react-dev.andersenlab.dev/";

    private final By buttonOpenLeftMenuBar = By.className("sidebar_arrow__2XyM_");
    private final By myProfileButton = By.xpath("//*[@id='root']//div[2]//div[2]//a");
    private final By supportButton = By.xpath("//*[@id='root']//nav//div[2]//div//a[4]");
    private final By exitButton = By.xpath("//*[text()='Выйти']");

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MainPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public MainPage clickToMyProfileButton() {
        getElement(myProfileButton).click();
        return this;
    }

    public HelpCenterPage openSupportPage() {
        logger.info("---> Login performed and main page opened");
        getElement(supportButton).click();
        return new HelpCenterPage(driver);
    }

    public LoginPage clickExit() {
        getElement(exitButton).click();
        return new LoginPage(driver);
    }

    public LeftSideBar clickOnLeftMenuBar() {
        getElement(buttonOpenLeftMenuBar).click();
        logger.info("---> Click on menu bar");
        return new LeftSideBar(driver);
    }
}
