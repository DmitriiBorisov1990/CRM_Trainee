package data_base;

import lombok.SneakyThrows;
import utils.ConnectionManager;

import java.sql.*;

public class DataBase {

    private static final String GET_ALL_CITY =
            "SELECT * " +
                    "FROM user " +
                    "WHERE corporate_email = 'd.barysau@andersenlab.com'";

    public static void main(String[] args) throws SQLException {
        loadDriver();
        try (Connection connection = ConnectionManager.get()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(GET_ALL_CITY);
                while (resultSet.next()) {
                    String cityName = resultSet.getString("id");
                    System.out.println(cityName);
                }
            }
        }
    }

    @SneakyThrows
    private static void loadDriver() {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
}
