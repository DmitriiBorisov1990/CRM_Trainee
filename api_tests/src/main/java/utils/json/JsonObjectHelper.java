package utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor
public final class JsonObjectHelper {

    @SneakyThrows
    public static <T> String generateObjectToJson(T object) {
        return new ObjectMapper().writeValueAsString(object);
    }
}
