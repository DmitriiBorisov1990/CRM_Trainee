import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.api.Authorization;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CrmTestLogin {

    @BeforeTest
    static void start(){
        Authorization.login();
    }

    @Test
    static void getUserById() {

        ValidatableResponse response;
        Map<String,String> map = new HashMap<>();
        map.put("Authorization","CRM_HA "+ Authorization.JSESSIONID);
        given()
                .header("Content-type", ContentType.JSON)
                .headers(map)
                .accept(ContentType.JSON)
                .get("http://10.10.15.160:8080/api/users/70000")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstNameRu",equalTo("Пользователь"));
    }
}
