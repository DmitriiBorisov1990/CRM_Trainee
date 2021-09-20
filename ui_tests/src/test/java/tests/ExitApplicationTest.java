
package tests;

import model.User;
import org.testng.annotations.Test;
import pages.LoginPage;

import static junit.framework.Assert.assertEquals;

public class ExitApplicationTest extends CommonConditions {
    private static final String EMAIL = "huntflow-test-16@andersenlab.com";
    private static final String PASSWORD = "159753CFThn";
    private static final String LOGIN_PAGE_URL = "https://crm-trainee-react-dev.andersenlab.dev/login";
    //TODO
    @Test(groups = {"valid_user"}, description = "ЮС 4.2.")
    public static void checkExitApplication() {
        //User testUser = UserCreator.withCredentialsFromProperty();
        User testUser = new User(EMAIL, PASSWORD);
        String currentUrl = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .clickToMyProfileButton()
                .clickExit()
                .getUrl();
        assertEquals(LOGIN_PAGE_URL, currentUrl);
    }
}

