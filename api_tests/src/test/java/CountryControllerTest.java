import controllers.location_controller.country.CountryJsonObject;
import dao.CountryDao;
import entity.Country;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.HttpHelper;
import utils.JsonObjectHelper;
import utils.country.CountryEntityHelper;
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

    @Test(description = "GET. Get all countries -> /location/country")
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
    @Test(description = "POST. Create new country -> /location/country")
    static void createCountryTest() {
        CountryJsonObject expectedResult = CountryJsonHelper.createJsonObject();
        String jsonString = JsonObjectHelper.generateObjectToJsonString(expectedResult);
        id =  HttpHelper.postMethod(REQUEST_URL,JSON,ADD_NEW_COUNTRY,jsonString,200,CountryJsonObject.class,"id");
        expectedResult.setId(id);
        Country countryInDataBase = CountryEntityHelper.getCountryFromDataBaseById(id);
        CountryJsonObject actualResult = CountryJsonHelper.mapEntityToJsonObject();
        CountryDao.getInstance().deleteCountry(id);
        assertThat(expectedResult, is(actualResult));
    }

    @Test(description = "GET. Get country by id -> /location/country/{id}?lang=ru")
    static void getCountryByIdTest() {
        Country country = CountryEntityHelper.createCountryEntity();
        id = CountryEntityHelper.saveCountryInDataBaseAndGetId(country);
        country.setId(id);
        CountryJsonObject expectedResult = CountryJsonHelper.mapEntityToJsonObject();
        CountryJsonObject actualResult = HttpHelper.getMethodByPath(REQUEST_URL,GET_COUNTRY_BY_ID,"id",JSON,id,200,CountryJsonObject.class);
        CountryDao.getInstance().deleteCountry(id);
        assertThat(expectedResult, is(actualResult));
    }

    @Test(description = "PUT. Update country by id -> /location/country/{id}")
    static void updateCountryTest() {
        Country country = CountryEntityHelper.createCountryEntity();
        id = CountryEntityHelper.saveCountryInDataBaseAndGetId(country);
        country.setId(id);
        country = CountryEntityHelper.updateCountry();
        CountryJsonObject expectedResult = CountryJsonHelper.mapEntityToJsonObject();
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
