package utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.user_controller.UserControllerResponse;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor
public final class JsonObjectHelper {

    @SneakyThrows
    public static <T> String generateObjectToJson(T object) {
        return new ObjectMapper().writeValueAsString(object);
    }

    @SneakyThrows
    public static UserControllerResponse generateJsonToObject(String jsonString, Class<UserControllerResponse> object){
        return new ObjectMapper().readValue(jsonString, UserControllerResponse.class);
    }
}
