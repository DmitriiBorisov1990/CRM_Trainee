package com.andersen.borisov.pages;

import com.andersen.borisov.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private final String LOGIN_PAGE_URL = "https://crm-trainee-react-dev.andersenlab.dev";

    @FindBy(id = "login")
    private WebElement emailWindow;

    @FindBy(id = "pass")
    private WebElement passWindow;

    @FindBy(xpath = "//*[@id='root']//form/button")
    private WebElement submitButton;

    private final By errorMessageEnterEmailElement = By.xpath("//*[@id='root']//form/div[1]/div/span");
    private final By errorMessageInvalidDomainName = By.xpath("//*[@id='root']//form/div[1]/div/span");
    private final By errorMessageEnterPasswordElement = By.xpath("//*[@id='root']//form/div[2]/div[2]/span");
    private final By errorMessageInvalidPassword = By.xpath("//*[text()='Пароль или логин введен неверно']");
    private final By errorMessageAnotherCase = By.xpath("//*[text()='Произошла ошибка. Обновите страницу или зайдите позже']");
    private final By errorMessageInvalidUser = By.xpath("//*[text()='Такой пользователь отсутствует в списке пользователей CRM Trainee']");

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public LoginPage openPage() {
        driver.navigate().to(LOGIN_PAGE_URL);
        return this;
    }

    @Override
    public String getUrl() {
        return super.getUrl();
    }

    public MainPage login(User user) {
        emailWindow.sendKeys(user.getUserName());
        passWindow.sendKeys(user.getPassword());
        submitButton.click();
        logger.info("---> On login page");
        return new MainPage(driver);
    }

    public LoginPage fillInEmailWindow(User user) {
        emailWindow.sendKeys(user.getUserName());
        return new LoginPage(driver);
    }

    private void clearField(WebElement element) {
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    public LoginPage clearEmailField() {
        clearField(emailWindow);
        return new LoginPage(driver);
    }

    public LoginPage clearPasswordField() {
        clearField(passWindow);
        return new LoginPage(driver);
    }

    public LoginPage fillInPassWindow(User user) {
        passWindow.sendKeys(user.getPassword());
        return new LoginPage(driver);
    }

    public LoginPage enterButtonClick() {
        submitButton.submit();
        return new LoginPage(driver);
    }

    public String getErrorMessageEnterEmail() {
        return getElement(errorMessageEnterEmailElement).getText();
    }

    public String getErrorMessageEnterPassword() {
        return getElement(errorMessageEnterPasswordElement).getText();
    }

    public String getErrorMessageInvalidEmailDomain() {
        return getElement(errorMessageInvalidDomainName).getText();
    }

    public String getErrorMessageNoSuchUser() {
        return getElement(errorMessageInvalidUser).getText();
    }

    public String getErrorMessageInCaseInvalidPassword() {
        return getElement(errorMessageInvalidPassword).getText();
    }

    public String getErrorMessageAnotherCase() {
        return getElement(errorMessageAnotherCase).getText();
    }
}
