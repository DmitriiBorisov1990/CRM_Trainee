
package tests;

import model.User;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static util.ConnectionNetWork.disConnectInternet;

public class LoginFormTest extends CommonConditions {

    private static final String EMPTY_PASSWORD_FIELD_MESSAGE = "Введите пароль";
    private static final String INVALID_PASSWORD = "Пароль или логин введен неверно";
    private static final String EMPTY_EMAIL_FIELD_MESSAGE = "Введите электронную почту";
    private static final String NOT_VALID_EMAIL = "Введите корпоративную почту Andersen";
    private static final String ANOTHER_CASE = "Произошла ошибка. Обновите страницу или зайдите позже";
    private static final String NOT_VALID_USER = "Такой пользователь отсутствует в списке пользователей CRM Trainee";

    @Test(groups = {"valid_user"}, description = "ЮС 4.1.4.1.")
    public static void checkEmailErrorMessage() {
        //User testUserEmptyEmail = UserCreator.withCredentialsFromProperty();
        User testUser = new User("huntflow-test-16@andersenlab.com", "159753CFThn");
        String emptyFieldMessageText = new LoginPage(driver)
                .openPage()
                .fillInEmailWindow(testUser)
                .clearEmailField()
                .fillInPassWindow(testUser)
                .getErrorMessageEnterEmail();
        assertEquals(EMPTY_EMAIL_FIELD_MESSAGE, emptyFieldMessageText);
    }

    @Test(groups = {"valid_user"}, description = "ЮС 4.1.4.2.")
    public static void checkPasswordErrorMessage() {
        //User testUserEmptyPassword = UserCreator.withCredentialsFromProperty();
        User testUser = new User("huntflow-test-16@andersenlab.com", "159753CFThn");
        String emptyFieldMessageText = new LoginPage(driver)
                .openPage()
                .fillInEmailWindow(testUser)
                .fillInPassWindow(testUser)
                .clearPasswordField()
                .getErrorMessageEnterPassword();
        assertEquals(emptyFieldMessageText, EMPTY_PASSWORD_FIELD_MESSAGE);
    }

    @Test(groups = {"invalid_user"}, description = "ЮС 4.1.4.3.")
    public static void checkInvalidDomainNameMessage() {
        //User testUserInvalidEmailDomain = UserCreator.withCredentialsFromProperty();
        User testUser = new User("huntflow-test-16@gmail.com", "159753CFThn");
        String invalidDomainNameMessageText = new LoginPage(driver)
                .openPage()
                .fillInEmailWindow(testUser)
                .fillInPassWindow(testUser)
                .enterButtonClick()
                .getErrorMessageInvalidEmailDomain();
        assertEquals(NOT_VALID_EMAIL, invalidDomainNameMessageText);
    }

    @Test(groups = {"invalid_user"}, description = "ЮС 4.1.4.4.")
    public static void notAuthorizedUser() {
        //User testUserNotAuthorizedInDataBase = UserCreator.withCredentialsFromProperty();
        User testUser = new User("no-authorize-user@andersenlab.com", "123");
        String notAuthorizedErrorMessage = new LoginPage(driver)
                .openPage()
                .fillInEmailWindow(testUser)
                .fillInPassWindow(testUser)
                .enterButtonClick()
                .getErrorMessageNoSuchUser();
        assertEquals(NOT_VALID_USER, notAuthorizedErrorMessage);
    }

    //TODO
    @Test(groups = {"valid_user"}, description = "ЮС 4.1.4.5.")
    public static void notValidPasswordMessage() {
        //User testValidUserWithInvalidPassword = UserCreator.withCredentialsFromProperty();
        User testUser = new User("huntflow-test-16@andersenlab.com", "wrong_pass123");
        String invalidPassword = new LoginPage(driver)
                .openPage()
                .fillInEmailWindow(testUser)
                .fillInPassWindow(testUser)
                .enterButtonClick()
                .getErrorMessageInCaseInvalidPassword();
        assertEquals(INVALID_PASSWORD, invalidPassword);
    }

    @Test(groups = {"valid_user"}, description = "ЮС 4.1.4.6.")
    public static void noInternet() throws IOException {
        //User testValidUserWithInvalidPassword = UserCreator.withCredentialsFromProperty();
        User testUser = new User("huntflow-test-16@andersenlab.com", "159753CFThn");
        LoginPage loginPage = new LoginPage(driver)
                .openPage()
                .fillInEmailWindow(testUser)
                .fillInPassWindow(testUser);
        disConnectInternet(driver);
        String errorMessageAnotherCase = loginPage
                .enterButtonClick()
                .getErrorMessageAnotherCase();
        assertEquals(errorMessageAnotherCase, ANOTHER_CASE);
    }
}

