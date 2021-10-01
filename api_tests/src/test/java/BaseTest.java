import lombok.SneakyThrows;
import org.testng.annotations.BeforeTest;
import utils.Authorization;

public abstract class BaseTest {
    private static Integer id;

    @BeforeTest
    protected void authorization() {
        Authorization.login();
        loadDriver();
    }

    //TODO
    /*protected static void deleteEntity(int id) {
        CountryDao.getInstance().deleteCountry(id);
    }*/

    @SneakyThrows
    private static void loadDriver() {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
}
