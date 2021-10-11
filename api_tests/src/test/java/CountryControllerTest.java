import controllers.location_controller.country.CountryJsonObject;
import dao.CountryDao;
import entity.Country;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.HttpHelper;
import utils.JsonObjectHelper;
import utils.country.CountryJsonHelper;

import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CountryControllerTest extends BaseTest {

    private static CountryDao countryDao = CountryDao.getInstance();
    private static final String ADD_NEW_COUNTRY = "/api/location/country?lang=en";
    private static final String GET_ALL_COUNTRY = "/api/location/country?lang=en";
    private static final String GET_COUNTRY_BY_ID = "/api/location/country/{id}/?lang=ru";
    private static final String DELETE_COUNTRY_BY_ID = "/api/location/country/{id}?lang=en";
    private static final String UPDATE_COUNTRY_BY_ID = "/api/location/country/{id}?lang=en";

    @Test(description = "C5564998 Получение списка всех стран от имени РОС админ")
    static void getAllCountryTest(){
        Set<Integer> countryIdFromDataBase = countryDao.getAll()
                .stream()
                .map(Country::getId)
                .collect(Collectors.toSet());
        Set<Integer> countryIdFromJsonResponse = JsonObjectHelper
                .stringToObjects(HttpHelper.getMethodGetAll(REQUEST_URL,GET_ALL_COUNTRY,JSON,200),CountryJsonObject.class)
                .stream()
                .map(CountryJsonObject::getId)
                .collect(Collectors.toSet());
        Assert.assertTrue(countryIdFromDataBase.containsAll(countryIdFromJsonResponse));
    }

    //TODO 201 code
    @Test(priority = 1,description = "C5564895 Создание страны от имени РОС-администратора")
    static void createCountryTest() {
        CountryJsonObject expectedResult = CountryJsonHelper.createJsonObject();
        CountryJsonObject actualResult =  HttpHelper
                .postMethod(REQUEST_URL,JSON,ADD_NEW_COUNTRY,JsonObjectHelper.generateObjectToJsonString(expectedResult),200,CountryJsonObject.class);
        id = actualResult.getId();
        expectedResult.setId(id);
        assertThat(expectedResult, is(actualResult));
    }

    @Test(priority = 2,description = "Получение названия страны по ID как РОС админ")
    static void getCountryByIdTest() {
        CountryJsonObject expectedResult = CountryJsonHelper.createJsonObject();
        expectedResult.setId(id);
        CountryJsonObject actualResult = HttpHelper
                .getMethodByPath(REQUEST_URL,GET_COUNTRY_BY_ID,"id",JSON,id,200,CountryJsonObject.class);
        assertThat(expectedResult, is(actualResult));
    }

    @Test(priority = 3,description = "C5564970 Обновление страны как РОС админ")
    static void updateCountryTest() {
        CountryJsonObject expectedResult = CountryJsonHelper.changeEnAndRuNames();
        CountryJsonObject actualResult = HttpHelper
                .putMethodUpdateById(REQUEST_URL, UPDATE_COUNTRY_BY_ID, JsonObjectHelper.generateObjectToJsonString(expectedResult), "id", JSON, id, 200, CountryJsonObject.class);
        expectedResult.setId(actualResult.getId());
        assertThat(expectedResult, is(actualResult));
    }

    @Test(priority = 4,description = "DELETE. Delete country by id -> /location/country/{id}")
    static void deleteCountryTest() {
        HttpHelper.deleteMethod(REQUEST_URL,DELETE_COUNTRY_BY_ID,"id",id,200);
        Assert.assertNull(countryDao.getOne(id));
    }
}
