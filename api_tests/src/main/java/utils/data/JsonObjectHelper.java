package utils.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import utils.api.User;

public class JsonObjectHelper {

    @SneakyThrows
    public static String generateJsonForLogin(){
        ObjectMapper objectMapper = new ObjectMapper();
        User user = User
                .builder()
                .email("huntflow-test-16@andersenlab.com")
                .password("159753CFThn")
                .build();
        return objectMapper
                .writeValueAsString(user);
    }
}
