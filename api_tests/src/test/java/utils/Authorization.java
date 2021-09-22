package utils;

import controllers.token_controller.TokenController;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.experimental.UtilityClass;
import org.testng.log4testng.Logger;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

@UtilityClass
public class Authorization {
    public static String JSESSIONID;
    private static final String PASSWORD = "159753CFThn";
    private static final String EMAIL = "huntflow-test-16@andersenlab.com";
    private static final String BASE_URI = "http://10.10.15.160:8080/api";
    static final Logger logger = Logger.getLogger(Authorization.class);

    public static void login() {

        RestAssured.baseURI = BASE_URI;
        TokenController createTokenController = new TokenController(PASSWORD, EMAIL);

        String userLoginJsonBody = JsonObjectHelper.generateObjectToJson(createTokenController);
        JSESSIONID = given()
                .header("Content-Type", ContentType.JSON)
                .body(userLoginJsonBody)
                .when()
                .post(baseURI + "/login")
                .then().statusCode(200).contentType(ContentType.JSON)
                .extract()
                .path("token");
        logger.info("\nAUTHORIZATION TOKEN: " + Authorization.JSESSIONID);
    }
}
