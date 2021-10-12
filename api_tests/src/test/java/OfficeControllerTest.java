

public class OfficeControllerTest extends BaseTest {

    private static int id;
    private static final String REQUEST_URL = "http://10.10.15.160:8080";
    //private static final String ADD_NEW_CITY = "/api/location/city?lang=en";
    private static final String GET_ALL_OFFICE = "/api/location/office?lang=en";
    //private static final String GET_CITY_BY_ID = "/api/location/city/{id}?lang=en";
    //private static final String DELETE_CITY_BY_ID = "/api/location/city/{id}?lang=en";
    //private static final String UPDATE_CITY_BY_ID = "/api/location/city/{id}?lang=en";

    //TODO extract all id and compare them
    static void getAllOfficeTest() {
        /*Set<Integer> idFromDataBase = OfficeDao.getAll().stream().map(Office::getId).collect(Collectors.toSet());
        String jsonResponse = HttpHelper.getMethodGetAll(REQUEST_URL, GET_ALL_OFFICE, JSON, 200);
        Set<Integer> idFromJson = JsonObjectHelper.stringToObjects(jsonResponse, OfficeJsonObject.class).stream().map(OfficeJsonObject::getId).collect(Collectors.toSet());
        Assert.assertTrue(idFromDataBase.containsAll(idFromJson));*/
    }

    static void createOfficeTest() {
        //Office office = OfficeEntityHelper.createOfficeEntity();
    }
}
