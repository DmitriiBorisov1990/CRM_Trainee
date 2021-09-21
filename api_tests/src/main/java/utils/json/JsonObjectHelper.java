package utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonObjectHelper {

    @SneakyThrows
    public static <T> String generateObjectToJson(T object) {
        return new ObjectMapper().writeValueAsString(object);
    }

    @SneakyThrows
    public static <T> T generateJsonToObject(String jsonString, Class<T> t) {
        return new ObjectMapper().readValue(jsonString, t);
    }
}
