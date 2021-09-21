package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {
    private static final int timeToWait = 10;

    public static void waitForTextToAppear(WebDriver driver, String textToAppear, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver,timeToWait);
        wait.until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
    }

    public static void waitForElementClickable(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver,timeToWait);
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }
}
