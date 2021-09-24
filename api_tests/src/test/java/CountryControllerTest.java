import controllers.location_controller.country.CountryItem;
import dao.CountryDao;
import entity.Country;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Authorization;
import utils.HttpHelper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CountryControllerTest {
    private static Integer id;
    private static String countryCode2 = "BE";
    private static String countryCode3 = "BEL";
    private static String countryNameRu = "Бельгия";
    private static String countryNameEn = "Belgique";
    private static final String REQUEST_URL = "http://10.10.15.160:8080";
    private static final String GET_COUNTRY_BY_ID = "/api/location/country/{id}/?lang=ru";
    private static final String DELETE_COUNTRY_BY_ID = "/api/location/country/{id}?lang=en";

    @BeforeTest
    void authorization() {
        Authorization.login();
        loadDriver();
    }

    @Test(description = "GET.Get country by id /location/country/{id}?lang=ru")
    static void getCountryByIdTest() {
        Country entity = Country.builder()
                .countryCode2(countryCode2)
                .countryCode3(countryCode3)
                .countryNameRu(countryNameRu)
                .countryNameEn(countryNameEn)
                .build();
        id = CountryDao.getInstance().saveCountry(entity).getId();
        entity.setId(id);
        CountryItem response = given()
                .spec(HttpHelper.setRequestSpec(REQUEST_URL, ContentType.JSON, GET_COUNTRY_BY_ID))
                .pathParam("id", id)
                .when()
                .get()
                .then()
                .spec(HttpHelper.setResponseSpec(ContentType.JSON, 200))
                .extract()
                .as(CountryItem.class);
        CountryItem expectedEntity = CountryItem.builder()
                .id(id)
                .countryCode2(countryCode2)
                .countryCode3(countryCode3)
                .countryNameRu(countryNameRu)
                .countryNameEn(countryNameRu)
                .visibility(true)
                .build();
        assertThat(expectedEntity, is(response));
    }

    @Test
    static void deleteCountryTest() {
        Country entity = Country.builder()
                .countryCode2(countryCode2)
                .countryCode3(countryCode3)
                .countryNameRu(countryNameRu)
                .countryNameEn(countryNameRu)
                .build();
        id = CountryDao.getInstance().saveCountry(entity).getId();
        given()
                .spec(HttpHelper.setRequestSpec(REQUEST_URL, ContentType.JSON, DELETE_COUNTRY_BY_ID))
                .pathParam("id", id)
                .when()
                .delete()
                .then()
                .statusCode(200);
        assertThat(CountryDao.getInstance().getOne(id).isEmpty(), is(true));
    }

    @AfterTest
    void deleteEntity() {
        CountryDao.getInstance().deleteCountry(id);
    }

    @SneakyThrows
    private static void loadDriver() {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
}
