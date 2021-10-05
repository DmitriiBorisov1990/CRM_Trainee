package tests;

import model.User;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class LeftSideMenuBarTest extends CommonConditions {

    @Test(groups = {"valid_user"}, description = "ЮС 5.1.3.")
    public static void testLeftMenuBarContentTest() {
        List<String> namesList = Arrays.asList(
                "Канбан доска", "Заявки", "Кандидаты", "Статистика", "Настройки", "Поиск кандидата",
                "Уведомления", "Пользователи", "Мой Профиль");
        //User testUser = UserCreator.withCredentialsFromProperty();
        User testUser = new User(EMAIL, PASSWORD);
        List<String> resultNamesList = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .clickOnLeftMenuBar()
                .getTextLeftMenuBar();
        assertEquals(namesList, resultNamesList);
    }

    @Test(groups = {"valid_user"}, description = "ЮС 5.1.6.")
    public static void safeConditionLeftMenuBarTest() {
        boolean actualResult;
        boolean flagLeftSideMenuBarIsOpen;
        //User testUser = UserCreator.withCredentialsFromProperty();
        User testUser = new User(EMAIL, PASSWORD);
        flagLeftSideMenuBarIsOpen = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .clickOnLeftSideMenuBarAndExit();
        actualResult = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .isLeftMenuBarOpen();
        assertEquals(flagLeftSideMenuBarIsOpen, actualResult);
    }
}
