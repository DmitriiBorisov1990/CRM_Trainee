import dao.CountryDao;
import entity.Country;
import lombok.SneakyThrows;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.api.Authorization;

import java.util.Optional;

public class LocationControllerTest {

    @BeforeTest
    void start() {
        Authorization.login();
        loadDriver();
    }

    @Test
    public static void getCountryById(){

       Optional<Country> countryOptional = CountryDao.getInstance().getOne(1);
        System.out.println(countryOptional.toString());

    }
    @SneakyThrows
    private static void loadDriver() {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
}
