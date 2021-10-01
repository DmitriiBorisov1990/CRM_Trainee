import controllers.role_controller.RoleJsonObject;
import dao.RoleDao;
import entity.Role;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.HttpHelper;
import utils.JsonObjectHelper;
import utils.role.RoleEntityHelper;
import utils.role.RoleJsonHelper;

import static io.restassured.http.ContentType.JSON;

public class RoleControllerTest extends BaseTest {

    private static int id;
    private static final String CREATE_ROLE = "/api/role";
    private static final String GET_ALL_ROLE = "/api/role";
    private static final String GET_ROLE_BY_ID = "/api/role/{id}";
    private static final String UPDATE_ROLE_BY_ID = "/api/role/{id}";
    private static final String DELETE_ROLE_BY_ID = "/api/role/{id}";
    private static final String REQUEST_URL = "http://10.10.15.160:8080";

    //TODO ?
    @Test(description = "GET. Role controller get all user roles -> /role")
    static void getAllRoleTest() {
        String jsonString = HttpHelper.getMethodGetAll(REQUEST_URL, GET_ALL_ROLE, JSON, 200);
        //System.out.println(RoleDao.getInstance().getAll() + "\n" + JsonObjectHelper.stringToObjects(jsonString, RoleJsonObject.class));
        Assert.assertTrue(RoleDao.getInstance().getAll().size() == JsonObjectHelper.stringToObjects(jsonString, RoleJsonObject.class).size());
    }

    @Test(description = "POST. Role controller create user role -> /role")
    static void createRoleTest() {
        Role role = RoleEntityHelper.createRoleEntity();
        RoleJsonObject jsonObject = RoleJsonHelper.mapEntityToJsonObject(role);
        String jsonString = JsonObjectHelper.generateObjectToJsonString(jsonObject);
        RoleJsonObject actualResultJson = HttpHelper.postMethod(REQUEST_URL, CREATE_ROLE, JSON, JSON, jsonString, 201, RoleJsonObject.class);
        jsonObject.setId(actualResultJson.getId());
        RoleDao.getInstance().deleteRole(actualResultJson.getId());
        Assert.assertEquals(actualResultJson, jsonObject);
    }

    @Test(description = "GET. Role controller get user role by id -> /role/{id}")
    static void getRoleByIdTest() {
        Role role = RoleEntityHelper.createRoleEntity();
        id = RoleEntityHelper.saveRoleInDataBaseAndGetId(role);
        role.setId(id);
        RoleJsonObject expectedResult = RoleJsonHelper.mapEntityToJsonObject(role);
        RoleJsonObject actualResult = HttpHelper.getMethodByPath(REQUEST_URL, GET_ROLE_BY_ID, "id", JSON, id, 200, RoleJsonObject.class);
        RoleDao.getInstance().deleteRole(id);
        Assert.assertEquals(actualResult, expectedResult);
    }

    //TODO BUG
    @Test(description = "PUT. Role controller update user role -> /role/{id}")
    static void updateRoleTest() {
        Role role = RoleEntityHelper.createRoleEntity();
        id = RoleEntityHelper.saveRoleInDataBaseAndGetId(role);
        role = RoleEntityHelper.updateRole(role);
        RoleJsonObject expectedResult = RoleJsonHelper.mapEntityToJsonObject(role);
        String requestBody = JsonObjectHelper.generateObjectToJsonString(expectedResult);
        RoleJsonObject actualResult = HttpHelper.putMethodUpdateById(REQUEST_URL, UPDATE_ROLE_BY_ID, requestBody, "id", JSON, id, 200, RoleJsonObject.class);
        RoleDao.getInstance().deleteRole(id);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(description = "DELETE. Role controller delete user role by id -> /role/{id}")
    static void deleteRoleTest() {
        Role role = RoleEntityHelper.createRoleEntity();
        id = RoleEntityHelper.saveRoleInDataBaseAndGetId(role);
        HttpHelper.deleteMethod(REQUEST_URL, DELETE_ROLE_BY_ID, "id", id, 200);
        Assert.assertNull(RoleEntityHelper.getRoleFromDataBaseById(id));
    }
}
