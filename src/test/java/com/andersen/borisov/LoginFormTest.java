/*

package com.andersen.borisov;
import com.andersen.borisov.model.User;
import com.andersen.borisov.pages.LoginPage;
import com.andersen.borisov.servise.UserCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.andersen.borisov.util.ConnectionNetWork.disConnectInternet;

public class LoginFormTest extends CommonConditions {

    private static final String EMPTY_PASSWORD_FIELD_MESSAGE = "Введите пароль";
    private static final String INVALID_PASSWORD = "Пароль или логин введен неверно";
    private static final String EMPTY_EMAIL_FIELD_MESSAGE = "Введите электронную почту";
    private static final String NOT_VALID_EMAIL = "Введите корпоративную почту Andersen";
    private static final String ANOTHER_CASE = "Произошла ошибка. Обновите страницу или зайдите позже";
    private static final String NOT_VALID_USER = "Такой пользователь отсутствует в списке пользователей CRM Trainee";


    @Test(groups = {"valid_user" },description = "ЮС 4.1.4.1.")
    public static void checkEmailErrorMessage() {
        User testUserEmptyEmail = UserCreator.withCredentialsFromProperty();
        String emptyFieldMessageText = new LoginPage(driver)
                .openPage()
                .fillInEmailWindow(testUserEmptyEmail)
                .clearEmailField()
                .fillInPassWindow(testUserEmptyEmail)
                .getErrorMessageEnterEmailText();
        Assert.assertEquals(EMPTY_EMAIL_FIELD_MESSAGE, emptyFieldMessageText);
    }

    @Test(groups = {"valid_user" },description = "ЮС 4.1.4.2.")
    public static void checkPasswordErrorMessage() {
        User testUserEmptyPassword = UserCreator.withCredentialsFromProperty();
        String emptyFieldMessageText = new LoginPage(driver)
                .openPage()
                .fillInEmailWindow(testUserEmptyPassword)
                .fillInPassWindow(testUserEmptyPassword)
                .clearPasswordField()
                .getErrorMessageEnterPasswordText();
        Assert.assertEquals(emptyFieldMessageText, EMPTY_PASSWORD_FIELD_MESSAGE);
    }

    @Test(groups = {"invalid_user" },description = "ЮС 4.1.4.3.")
    public static void checkInvalidDomainNameMessage() {
        User testUserInvalidEmailDomain = UserCreator.withCredentialsFromProperty();
        String invalidDomainNameMessageText = new LoginPage(driver)
                .openPage()
                .fillInEmailWindow(testUserInvalidEmailDomain)
                .fillInPassWindow(testUserInvalidEmailDomain)
                .enterButtonClick()
                .getErrorMessageInvalidEmailDomain();
        Assert.assertEquals(NOT_VALID_EMAIL, invalidDomainNameMessageText);
    }
    //TODO
    @Test(groups = {"invalid_user" },description = "ЮС 4.1.4.4.")
    public static void notAuthorizedUser() {
        User testUserNotAuthorizedInDataBase = UserCreator.withCredentialsFromProperty();
        String notAuthorizedErrorMessage = new LoginPage(driver)
                .openPage()
                .fillInEmailWindow(testUserNotAuthorizedInDataBase)
                .fillInPassWindow(testUserNotAuthorizedInDataBase)
                .enterButtonClick()
                .getErrorMessageNoSuchUser();
        Assert.assertEquals(NOT_VALID_USER, notAuthorizedErrorMessage);
    }
    //TODO
    @Test(groups = {"valid_user" },description = "ЮС 4.1.4.5.")
    public static void notValidPasswordMessage() {
        User testValidUserWithInvalidPassword = UserCreator.withCredentialsFromProperty();
        testValidUserWithInvalidPassword.setPassword("wrong_pass123");
        String invalidPassword = new LoginPage(driver)
                .openPage()
                .fillInEmailWindow(testValidUserWithInvalidPassword)
                .fillInPassWindow(testValidUserWithInvalidPassword)
                .enterButtonClick()
                .getErrorMessageInCaseInvalidPassword();
        Assert.assertEquals(INVALID_PASSWORD, invalidPassword);
    }

    @Test(groups = {"valid_user" },description = "ЮС 4.1.4.6.")
    public static void noInternet() throws IOException {
        User testValidUserWithInvalidPassword = UserCreator.withCredentialsFromProperty();
        LoginPage loginPage = new LoginPage(driver)
                .openPage()
                .fillInEmailWindow(testValidUserWithInvalidPassword)
                .fillInPassWindow(testValidUserWithInvalidPassword);
        disConnectInternet(driver);
        String errorMessageAnotherCase = loginPage.enterButtonClick().getErrorMessageAnotherCase();
        Assert.assertEquals(errorMessageAnotherCase,ANOTHER_CASE);
    }
}

*/
