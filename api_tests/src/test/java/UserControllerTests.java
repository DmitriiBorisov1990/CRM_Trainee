import controllers.user_controller.UserController;
import dao.UserDao;
import lombok.SneakyThrows;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Authorization;
import utils.JsonObjectHelper;

import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static utils.HttpHelper.requestHeaderFieldPutKey;

public class UserControllerTests {

    private static final String REQUEST_URL = "http://10.10.15.160:8080/api/users/";

    @BeforeTest
    void start() {
        Authorization.login();
        loadDriver();
    }

    @Test
    static void getUserById() {
        /*User testUser = UserDao.getInstance().saveUser(User
                .builder()
                .firstNameRu("Анна")
                .lastNameRu("Скиндерская")
                .firstNameEn("Hanna")
                .lastNameEn("Skinderskaia")
                .birthday(dateOfBirthday(1990, 9, 18))
                .skype(skypeNameCreator("Skinderskaia"))
                .corporateEmail(mailNameCreator("Hanna", "Skinderskaia") + TestData.ANDERSEN_DOMAIN_NAME_EMAIL)
                .phone("123-55-67")
                .role(Role.builder()
                        .id(1001)
                        .build())
                .office(Office.builder()
                        .id(2)
                        .build())
                .build());
        Optional<User> user = UserDao.getInstance().getOne(testUser.getId());
        System.out.println(user.toString());*/

        UserDao.getInstance()
                .getOne(70205)
                .map(peek(it ->it.setBirthday(dateOfBirthday(1990, 9, 17))))
                .ifPresent(UserDao.getInstance()::update);

        String jsonResponse = given()
                .header("Content-type", JSON)
                .headers(requestHeaderFieldPutKey("Authorization", "CRM_HA " + Authorization.JSESSIONID))
                .accept(JSON)
                .get(REQUEST_URL + "70205")
                .then().statusCode(200).contentType(JSON).extract().asString();
        UserController testUserObject = JsonObjectHelper.generateJsonToObject(jsonResponse, UserController.class);
        //System.out.println(testUserObject.toString() + "\n" + testUser);
        //Assert.assertTrue(UserDao.getInstance().deleteUser(70203));
    }

    private static LocalDate dateOfBirthday(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }

    private static String mailNameCreator(String firstNameEn, String lastNameEn) {
        return firstNameEn.toLowerCase().substring(0, 1) + "." + lastNameEn.toLowerCase();
    }

    private static String skypeNameCreator(String lastNameEn) {
        return "skype_" + lastNameEn.toLowerCase();
    }

    public static <T> UnaryOperator<T> peek(Consumer<T> consumer){
        return object -> {
            consumer.accept(object);
            return object;
        };
    }

    @SneakyThrows
    private static void loadDriver() {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
}
