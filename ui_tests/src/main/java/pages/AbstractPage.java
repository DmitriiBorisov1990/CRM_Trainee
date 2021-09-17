package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class AbstractPage {

    protected WebDriver driver;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    protected abstract AbstractPage openPage();

    protected final int WAIT_TIMEOUT_SECONDS = 10;

    protected WebElement getElementWithTimeOut(By by) {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected List<WebElement> getElements(By by) {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    protected String getUrl() {
        return driver.getCurrentUrl();
    }
}
