package com.andersen.borisov;

import com.andersen.borisov.model.User;
import com.andersen.borisov.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyProfileModalWindowTest extends CommonConditions {

    private static final String TELEGRAM_ADMIN = "http://18.196.202.114/login";
    private static final String JIRA_URL = "https://jira.andersenlab.com/secure/Dashboard.jspa";
    private static final String SUPPORT_PAGE_URL = "https://jsupport.andersenlab.com/servicedesk/customer/user/login?destination=portals";

    @Test(groups = {"valid_user"}, description = "ЮС 5.2.1.")
    public static void myProfileMenuBarContentTest() {
        User testUser = new User("huntflow-test-16@andersenlab.com", "159753CFThn");
        List<String> namesList = Arrays.asList("Мой профиль", "Telegram admin", "Jira", "Support", "Выйти");
        List<String> actualResult = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .clickToMyProfileButton()
                .getTextModalWindow();
        System.out.println(actualResult + "\n" + namesList);
        Assert.assertEquals(actualResult, namesList);
    }

    //TODO
    @Test(groups = {"valid_user"},description = "https://jsupport.andersenlab.com/servicedesk/customer/portals")
    public static void supportPageAcesTest(){
        User testUser = new User("huntflow-test-16@andersenlab.com", "159753CFThn");
        //String actualResult =
                new LoginPage(driver)
                .openPage()
                .login(testUser)
                .clickToMyProfileButton()
                .clickOnSupportButton();
        List<String> activeTabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(activeTabs.get(1));
        String telegramCurrentUrl = driver.getCurrentUrl();
        driver.close();
        driver.switchTo().window(activeTabs.get(0));
                //.getUrl();
        Assert.assertEquals(telegramCurrentUrl,SUPPORT_PAGE_URL);
    }

    @Test(groups = {"valid_user"}, description = "https://jira.andersenlab.com/secure/Dashboard.jspa")
    public static void jiraPageAcesTest(){
        User testUser = new User("huntflow-test-16@andersenlab.com", "159753CFThn");
        //String actualResult = null;
        new LoginPage(driver)
                .openPage()
                .login(testUser)
                .clickToMyProfileButton()
                .clickOnJiraButton();
        List<String> activeTabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(activeTabs.get(1));
        String telegramCurrentUrl = driver.getCurrentUrl();
        driver.close();
        driver.switchTo().window(activeTabs.get(0));
                //.getUrl();
        Assert.assertEquals(telegramCurrentUrl,JIRA_URL);
    }

    @Test(groups = {"valid_user"}, description = "http://18.196.202.114/login")
    public static void telegramAdminPageAcesTest(){
        User testUser = new User("huntflow-test-16@andersenlab.com", "159753CFThn");
        //String actualResult
                new LoginPage(driver)
                .openPage()
                .login(testUser)
                .clickToMyProfileButton()
                .clickOnTelegramAdminButton();
        List<String> activeTabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(activeTabs.get(1));
        String telegramCurrentUrl = driver.getCurrentUrl();
        driver.close();
        driver.switchTo().window(activeTabs.get(0));
                //.getUrl();
        Assert.assertEquals(telegramCurrentUrl,TELEGRAM_ADMIN);
    }
}
