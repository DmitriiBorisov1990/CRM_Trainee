import controllers.location_controller.city.CityJsonObject;
import dao.CityDao;
import dao.CountryDao;
import entity.City;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import utils.HttpHelper;
import utils.JsonObjectHelper;
import utils.city.CityJsonHelper;

import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.http.ContentType.JSON;
@Test(priority = 1)
public class CityControllerTest extends BaseTest {

    private static int countryId;
    private static final CityDao cityDao = CityDao.getInstance();
    private static final CountryDao countryDao = CountryDao.getInstance();
    private static CityJsonObject expectedResult = CityJsonHelper.createJsonObject();
    private static final String ADD_NEW_CITY = "/api/location/city?lang=en";
    private static final String GET_ALL_CITY = "/api/location/city?lang=en";
    private static final String GET_CITY_BY_ID = "/api/location/city/{id}?lang=en";
    private static final String DELETE_CITY_BY_ID = "/api/location/city/{id}?lang=en";
    private static final String UPDATE_CITY_BY_ID = "/api/location/city/{id}?lang=en";


    @Test(description = "GET. Get all cities -> /location/city")
    static void getAllCityTest() {
        Set<Integer> cityIdFromDataBase = cityDao.getAll()
                .stream()
                .map(City::getId)
                .collect(Collectors.toSet());
        Set<Integer> cityIdFromJsonResponse = JsonObjectHelper
                .stringToObjects(HttpHelper.getMethodGetAll(REQUEST_URL, GET_ALL_CITY, JSON, 200), CityJsonObject.class)
                .stream()
                .map(CityJsonObject::getId)
                .collect(Collectors.toSet());
        Assert.assertTrue(cityIdFromDataBase.containsAll(cityIdFromJsonResponse));
    }


    @Test(priority = 5, description = "C5670708 Создание города от имени РОС-администратора")
    static void createCityTest() {
        CityJsonObject actualResult = HttpHelper
                .postMethod(REQUEST_URL, JSON, ADD_NEW_CITY, JsonObjectHelper.generateObjectToJsonString(expectedResult), 200, CityJsonObject.class);
        id = actualResult.getId();
        countryId = actualResult.getCountryId();
        expectedResult.setId(id);
        expectedResult.setCountryName(actualResult.getCountryName());
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(priority = 6,description = "C5670710 Получение данных о городе по ID как РОС админ")
    static void getCityByIdTest() {
        expectedResult.setId(id);
        CityJsonObject actualResult = HttpHelper
                .getMethodByPath(REQUEST_URL,GET_CITY_BY_ID,"id",JSON,id,200,CityJsonObject.class);
        expectedResult.setCountryName(actualResult.getCountryName());
        Assert.assertEquals(expectedResult,actualResult);
    }

    @Ignore
    @Test(priority = 7, description = "C5670709 Обновление города как РОС админ")
    static void updateCityTest() {

    }

    @Test(priority = 8, description = "C5670711 Удаление города от имени РОС-администратора")
    static void deleteCityTest() {
        HttpHelper.deleteMethod(REQUEST_URL, DELETE_CITY_BY_ID, "id", id, 200);
        countryDao.deleteCountry(countryId);
        Assert.assertNull(cityDao.getOne(id));
    }
}
