package utils.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.log4testng.Logger;
import utils.data.JsonObjectHelper;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Authorization {
    public static String JSESSIONID;
    private static final String BASE_URI = "http://10.10.15.160:8080/api";
    static final Logger logger = Logger.getLogger(Authorization.class);
    public static void login() {

        RestAssured.baseURI = BASE_URI;

        String loginDtoJSONBody = JsonObjectHelper.generateJsonForLogin();

        JSESSIONID = given()
                .header("Content-Type", ContentType.JSON)
                .body(loginDtoJSONBody)
                .when()
                .post(baseURI + "/login")
                .then().statusCode(200).contentType(ContentType.JSON)
                .extract()
                .path("token");
        System.out.printf("\nSESSION: " + JSESSIONID);
        logger.info("\nAUTHORIZATION TOKEN: " + Authorization.JSESSIONID);
    }
}
