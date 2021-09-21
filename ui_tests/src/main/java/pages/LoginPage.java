package pages;

import driver.WaitHelper;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    @FindBy(xpath = "//button[text()='Войти' and @type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//*[contains(@class,'login_error-message')]")
    private WebElement errorMessage;

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
        emailWindow.sendKeys(user.getUserEmail());
        passWindow.sendKeys(user.getPassword());
        submitButton.click();
        logger.info("---> On login page");
        return new MainPage(driver);
    }

    public LoginPage fillInEmailWindow(User user) {
        emailWindow.sendKeys(user.getUserEmail());
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
        return errorMessage.getText();
    }

    public String getErrorMessageEnterPassword() {
        return errorMessage.getText();
    }

    public String getErrorMessageInvalidEmailDomain() {
        return errorMessage.getText();
    }

    public String getErrorMessageNoSuchUser() {
        WaitHelper.waitForTextToAppear(driver, "Такой пользователь отсутствует в списке пользователей CRM Trainee", errorMessage);
        return errorMessage.getText();
    }

    public String getErrorMessageInCaseInvalidPassword() {
        WaitHelper.waitForTextToAppear(driver, "Пароль или логин введен неверно", errorMessage);
        return errorMessage.getText();
    }

    public String getErrorMessageAnotherCase() {
        return errorMessage.getText();
    }
}
