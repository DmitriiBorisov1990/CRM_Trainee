package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.Arrays;
import java.util.List;

public class LeftSideMenuBarTest extends CommonConditions {

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
}
