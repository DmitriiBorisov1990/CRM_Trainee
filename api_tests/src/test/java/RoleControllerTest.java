import dao.RoleDao;
import entity.Role;
import io.restassured.http.ContentType;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.HttpHelper;
import utils.api.Authorization;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

import static io.restassured.RestAssured.given;

public class RoleControllerTest {

    private static final String REQUEST_URL = "http://10.10.15.160:8080";
    private static final String GET_ALL_ROLE_PATH = "/api/role";
    private static final String GET_ROLE_BY_ID = "/role/{id}";

    @BeforeTest
    void authorization() {
        Authorization.login();
        loadDriver();
    }

    @Test
    static void getAllRoleTest() {

        given()
                .headers(HttpHelper.requestHeaderFieldPutKey("Authorization", "CRM_HA " + Authorization.JSESSIONID))
                .spec(HttpHelper.setRequestSpec(REQUEST_URL,ContentType.JSON,GET_ALL_ROLE_PATH))
                .then().log().body().statusCode(200);
        System.out.println(RoleDao.getInstance().getAll());
    }

    @Test
    static void createRoleTest() {
        Role role = Role.builder().roleName("LAZY_PERSON").descriptionRu("Лентяй").descriptionEn("lazy person").build();
        RoleDao.getInstance().saveRole(role);
        Optional<Role> newTestRole = RoleDao.getInstance().getOne(role.getId());
        System.out.println(newTestRole.toString());
        //create_role_in_swagger.go_to_db_and_check_it;
    }

    @Test
    static void getRoleByIdTest() {
        // -> преобразоавать json в строку
        // -> преобразовать строку в обьект
        Role entity = Role.builder().roleName("").build();
        Role roleOptional = RoleDao.getInstance().saveRole(entity); // -> сохранить role в базу данных
        String responseJsonAsString = given()
                .headers(HttpHelper.requestHeaderFieldPutKey("Authorization", "CRM_HA " + Authorization.JSESSIONID))
                .spec(HttpHelper.setRequestSpec(REQUEST_URL,ContentType.JSON,GET_ROLE_BY_ID))
                .pathParam("id",roleOptional.getId())
                .when()
                .get()
                .then()
                .spec(HttpHelper.setResponseSpec(ContentType.JSON))
                .extract()
                .asString();
    }

    @Test
    static void deleteRoleTest() {
        Assert.assertTrue(RoleDao.getInstance().deleteRole(1018));
    }

    @Test
    static void updateRoleTest() {
        RoleDao.getInstance()
                .getOne(1017)
                .map(peek(it -> it.setRoleName("TEST_ROLE"))).ifPresent(RoleDao.getInstance()::update);
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
}
