package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private final String BASE_URL = "https://crm-trainee-react-dev.andersenlab.dev/";

    private final By buttonOpenLeftMenuBar = By.className("sidebar_arrow__2XyM_");
    private final By myProfileButtonLiftMenuBar = By.xpath("//*[@id='root']//div[2]//div[2]//a");

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
        getElement(myProfileButtonLiftMenuBar).click();
        return new MyProfileModalWindow(driver);
    }

    public LeftSideBarMenu clickOnLeftMenuBar() {
        getElement(buttonOpenLeftMenuBar).click();
        logger.info("---> Click on menu bar");
        return new LeftSideBarMenu(driver);
    }

    public boolean clickOnLeftSideMenuBarAndExit() {
        getElement(buttonOpenLeftMenuBar).click();
        boolean result = isLeftMenuBarOpen();
        new LeftSideBarMenu(this.driver)
                .clickOnMyProfile()
                .clickExit();
        return result;
    }

    public boolean isLeftMenuBarOpen() {
        String classAttribute = getElement(By.tagName("nav")).getAttribute("class");
        return classAttribute.endsWith("ECnxL");
    }
}
