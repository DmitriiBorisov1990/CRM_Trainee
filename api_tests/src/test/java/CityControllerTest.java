import controllers.location_controller.city.CityJsonObject;
import dao.CityDao;
import dao.CountryDao;
import entity.City;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.HttpHelper;
import utils.JsonObjectHelper;
import utils.city.CityJsonHelper;

import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CityControllerTest extends BaseTest {

    private static int cityId;
    private static int countryId;
    private static CityJsonObject expectedResult = null;
    private static final CityDao cityDao = CityDao.getInstance();
    private static final CountryDao countryDao = CountryDao.getInstance();
    private static final String ADD_NEW_CITY = "/api/location/city?lang=en";
    private static final String GET_ALL_CITY = "/api/location/city?lang=en";
    private static final String GET_CITY_BY_ID = "/api/location/city/{id}?lang=en";
    private static final String DELETE_CITY_BY_ID = "/api/location/city/{id}?lang=en";
    private static final String UPDATE_CITY_BY_ID = "/api/location/city/{id}?lang=en";


    @Test(description = "C5670717 Получение списка всех городов от имени РОС админ")
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


    @Test(priority = 1, description = "C5670708 Создание города от имени РОС-администратора")
    static void createCityTest() {
        expectedResult = CityJsonHelper.createJsonObject();
        CityJsonObject actualResult = HttpHelper
                .postMethod(REQUEST_URL, JSON, ADD_NEW_CITY, JsonObjectHelper.generateObjectToJsonString(expectedResult), 200, CityJsonObject.class);
        cityId = actualResult.getId();
        countryId = actualResult.getCountryId();
        expectedResult.setId(cityId);
        expectedResult.setCountryName(actualResult.getCountryName());
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(priority = 2,description = "C5670710 Получение данных о городе по ID как РОС админ")
    static void getCityByIdTest() {
        CityJsonObject actualResult = HttpHelper
                .getMethodByPath(REQUEST_URL,GET_CITY_BY_ID,"id",JSON,cityId,200,CityJsonObject.class);
        expectedResult.setCountryName(actualResult.getCountryName());
        Assert.assertEquals(expectedResult,actualResult);
    }


    @Test(priority = 3, description = "C5670709 Обновление города как РОС админ")
    static void updateCityTest() {
        expectedResult = CityJsonHelper.changeCityNameAndPostIndex();
        CityJsonObject actualResult = HttpHelper
                .putMethodUpdateById(REQUEST_URL,UPDATE_CITY_BY_ID,JsonObjectHelper.generateObjectToJsonString(expectedResult),"id",JSON,cityId,200,CityJsonObject.class);
        expectedResult.setId(actualResult.getId());
        expectedResult.setCountryName(actualResult.getCountryName());
        assertThat(expectedResult,is(actualResult));
    }

    @Test(priority = 4, description = "C5670711 Удаление города от имени РОС-администратора")
    static void deleteCityTest() {
        HttpHelper.deleteMethod(REQUEST_URL, DELETE_CITY_BY_ID, "id", cityId, 200);
        countryDao.deleteCountry(countryId);
        Assert.assertNull(cityDao.getOne(cityId));
    }
}
