package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class MyProfileModalWindow extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private final String BASE_URL = "https://crm-trainee-react-dev.andersenlab.dev/";

    @FindBy(xpath = "//*[text()='Jira']")
    private WebElement jiraButton;

    @FindBy(xpath = "//*[text()='Выйти']")
    private WebElement exitButton;

    @FindBy(xpath = "//*[text()='Support']")
    private WebElement supportButton;

    @FindBy(xpath = "//*[text()='Мой профиль']")
    WebElement myProfileButton;

    @FindBy(xpath = "//*[text()='Telegram admin']")
    WebElement telegramButton;

    @FindBy(xpath = "//div[contains(@class,'popupMenu_popup')]")
    private WebElement popUpMenu;

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
        String[] res = popUpMenu
                .findElement(By.xpath("//div[contains(@class,'popupMenu_popup')]"))
                .getText()
                .split("\n");
        for (int i = 0; i < res.length; i++) {
            listNamesInModalWindow.add(res[i]);
        }
        return listNamesInModalWindow;
    }

    public LoginPage clickExit() {
        exitButton.click();
        return new LoginPage(driver);
    }

    public List<String> getTextModalWindow() {
        return new MyProfileModalWindow(this.driver).getContentModalWindow();
    }

    public TelegramAdminPage clickOnTelegramAdminButton() {
        telegramButton.click();
        return new TelegramAdminPage(driver);
    }

    public JiraPage clickOnJiraButton() {
        jiraButton.click();
        return new JiraPage(driver);
    }

    public SupportPage clickOnSupportButton() {
        supportButton.click();
        return new SupportPage(driver);
    }
}
