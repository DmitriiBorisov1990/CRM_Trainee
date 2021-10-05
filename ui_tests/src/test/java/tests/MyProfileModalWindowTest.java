package tests;

import model.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class MyProfileModalWindowTest extends CommonConditions {

    private static final String TELEGRAM_ADMIN = "http://18.196.202.114/login";
    private static final String JIRA_URL = "https://jira.andersenlab.com/secure/Dashboard.jspa";
    private static final String SUPPORT_PAGE_URL = "https://jsupport.andersenlab.com/servicedesk/customer/user/login?destination=portals";

    @Test(groups = {"valid_user"}, description = "ЮС 5.2.1.")
    public static void myProfileMenuBarContentTest() {
        User testUser = new User(EMAIL, PASSWORD);
        List<String> namesList = Arrays.asList("Мой профиль", "Telegram admin", "Jira", "Support", "Выйти");
        List<String> actualResult = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .clickToMyProfileButton()
                .getTextModalWindow();
        //System.out.println(actualResult + "\n" + namesList);
        assertEquals(actualResult, namesList);
    }

    //TODO
    @Test(groups = {"valid_user"}, description = "Переход на страницу поддержки")
    public static void supportPageAcesTest() {
        User testUser = new User(EMAIL, PASSWORD);
        new LoginPage(driver)
                .openPage()
                .login(testUser)
                .clickToMyProfileButton()
                .clickOnSupportButton();
        switchToNewTab(driver);
        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, SUPPORT_PAGE_URL);
    }

    @Test(groups = {"valid_user"}, description = "https://jira.andersenlab.com/secure/Dashboard.jspa")
    public static void jiraPageAcesTest() {
        User testUser = new User(EMAIL, PASSWORD);
        new LoginPage(driver)
                .openPage()
                .login(testUser)
                .clickToMyProfileButton()
                .clickOnJiraButton();
        switchToNewTab(driver);
        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, JIRA_URL);
    }

    @Ignore
    @Test(groups = {"valid_user"}, description = "Переход на страницу с Telegram")
    public static void telegramAdminPageAcesTest() {
        User testUser = new User(EMAIL, PASSWORD);
        new LoginPage(driver)
                .openPage()
                .login(testUser)
                .clickToMyProfileButton()
                .clickOnTelegramAdminButton();
        List<String> activeTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(activeTabs.get(1));
        String telegramCurrentUrl = driver.getCurrentUrl();
        driver.close();
        driver.switchTo().window(activeTabs.get(0));
        assertEquals(telegramCurrentUrl, TELEGRAM_ADMIN);
    }

    private static void switchToNewTab(WebDriver driver){
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));
    }
}
