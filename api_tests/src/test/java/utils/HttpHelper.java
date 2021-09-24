package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class HttpHelper {

    public static Map<String, String> requestHeaderFieldPutKey(String key, String value) {
        Map<String, String> result = new HashMap<>();
        result.put(key, value);
        return result;
    }

    public static ResponseSpecification setResponseSpec(ContentType contentType,int statusCode) {
        return new ResponseSpecBuilder().expectStatusCode(statusCode).expectContentType(contentType).build();
    }

    public static RequestSpecification setRequestSpec(String baseUri, ContentType contentType, String basePath) {
        return new RequestSpecBuilder()
                .addHeaders(requestHeaderFieldPutKey("Authorization", "CRM_HA " + Authorization.JSESSIONID))
                .setBaseUri(baseUri)
                .setContentType(contentType)
                .setBasePath(basePath)
                .build();
    }
}
