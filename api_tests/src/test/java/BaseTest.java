import lombok.SneakyThrows;
import org.testng.annotations.BeforeTest;
import utils.Authorization;

public abstract class BaseTest {

    protected static Integer id;
    protected static String REQUEST_URL = "http://10.10.15.160:8080";

    @BeforeTest
    protected void authorization() {
        Authorization.login();
        loadDriver();
    }

    @SneakyThrows
    private static void loadDriver() {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
}
