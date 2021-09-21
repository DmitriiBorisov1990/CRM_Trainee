package pages;

import driver.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private final String BASE_URL = "https://crm-trainee-react-dev.andersenlab.dev/";

    @FindBy(xpath = "//button[contains(@class,'sidebar_arrow')]")
    private WebElement buttonOpenLeftMenuBar;

    @FindBy(xpath = "//a[contains(@class,'sidebarOption_option')]//img")
    private WebElement myProfileButtonLiftMenuBar;

    private MyProfileModalWindow modalWindow;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MainPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    @Override
    public String getUrl() {
        return super.getUrl();
    }

    public MyProfileModalWindow clickToMyProfileButton() {
        WaitHelper.waitForElementClickable(driver, myProfileButtonLiftMenuBar);
        myProfileButtonLiftMenuBar.click();
        logger.info("---> Click on my profile button");
        return new MyProfileModalWindow(driver);
    }

    public LeftSideBarMenu clickOnLeftMenuBar() {
        WaitHelper.waitForElementClickable(driver, buttonOpenLeftMenuBar);
        buttonOpenLeftMenuBar.click();
        logger.info("---> Click on menu bar");
        return new LeftSideBarMenu(driver);
    }

    public boolean clickOnLeftSideMenuBarAndExit() {
        WaitHelper.waitForElementClickable(driver, buttonOpenLeftMenuBar);
        buttonOpenLeftMenuBar.click();
        boolean result = isLeftMenuBarOpen();
        new LeftSideBarMenu(this.driver)
                .clickOnMyProfile()
                .clickExit();
        logger.info("---> Click on exit button");
        return result;
    }

    public boolean isLeftMenuBarOpen() {
        String classAttribute = getElementWithTimeOut(By.tagName("nav")).getAttribute("class");
        return classAttribute.endsWith("ECnxL");
    }
}
