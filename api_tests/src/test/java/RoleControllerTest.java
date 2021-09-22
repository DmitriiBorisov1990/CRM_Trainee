import dao.RoleDao;
import entity.Role;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Authorization;
import utils.HttpHelper;
import utils.JsonObjectHelper;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

import static io.restassured.RestAssured.given;

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

    //TODO
    @Test
    static void getAllRoleTest() {
        given()
                .headers(HttpHelper.requestHeaderFieldPutKey("Authorization", "CRM_HA " + Authorization.JSESSIONID))
                .spec(HttpHelper.setRequestSpec(REQUEST_URL, ContentType.JSON, GET_ALL_ROLE_PATH))
                .then().log().body().statusCode(200);
        System.out.println(RoleDao.getInstance().getAll());
    }

    @Test(description = "POST. Role controller create role -> /role")
    static void createRoleTest() {

        Role entity = Role.builder().roleName("LAZY_PERSON").descriptionRu("Лентяй").descriptionEn("lazy person").build();
        String jsonString = JsonObjectHelper.generateObjectToJson(entity);
        Role objectResponse = given()
                .spec(HttpHelper.setRequestSpec(REQUEST_URL, ContentType.JSON, CREATE_ROLE))
                .body(jsonString)
                .when()
                .post()
                .then().statusCode(201)
                .extract()
                .as(Role.class);
        entity.setId(objectResponse.getId());
        System.out.println(entity + "\n" + objectResponse);
        RoleDao.getInstance().deleteRole(objectResponse.getId());
        Assert.assertTrue(entity.equals(objectResponse));
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
                .spec(HttpHelper.setRequestSpec(REQUEST_URL, ContentType.JSON, GET_ROLE_BY_ID))
                .pathParam("id", roleOptional.getId())
                .when()
                .get()
                .then()
                .spec(HttpHelper.setResponseSpec(ContentType.JSON))
                .extract()
                .asString();
        Class<Role> roleClass = Role.class;
        Role controllerResponse = JsonObjectHelper.generateJsonToObject(responseJsonAsString, roleClass);
        RoleDao.getInstance().deleteRole(roleOptional.getId());
        Assert.assertTrue(entity.equals(controllerResponse));
    }

    @Test(description = "DELETE.DRole controller get role by id -> /role/{id}")
    static void deleteRoleTest() {
        Role entity = Role.builder()
                .roleName("QA")
                .descriptionRu("Тестировщик ПО")
                .descriptionEn("QA manual")
                .build();
        System.out.println(entity);
        Role roleInDataBase = RoleDao.getInstance().saveRole(entity);
        entity.setId(roleInDataBase.getId());
        System.out.println(entity);
        given()
                .spec(HttpHelper.setRequestSpec(REQUEST_URL, ContentType.JSON, GET_ROLE_BY_ID))
                .pathParam("id", roleInDataBase.getId())
                .when()
                .delete().then().statusCode(200);
        Optional<Role> optionalRole = RoleDao.getInstance().getOne(roleInDataBase.getId());
        Assert.assertTrue(optionalRole.isEmpty());
    }

    @Test(description = "PUT. Role controller update user role -> /role/{id}")
    static void updateRoleTest() {

        Role entity = Role.builder()
                .roleName("AQA")
                .descriptionRu("Автоматизатор ПО")
                .descriptionEn("AQA engineer")
                .build();
        Role roleInDataBase = RoleDao.getInstance().saveRole(entity);
        entity.setId(roleInDataBase.getId());
        // System.out.println(entity);
        entity = stringToUpperCase(entity);
        String requestBody = JsonObjectHelper.generateObjectToJson(entity);
        //  System.out.println(requestBody);
        Role roleAfterUpdate = given()
                .spec(HttpHelper.setRequestSpec(REQUEST_URL, ContentType.JSON, GET_ROLE_BY_ID))
                .pathParam("id", roleInDataBase.getId())
                .body(requestBody)
                .when()
                .put()
                .then().statusCode(200)
                .extract()
                .as(Role.class);
        // получить role по id  из db
        Optional<Role> roleOptional = RoleDao.getInstance().getOne(roleInDataBase.getId());
        System.out.println(roleOptional + "\n" + entity + "\n" + roleAfterUpdate);
        Assert.assertTrue(entity.equals(roleAfterUpdate));
    }

    @SneakyThrows
    private static void loadDriver() {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public static <T> UnaryOperator<T> peek(Consumer<T> consumer) {
        return object -> {
            consumer.accept(object);
            return object;
        };
    }

    private static Role stringToUpperCase(Role entity) {
        Role updateEntity = Role.builder()
                .id(entity.getId())
                .roleName(entity.getRoleName().toUpperCase())
                .descriptionRu(entity.getDescriptionRu().toUpperCase())
                .descriptionEn(entity.getDescriptionEn().toUpperCase())
                .build();
        return updateEntity;
    }
}
