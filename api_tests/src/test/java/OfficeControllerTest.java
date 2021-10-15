import controllers.location_controller.office.OfficeJsonObject;
import dao.OfficeDao;
import entity.Office;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.HttpHelper;
import utils.JsonObjectHelper;
import utils.office.OfficeJsonHelper;

import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.http.ContentType.JSON;

public class OfficeControllerTest extends BaseTest {

    private static int officeId;
    private static OfficeDao officeDao = OfficeDao.getInstance();
    private static final String ADD_NEW_OFFICE = "/api/location/office?lang=en";
    private static final String GET_ALL_OFFICE = "/api/location/office?lang=en";
    //private static final String GET_CITY_BY_ID = "/api/location/city/{id}?lang=en";
    //private static final String DELETE_CITY_BY_ID = "/api/location/city/{id}?lang=en";
    //private static final String UPDATE_CITY_BY_ID = "/api/location/city/{id}?lang=en";

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


    @Test(priority = 1,description = "C5670712 Создание офиса от имени РОС-администратора")
    static void createOfficeTest() {
        /*CityDao cityDao = CityDao.getInstance();
        System.out.println(cityDao.getCityIdByCityIndex("420000"));*/
        OfficeJsonObject expectedResult = OfficeJsonHelper.createJsonObject();
        OfficeJsonObject actualResult = HttpHelper
               .postMethod(REQUEST_URL,JSON,ADD_NEW_OFFICE,JsonObjectHelper.generateObjectToJsonString(expectedResult),200,OfficeJsonObject.class);
        officeId = actualResult.getId();
        expectedResult.setId(officeId);
        System.out.println(expectedResult + "\n" + actualResult);
        Assert.assertEquals(expectedResult,actualResult);
    }
}
