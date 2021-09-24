import dao.RoleDao;
import entity.Role;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Authorization;
import utils.HttpHelper;
import utils.JsonObjectHelper;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class RoleControllerTest {

    private static final String CREATE_ROLE = "/api/role";
    private static final String GET_ALL_ROLE_PATH = "/api/role";
    private static final String GET_ROLE_BY_ID = "/api/role/{id}";
    private static final String REQUEST_URL = "http://10.10.15.160:8080";

    @BeforeTest
    void authorization() {
        Authorization.login();
        loadDriver();
    }

    @Test
    static void getAllRoleTest() {
        String jsonString = given()
                .spec(HttpHelper.setRequestSpec(REQUEST_URL, JSON, GET_ALL_ROLE_PATH))
                .when()
                .get()
                .then()
                .spec(HttpHelper.setResponseSpec(JSON,200))
                .extract()
                .body().asPrettyString();
        Assert.assertEquals(RoleDao.getInstance().getAll(),JsonObjectHelper.stringToObjects(jsonString,Role.class));
    }

    @Test(description = "POST. Role controller create role -> /role")
    static void createRoleTest() {
        Role entity = Role.builder().roleName("LAZY_PERSON").descriptionRu("Лентяй").descriptionEn("lazy person").build();
        String jsonString = JsonObjectHelper.generateObjectToJson(entity);
        Role objectResponse = given()
                .spec(HttpHelper.setRequestSpec(REQUEST_URL, JSON, CREATE_ROLE))
                .body(jsonString)
                .when()
                .post()
                .then()
                .spec(HttpHelper.setResponseSpec(JSON,201))
                .extract()
                .as(Role.class);
        entity.setId(objectResponse.getId());
        RoleDao.getInstance().deleteRole(objectResponse.getId());
        Assert.assertEquals(objectResponse, entity);
    }

    @Test(description = "GET. Role controller get role by id -> /role/{id}")
    static void getRoleByIdTest() {
        Role entity = Role.builder()
                .roleName("AUTOMATION_TESTING")
                .descriptionRu("Автоматизация тестирования")
                .descriptionEn("Automation test")
                .build();
        Role roleOptional = RoleDao.getInstance().saveRole(entity);
        String responseJsonAsString = given()
                .spec(HttpHelper.setRequestSpec(REQUEST_URL, JSON, GET_ROLE_BY_ID))
                .pathParam("id", roleOptional.getId())
                .when()
                .get()
                .then()
                .spec(HttpHelper.setResponseSpec(JSON,200))
                .extract()
                .asString();
        Class<Role> roleClass = Role.class;
        Role controllerResponse = JsonObjectHelper.generateJsonToObject(responseJsonAsString, roleClass);
        RoleDao.getInstance().deleteRole(roleOptional.getId());
        Assert.assertEquals(controllerResponse, entity);
    }

    @Test(description = "DELETE.DRole controller get role by id -> /role/{id}")
    static void deleteRoleTest() {
        Role entity = Role.builder()
                .roleName("QA")
                .descriptionRu("Тестировщик ПО")
                .descriptionEn("QA manual")
                .build();
        Role roleInDataBase = RoleDao.getInstance().saveRole(entity);
        entity.setId(roleInDataBase.getId());
        given()
                .spec(HttpHelper.setRequestSpec(REQUEST_URL, JSON, GET_ROLE_BY_ID))
                .pathParam("id", roleInDataBase.getId())
                .when()
                .delete()
                .then()
                .statusCode(200);
        Optional<Role> optionalRole = RoleDao.getInstance().getOne(roleInDataBase.getId());
        Assert.assertTrue(optionalRole.isEmpty());
    }

    @Test(description = "PUT. Role controller update user role -> /role/{id}")
    static void updateRoleTest() {
        Role entity = Role.builder()
                .roleName("AQa")
                .descriptionRu("Автоматизатор ПО")
                .descriptionEn("AQA engineer")
                .build();
        Role roleInDataBase = RoleDao.getInstance().saveRole(entity);
        entity.setId(roleInDataBase.getId());
        entity = stringToUpperCase(entity);
        String requestBody = JsonObjectHelper.generateObjectToJson(entity);
        Role roleAfterUpdate = given()
                .spec(HttpHelper.setRequestSpec(REQUEST_URL, JSON, GET_ROLE_BY_ID))
                .pathParam("id", roleInDataBase.getId())
                .body(requestBody)
                .when()
                .put()
                .then()
                .spec(HttpHelper.setResponseSpec(JSON,200))
                .extract()
                .as(Role.class);
        RoleDao.getInstance().deleteRole(roleInDataBase.getId());
        Assert.assertEquals(roleAfterUpdate, entity);
    }

    @SneakyThrows
    private static void loadDriver() {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    private static Role stringToUpperCase(Role entity) {
        return Role.builder()
                .id(entity.getId())
                .roleName(entity.getRoleName().toUpperCase())
                .descriptionRu(entity.getDescriptionRu().toUpperCase())
                .descriptionEn(entity.getDescriptionEn().toUpperCase())
                .build();
    }
}
