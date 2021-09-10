package data_base;

import java.sql.*;

public class DataBase {
    private static final String URL = "jdbc:mysql://10.10.15.45:3306/dev_crmreq_t";
    private static final String USER = "crmreqtdev_u";
    private static final String PASSWORD = "xmsLxaE6ar7v6Rk";
    private static final String GET_ALL_CITY =
            "SELECT * " +
                    "FROM user " +
                    "WHERE corporate_email = 'd.barysau@andersenlab.com'";

    public static void main(String[] args) throws SQLException {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(GET_ALL_CITY);
                while (resultSet.next()) {
                    String cityName = resultSet.getString("first_name_ru");
                    System.out.println(cityName);
                }
            }
        }
    }
    //TODO
    /*@SneakyThrows
    private static void loadDriver() {
        Class.forName("com.mysql.jdbc.Driver");
    }*/
}
