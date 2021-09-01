package com.andersen.borisov;

import com.andersen.borisov.model.User;
import com.andersen.borisov.pages.LoginPage;
import com.andersen.borisov.servise.UserCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class LeftSideBarTest extends CommonConditions {

    //TODO
    @Test(description = "ЮС 5.1.3.")
    public static void testLeftMenuBarContent() {
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

    @Test(description = "ЮС 5.1.6.")
    public static void test1() {
        User testUser = new User("huntflow-test-16@andersenlab.com", "159753CFThn");
        new LoginPage(driver).openPage().login(testUser).clickOnLeftMenuBar();
    }
}
