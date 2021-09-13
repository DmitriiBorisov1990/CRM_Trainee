package utils;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@UtilityClass
public class PropertiesManager {

    private static Properties properties = new Properties();

    static {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("api_tests/src/main/resources", "application.properties"))) {
            properties.load(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
