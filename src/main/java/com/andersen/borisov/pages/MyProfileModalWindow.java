package com.andersen.borisov.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class MyProfileModalWindow extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private final String BASE_URL = "https://crm-trainee-react-dev.andersenlab.dev/";

    final By jiraButton = By.xpath("//*[text()='Jira']");
    final By exitButton = By.xpath("//*[text()='Выйти']");
    final By supportButton = By.xpath("//*[text()='Support']");
    final By myProfileButton = By.xpath("//*[text()='Мой профиль']");
    final By telegramButton = By.xpath("//*[text()='Telegram admin']");
    final By popUpMenu = By.xpath("//*[@id='root']//nav//div[2]/div[2]/div[2]");

    protected MyProfileModalWindow(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
        logger.info("---> Modal window opened");
    }

    @Override
    protected MyProfileModalWindow openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    @Override
    public String getUrl() {
        return super.getUrl();
    }

    List<String> getContentModalWindow() {
        List<String> listNamesInModalWindow = new ArrayList<>();
        WebElement modalWindowElement = getElement(popUpMenu);
        List<WebElement> elements = modalWindowElement.findElements(By.className("popupMenu_option__3ZD4c"));
        for (WebElement element : elements) {
            listNamesInModalWindow.add(element.getText());
        }
        return listNamesInModalWindow;
    }

    public LoginPage clickExit() {
        getElement(exitButton).click();
        return new LoginPage(driver);
    }

    public List<String> getTextModalWindow() {
        return new MyProfileModalWindow(this.driver).getContentModalWindow();

    }

    public TelegramAdminPage clickOnTelegramAdminButton() {
        getElement(telegramButton).click();
        return new TelegramAdminPage(driver);
    }

    public JiraPage clickOnJiraButton() {
        getElement(jiraButton).click();
        return new JiraPage(driver);
    }


    //TODO
    public MyProfilePage clickOnMyProfileButton() {
        getElement(myProfileButton).click();
        return new MyProfilePage(driver);
    }

    public SupportPage clickOnSupportButton() {
        getElement(supportButton).click();
        return new SupportPage(driver);
    }
}
