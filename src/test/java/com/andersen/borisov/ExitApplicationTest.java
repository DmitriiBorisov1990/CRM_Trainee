

package com.andersen.borisov;
import com.andersen.borisov.model.User;
import com.andersen.borisov.pages.LoginPage;
import com.andersen.borisov.servise.UserCreator;
import junit.framework.Assert;
import org.testng.annotations.Test;

public class ExitApplicationTest extends CommonConditions {

    private static final String LOGIN_PAGE_URL = "https://crm-trainee-react-dev.andersenlab.dev/login";
    //TODO
    @Test(groups = {"valid_user" },description = "ЮС 4.2.")
    public static void checkExitApplication() {
        //User testUser = UserCreator.withCredentialsFromProperty();
        User testUser = new User("huntflow-test-16@andersenlab.com","159753CFThn");
        String currentUrl = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .clickToMyProfileButton()
                .clickExit()
                .getUrl();
        Assert.assertEquals(LOGIN_PAGE_URL, currentUrl);
    }
}

