package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MyProfilePage extends AbstractPage{

    private final Logger logger = LogManager.getRootLogger();
    private final String MY_PROFILE_PAGE_URL = "https://crm-trainee-react-dev.andersenlab.dev/";

    protected MyProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    protected AbstractPage openPage() {
        driver.navigate().to(MY_PROFILE_PAGE_URL);
        return this;
    }

    @Override
    public String getUrl() {
        return super.getUrl();
    }
}
