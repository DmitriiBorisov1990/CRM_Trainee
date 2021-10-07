import controllers.role_controller.RoleJsonObject;
import dao.RoleDao;
import entity.Role;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.HttpHelper;
import utils.JsonObjectHelper;
import utils.role.RoleEntityHelper;
import utils.role.RoleJsonHelper;

import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.http.ContentType.JSON;

public class RoleControllerTest extends BaseTest {

    private static RoleDao roleDao = RoleDao.getInstance();
    private static final String CREATE_ROLE = "/api/role";
    private static final String GET_ALL_ROLE = "/api/role";
    private static final String GET_ROLE_BY_ID = "/api/role/{id}";
    private static final String UPDATE_ROLE_BY_ID = "/api/role/{id}";
    private static final String DELETE_ROLE_BY_ID = "/api/role/{id}";

    @Test(description = "GET. Role controller get all user roles -> /role")
    static void getAllRoleTest() {
        Set<Integer> roleIdFromDataBase = roleDao.getAll()
                .stream()
                .map(Role::getId)
                .collect(Collectors.toSet());
        Set<Integer> roleIdFromJsonResponse = JsonObjectHelper
                .stringToObjects(HttpHelper.getMethodGetAll(REQUEST_URL, GET_ALL_ROLE, JSON, 200),RoleJsonObject.class)
                .stream()
                .map(RoleJsonObject::getId)
                .collect(Collectors.toSet());
        Assert.assertTrue(roleIdFromDataBase.containsAll(roleIdFromJsonResponse));
    }

    @Test(description = "POST. Role controller create user role -> /role")
    static void createRoleTest() {
        RoleJsonObject jsonObject = RoleJsonHelper.createJsonObject();
        RoleJsonObject actualResultJson = HttpHelper.postMethod(REQUEST_URL, CREATE_ROLE, JSON, JSON, JsonObjectHelper.generateObjectToJsonString(jsonObject), 201, RoleJsonObject.class);
        jsonObject.setId(actualResultJson.getId());
        roleDao.deleteRole(actualResultJson.getId());
        Assert.assertEquals(actualResultJson, jsonObject);
    }

    @Test(description = "GET. Role controller get user role by id -> /role/{id}")
    static void getRoleByIdTest() {
        id = RoleEntityHelper.saveRoleInDataBaseAndGetId();
        RoleJsonObject expectedResult = RoleJsonHelper.createJsonObject();
        expectedResult.setId(id);
        RoleJsonObject actualResult = HttpHelper.getMethodByPath(REQUEST_URL, GET_ROLE_BY_ID, "id", JSON, id, 200, RoleJsonObject.class);
        roleDao.deleteRole(id);
        Assert.assertEquals(actualResult, expectedResult);
    }

    //TODO BUG
    @Test(description = "PUT. Role controller update user role -> /role/{id}")
    static void updateRoleTest() {
        id = RoleEntityHelper.saveRoleInDataBaseAndGetId();
        RoleJsonObject expectedResult = RoleJsonHelper.updateRole();
        expectedResult.setId(id);
        String requestBody = JsonObjectHelper.generateObjectToJsonString(expectedResult);
        RoleJsonObject actualResult = HttpHelper.putMethodUpdateById(REQUEST_URL, UPDATE_ROLE_BY_ID, requestBody, "id", JSON, id, 200, RoleJsonObject.class);
        RoleDao.getInstance().deleteRole(id);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(description = "DELETE. Role controller delete user role by id -> /role/{id}")
    static void deleteRoleTest() {
        id = RoleEntityHelper.saveRoleInDataBaseAndGetId();
        HttpHelper.deleteMethod(REQUEST_URL, DELETE_ROLE_BY_ID, "id", id, 200);
        Assert.assertNull(RoleEntityHelper.getRoleFromDataBaseById(id));
    }
}
