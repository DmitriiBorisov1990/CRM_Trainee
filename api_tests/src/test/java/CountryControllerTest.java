import controllers.location_controller.country.CountryJsonObject;
import dao.CountryDao;
import entity.Country;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.HttpHelper;
import utils.JsonObjectHelper;
import utils.country.CountryEntityHelper;
import utils.country.CountryJsonHelper;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CountryControllerTest extends BaseTest {
    private static int id;
    private static final String REQUEST_URL = "http://10.10.15.160:8080";
    private static final String ADD_NEW_COUNTRY = "/api/location/country?lang=en";
    private static final String GET_ALL_COUNTRY = "/api/location/country?lang=en";
    private static final String GET_COUNTRY_BY_ID = "/api/location/country/{id}/?lang=ru";
    private static final String DELETE_COUNTRY_BY_ID = "/api/location/country/{id}?lang=en";
    private static final String UPDATE_COUNTRY_BY_ID = "/api/location/country/{id}?lang=en";


    //TODO ?
    @Test(description = "GET. Get all countries -> /location/country")
    static void getAllCountryTest(){
        String jsonString = HttpHelper.getMethodGetAll(REQUEST_URL,GET_ALL_COUNTRY,JSON,200);
        //System.out.println(JsonObjectHelper.stringToObjects(jsonString, CountryJsonObject.class));
        Assert.assertTrue(JsonObjectHelper.stringToObjects(jsonString, CountryJsonObject.class).size() == CountryDao.getInstance().getAll().size());
    }

    //TODO 201 code
    @Test(description = "POST. Create new country -> /location/country")
    static void createCountryTest() {
        CountryJsonObject expectedResult = CountryJsonHelper.createJsonObject();
        String jsonString = JsonObjectHelper.generateObjectToJsonString(expectedResult);
        id =  HttpHelper.postMethod(REQUEST_URL,JSON,ADD_NEW_COUNTRY,jsonString,200,CountryJsonObject.class,"id");
        expectedResult.setId(id);
        Country countryInDataBase = CountryEntityHelper.getCountryFromDataBaseById(id);
        CountryJsonObject actualResult = CountryJsonHelper.mapEntityToJsonObject(countryInDataBase);
        CountryDao.getInstance().deleteCountry(id);
        assertThat(expectedResult, is(actualResult));
    }

    @Test(description = "GET. Get country by id -> /location/country/{id}?lang=ru")
    static void getCountryByIdTest() {
        Country country = CountryEntityHelper.createCountryEntity();
        id = CountryEntityHelper.saveCountryInDataBaseAndGetId(country);
        country.setId(id);
        CountryJsonObject expectedResult = CountryJsonHelper.mapEntityToJsonObject(country);
        CountryJsonObject actualResult = HttpHelper.getMethodByPath(REQUEST_URL,GET_COUNTRY_BY_ID,"id",JSON,id,200,CountryJsonObject.class);
        CountryDao.getInstance().deleteCountry(id);
        assertThat(expectedResult, is(actualResult));
    }

    @Test(description = "PUT. Update country by id -> /location/country/{id}")
    static void updateCountryTest() {
        Country country = CountryEntityHelper.createCountryEntity();
        id = CountryEntityHelper.saveCountryInDataBaseAndGetId(country);
        country.setId(id);
        country = CountryEntityHelper.updateCountry(country);
        CountryJsonObject expectedResult = CountryJsonHelper.mapEntityToJsonObject(country);
        String requestBody = JsonObjectHelper.generateObjectToJsonString(expectedResult);
        CountryJsonObject actualResult = HttpHelper.putMethodUpdateById(REQUEST_URL, UPDATE_COUNTRY_BY_ID, requestBody, "id", JSON, id, 200, CountryJsonObject.class);
        CountryDao.getInstance().deleteCountry(id);
        assertThat(expectedResult, is(actualResult));
    }

    @Test(description = "DELETE. Delete country by id -> /location/country/{id}")
    static void deleteCountryTest() {
        Country entity = CountryEntityHelper.createCountryEntity();
        id = CountryEntityHelper.saveCountryInDataBaseAndGetId(entity);
        HttpHelper.deleteMethod(REQUEST_URL,DELETE_COUNTRY_BY_ID,"id",id,200);
        Assert.assertNull(CountryEntityHelper.getCountryFromDataBaseById(id));
    }
}
