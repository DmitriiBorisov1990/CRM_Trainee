package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SupportPage extends AbstractPage{

    private final Logger logger = LogManager.getRootLogger();
    public final String SUPPORT_PAGE_URL = "https://jsupport.andersenlab.com/servicedesk/customer/user/login?destination=portals";

    protected SupportPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        logger.info("---> Help center page opened");
    }

    @Override
    protected AbstractPage openPage() {
        driver.navigate().to(SUPPORT_PAGE_URL);
        return this;
    }

    @Override
    public String getUrl() {
        return super.getUrl();
    }
}
