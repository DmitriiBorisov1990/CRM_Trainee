package transaction;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;

@UtilityClass
public class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String USER_NAME = "db.user";
    private static final String PASSWORD = "db.password";

    @SneakyThrows
    public static Connection get(){
        return DriverManager.getConnection(
                PropertiesManager.get(URL_KEY),
                PropertiesManager.get(USER_NAME),
                PropertiesManager.get(PASSWORD));
    }
}
