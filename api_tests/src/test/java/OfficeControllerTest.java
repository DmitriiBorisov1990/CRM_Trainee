import controllers.location_controller.city.CityJsonObject;
import controllers.location_controller.office.OfficeJsonObject;
import dao.CityDao;
import dao.CountryDao;
import dao.OfficeDao;
import entity.Office;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.HttpHelper;
import utils.JsonObjectHelper;
import utils.city.CityJsonHelper;
import utils.office.OfficeJsonHelper;

import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.http.ContentType.JSON;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OfficeControllerTest extends BaseTest {

    private static int cityId;
    private static int officeId;
    private static int countryId;
    private static OfficeJsonObject expectedResult;
    private static OfficeDao officeDao = OfficeDao.getInstance();
    private static final String ADD_NEW_CITY = "/api/location/city?lang=en";
    private static final String ADD_NEW_OFFICE = "/api/location/office?lang=en";
    private static final String GET_ALL_OFFICE = "/api/location/office?lang=en";
    private static final String GET_OFFICE_BY_ID = "/api/location/office/{id}?lang=en";
    private static final String DELETE_OFFICE_BY_ID = "/api/location/office/{id}?lang=en";
    private static final String UPDATE_OFFICE_BY_ID = "/api/location/office/{id}?lang=en";

    @Test(description = "C5670718 Получение списка всех офисов от имени РОС админ")
    static void getAllOfficeTest() {
        Set<Integer> officeIdFromDataBase = officeDao.getAll()
                .stream()
                .map(Office::getId)
                .collect(Collectors.toSet());
        String jsonResponse = HttpHelper.getMethodGetAll(REQUEST_URL, GET_ALL_OFFICE, JSON, 200);
        Set<Integer> officeIdFromJson = JsonObjectHelper.stringToObjects(jsonResponse, OfficeJsonObject.class)
                .stream()
                .map(OfficeJsonObject::getId)
                .collect(Collectors.toSet());
        Assert.assertTrue(officeIdFromDataBase.containsAll(officeIdFromJson));
    }


    @Test(priority = 1, description = "C5670712 Создание офиса от имени РОС-администратора")
    static void createOfficeTest() {
        CityJsonObject cityJsonObject = HttpHelper
                .postMethod(REQUEST_URL, JSON, ADD_NEW_CITY, JsonObjectHelper.generateObjectToJsonString(CityJsonHelper.createJsonObject()), 200, CityJsonObject.class);
        cityId = cityJsonObject.getId();
        countryId = cityJsonObject.getCountryId();
        expectedResult = OfficeJsonHelper.createJsonObject(cityId);
        OfficeJsonObject actualResult = HttpHelper
                .postMethod(REQUEST_URL, JSON, ADD_NEW_OFFICE, JsonObjectHelper.generateObjectToJsonString(expectedResult), 200, OfficeJsonObject.class);
        officeId = actualResult.getId();
        expectedResult.setId(officeId);
        expectedResult.setCityName(actualResult.getCityName());
        assertThat(expectedResult, is(actualResult));
    }

    @Test(priority = 2, description = "C5670714 Получение данных об офисе по ID как РОС админ")
    static void getOfficeByIdTest() {
        OfficeJsonObject actualResult = HttpHelper
                .getMethodByPath(REQUEST_URL, GET_OFFICE_BY_ID, "id", JSON, officeId, 200, OfficeJsonObject.class);
        assertThat(expectedResult, is(actualResult));
    }

    @Test(priority = 3, description = "C5670713 Обновление офиса как РОС админ")
    static void updateOfficeTest() {
        expectedResult = OfficeJsonHelper.updateOfficeLocation(expectedResult);
        OfficeJsonObject actualResult = HttpHelper
                .putMethodUpdateById(REQUEST_URL, UPDATE_OFFICE_BY_ID, JsonObjectHelper.generateObjectToJsonString(expectedResult), "id", JSON, officeId, 200, OfficeJsonObject.class);
        assertThat(expectedResult, is(actualResult));

    }

    @Test(priority = 4, description = "C5670715 Удаление офиса от имени РОС-администратора")
    static void deleteOfficeTest() {
        HttpHelper.deleteMethod(REQUEST_URL, DELETE_OFFICE_BY_ID, "id", officeId, 200);
        CityDao.getInstance().deleteCity(cityId);
        CountryDao.getInstance().deleteCountry(countryId);
        Assert.assertNull(officeDao.getOne(officeId));
    }
}
