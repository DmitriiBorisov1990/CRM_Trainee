package lesson;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Test_POST {

    @lombok.SneakyThrows
    @Test
    public void test_1() {
        Map<String,Object> map = new HashMap<>();
        map.put("name","Dmitrii");
        map.put("job","AQA");

        ObjectMapper objectMapper = new ObjectMapper();
        String request = objectMapper.writeValueAsString(map);
        System.out.println(request);

        RestAssured.given()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when()
                    .put("https://reqres.in/api/users/2")
                .then().statusCode(200).log().all();
    }
}
