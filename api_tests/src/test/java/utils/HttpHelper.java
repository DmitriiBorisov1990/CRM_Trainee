package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@UtilityClass
public class HttpHelper {

    private static Map<String, String> requestHeaderFieldPutKey(String key, String value) {
        Map<String, String> result = new HashMap<>();
        result.put(key, value);
        return result;
    }

    private static ResponseSpecification setResponseSpec(ContentType contentType, int statusCode) {
        return new ResponseSpecBuilder().expectStatusCode(statusCode).expectContentType(contentType).build();
    }

    private static RequestSpecification setRequestSpec(String baseUri, ContentType contentType, String basePath) {
        return new RequestSpecBuilder()
                .addHeaders(requestHeaderFieldPutKey("Authorization", "CRM_HA " + Authorization.JSESSIONID))
                .setBaseUri(baseUri)
                .setContentType(contentType)
                .setBasePath(basePath)
                .build();
    }

    public static <T> int postMethod(String baseUrl, ContentType contentType, String uri, String jsonString, int responseStatusCode, Class<T> t, String setPathName) {
        return given()
                .spec(setRequestSpec(baseUrl, contentType, uri))
                .body(jsonString)
                .when()
                .post()
                .then()
                .spec(setResponseSpec(contentType, responseStatusCode))
                .extract()
                .path(setPathName);
    }

    public static String getMethodGetAll(String baseUrl, String uri, ContentType responseContentType, int statusCode) {
        return given()
                .spec(setRequestSpec(baseUrl, JSON, uri))
                .when()
                .get()
                .then()
                .spec(setResponseSpec(responseContentType, statusCode))
                .extract()
                .body()
                .asPrettyString();
    }

    public static <T> T postMethod(String baseUrl, String uri, ContentType requestContentType, ContentType responseContentType, String jsonString, int statusCode, Class<T> t) {
        return given()
                .spec(setRequestSpec(baseUrl, requestContentType, uri))
                .body(jsonString)
                .when()
                .post()
                .then()
                .spec(setResponseSpec(responseContentType, statusCode))
                .extract()
                .as(t);
    }

    public static <T> T getMethodByPath(String baseUrl, String uri, String pathKay, ContentType responseContentType, Object pathValue, int statusCode, Class<T> t) {
        return given()
                .spec(setRequestSpec(baseUrl, JSON, uri))
                .pathParam(pathKay, pathValue)
                .when()
                .get()
                .then()
                .spec(setResponseSpec(responseContentType, statusCode))
                .extract()
                .as(t);
    }

    public static <T> T putMethodUpdateById(String baseUrl, String uri, String requestBody, String pathKay, ContentType responseContentType, Object pathValue, int statusCode, Class<T> t) {
        return given()
                .spec(setRequestSpec(baseUrl, JSON, uri))
                .pathParam(pathKay, pathValue)
                .body(requestBody)
                .when()
                .put()
                .then()
                .spec(setResponseSpec(responseContentType, statusCode))
                .extract()
                .as(t);
    }

    public static void deleteMethod(String baseUrl, String uri, String pathKay, Object pathValue, int statusCode) {
        given()
                .spec(setRequestSpec(baseUrl, JSON, uri))
                .pathParam(pathKay, pathValue)
                .when()
                .delete()
                .then()
                .statusCode(statusCode);
    }
}
