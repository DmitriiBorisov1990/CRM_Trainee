import controllers.location_controller.city.CityJsonObject;
import dao.CityDao;
import dao.CountryDao;
import entity.City;
import entity.Country;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.HttpHelper;
import utils.JsonObjectHelper;
import utils.city.CityEntityHelper;
import utils.city.CityJsonHelper;
import utils.country.CountryEntityHelper;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CityControllerTest extends BaseTest {

    private static int id;
    private static final String REQUEST_URL = "http://10.10.15.160:8080";
    private static final String ADD_NEW_CITY = "/api/location/city?lang=en";
    private static final String GET_ALL_CITY = "/api/location/city?lang=en";
    private static final String GET_CITY_BY_ID = "/api/location/city/{id}?lang=en";
    private static final String DELETE_CITY_BY_ID = "/api/location/city/{id}?lang=en";
    private static final String UPDATE_CITY_BY_ID = "/api/location/city/{id}?lang=en";

    //TODO ?
    @Test(description = "GET. Get all cities -> /location/city")
    static void getAllCityTest() {
        String jsonString = HttpHelper.getMethodGetAll(REQUEST_URL, GET_ALL_CITY, JSON, 200);
        //System.out.println(JsonObjectHelper.stringToObjects(jsonString, CityJsonObject.class) + "\n" + CityDao.getInstance().getAll());
        Assert.assertTrue(JsonObjectHelper.stringToObjects(jsonString, CityJsonObject.class).size() == CityDao.getInstance().getAll().size());
    }

    @Test(description = "POST. Create new city -> /location/city")
    static void createCityTest() {
        Country country = CountryEntityHelper.createCountryEntity();
        int countryId = CountryEntityHelper.saveCountryInDataBaseAndGetId(country);
        String countryNameEn = country.getCountryNameEn();
        CityJsonObject expectedResult = CityJsonHelper.createJsonObject(countryId, countryNameEn);
        String jsonString = JsonObjectHelper.generateObjectToJsonString(expectedResult);
        //id = HttpHelper.postMethod(REQUEST_URL, JSON, ADD_NEW_CITY, jsonString, 200, CityJsonObject.class);
        expectedResult.setId(id);
        City cityInDataBase = CityEntityHelper.getCityFromDataBaseById(id);
        CityJsonObject actualResult = CityJsonHelper.mapEntityToJsonObject(cityInDataBase, countryNameEn);
        CityDao.getInstance().deleteCity(id);
        CountryDao.getInstance().deleteCountry(countryId);
        assertThat(expectedResult, is(actualResult));
    }

    @Test(description = "GET. Get city by id -> /location/city/{id}?lang=ru")
    static void getCityByIdTest() {
        Country country = CountryEntityHelper.createCountryEntity();
        int countryId = CountryEntityHelper.saveCountryInDataBaseAndGetId(country);
        String countryNameRu = country.getCountryNameRu();
        City city = CityEntityHelper.createCityEntity(countryId);
        id = CityEntityHelper.saveCityInDataBaseAndGetId(city);
        city.setId(id);
        CityJsonObject expectedResult = CityJsonHelper.mapEntityToJsonObject(city, countryNameRu);
        CityJsonObject actualResult = HttpHelper.getMethodByPath(REQUEST_URL, GET_CITY_BY_ID, "id", JSON, id, 200, CityJsonObject.class);
        CityDao.getInstance().deleteCity(id);
        CountryDao.getInstance().deleteCountry(city.getCountryId());
        assertThat(expectedResult, is(actualResult));
    }

    @Test(description = "PUT. Update city by id -> /location/city/{id}")
    static void updateCityTest() {
        Country country = CountryEntityHelper.createCountryEntity();
        int countryId = CountryEntityHelper.saveCountryInDataBaseAndGetId(country);
        String countryNameRu = country.getCountryNameRu();
        City city = CityEntityHelper.createCityEntity(countryId);
        id = CityEntityHelper.saveCityInDataBaseAndGetId(city);
        city.setId(id);
        city = CityEntityHelper.updateCity(city,countryId);
        CityJsonObject expectedResult = CityJsonHelper.mapEntityToJsonObject(city, countryNameRu);
        String requestBody = JsonObjectHelper.generateObjectToJsonString(expectedResult);
        CityJsonObject actualResult = HttpHelper.putMethodUpdateById(REQUEST_URL,UPDATE_CITY_BY_ID,requestBody,"id",JSON,id,200,CityJsonObject.class);
        CityDao.getInstance().deleteCity(id);
        CountryDao.getInstance().deleteCountry(city.getCountryId());
        assertThat(expectedResult, is(actualResult));
    }

    @Test(description = "DELETE. Delete city by id -> /location/city/{id}")
    static void deleteCityTest() {
        Country country = CountryEntityHelper.createCountryEntity();
        int countryId = CountryEntityHelper.saveCountryInDataBaseAndGetId(country);
        City city = CityEntityHelper.createCityEntity(countryId);
        id = CityEntityHelper.saveCityInDataBaseAndGetId(city);
        HttpHelper.deleteMethod(REQUEST_URL, DELETE_CITY_BY_ID, "id", id, 200);
        CountryDao.getInstance().deleteCountry(countryId);
        Assert.assertNull(CityEntityHelper.getCityFromDataBaseById(id));
    }
}
