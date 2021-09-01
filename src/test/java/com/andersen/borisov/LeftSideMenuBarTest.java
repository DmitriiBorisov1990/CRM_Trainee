package com.andersen.borisov;

import com.andersen.borisov.model.User;
import com.andersen.borisov.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class LeftSideMenuBarTest extends CommonConditions {

    private static final String TELEGRAM_ADMIN = "http://18.196.202.114/login";
    private static final String JIRA_URL = "https://jira.andersenlab.com/secure/Dashboard.jspa";
    private static final String SUPPORT_PAGE_URL = "https://jsupport.andersenlab.com/servicedesk/customer/portals";

    //TODO
    @Test(groups = {"valid_user"}, description = "ЮС 5.1.3.")
    public static void testLeftMenuBarContentTest() {
        List<String> namesList = Arrays.asList(
                "Канбан доска", "Заявки", "Кандидаты", "Статистика", "Настройки", "Поиск кандидата",
                "Уведомления", "Пользователи", "Мой Профиль");
        //User testUser = UserCreator.withCredentialsFromProperty();
        User testUser = new User("huntflow-test-16@andersenlab.com", "159753CFThn");
        List<String> resultNamesList = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .clickOnLeftMenuBar()
                .getTextLeftMenuBar();
        System.out.println(namesList + "\n" + resultNamesList);
        Assert.assertEquals(namesList, resultNamesList);
    }

    @Test(groups = {"valid_user"}, description = "ЮС 5.1.6.")
    public static void safeConditionLeftMenuBarTest() {
        boolean actualResult;
        boolean flagLeftSideMenuBarIsOpen;
        //User testUser = UserCreator.withCredentialsFromProperty();
        User testUser = new User("huntflow-test-16@andersenlab.com", "159753CFThn");
        flagLeftSideMenuBarIsOpen = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .clickOnLeftSideMenuBarAndExit();
        actualResult = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .isLeftMenuBarOpen();
        Assert.assertEquals(flagLeftSideMenuBarIsOpen, actualResult);
    }


    //Как СОС/РОС,
    //я хочу открыть левое нижнее меню
    //чтобы мне был доступен его функционал
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
}
