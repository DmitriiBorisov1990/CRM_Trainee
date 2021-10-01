package utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class JsonObjectHelper {

    @SneakyThrows
    public static <T> String generateObjectToJsonString(T object) {
        return new ObjectMapper().writeValueAsString(object);
    }

    @SneakyThrows
    public static <T> T generateJsonToObject(String jsonString, Class<T> t) {
        return new ObjectMapper().readValue(jsonString, t);
    }

    //TODO
    /*@SneakyThrows
    public static <T> List<T> generateJsonToListObjects(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<T> t = objectMapper.readValue(jsonString, new TypeReference<>() {});
        return t;
    }*/

    @SneakyThrows
    public static <T> List<T> stringToObjects(String str, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, false);
        return mapper.readValue(str, mapper.getTypeFactory().constructCollectionType(List.class, type));
    }
}
