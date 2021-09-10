package lesson;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTest {
private static final Logger logger = LogManager.getLogger(ApiTest.class);

    /*@Test
    public static void firstTest(){
        logger.info("INFO");
        logger.debug("DEBUG");
        logger.error("ERROR");
        logger.fatal("FATAL");
        logger.warn("WARN");
    }*/

    @Test
    public static void test_01(){
       Response response = get("https://reqres.in/api/users?page=2");
        System.out.println(response.getHeader("content-type"));
        //System.out.println(response.getTime());
        System.out.println(response.getBody().prettyPrint());
        //System.out.println(response.asString());
        System.out.println(response.getStatusCode());
    }

    @Test
    public static void test_02(){
        System.out.println(given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200).extract().asPrettyString());
    }

    //https://crm-trainee-react-dev.andersenlab.dev/login
    @Test
    public static void test(){
        given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data.id[0]",equalTo(7))
                .log()
                .all();
    }
}
