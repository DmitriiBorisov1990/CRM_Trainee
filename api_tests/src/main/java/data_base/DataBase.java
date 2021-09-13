package data_base;

import lombok.SneakyThrows;
import utils.ConnectionManager;

import java.sql.*;

public class DataBase {

    private static final String GET_USER_BY_ID = "SELECT *" + "FROM user " + "WHERE id = '?'";
    private static final String GET_ALL_USERS = "SELECT* FROM user";

    public static void main(String[] args) throws SQLException {
        loadDriver();
        /*try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)) {
        }*/
        getUserBiId(70000);
    }

    static void getAllUsers() {
    }

    static void createUser() {
    }

    @SneakyThrows
    static void getUserBiId(Integer id) {
        try (Connection connection = ConnectionManager.get();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery(GET_USER_BY_ID);
            while (resultSet.next()){
                String userFirstNameRu = resultSet.getString("first_name_en");
                System.out.println(userFirstNameRu);
            }
        }
    }

    static void updateUser() {
    }

    static void deleteUser() {
    }

    static void getUserRoleByUserId() {
    }

    static void getCurrentUser() {
    }

    static void getAllExternalUsers() {
    }

    static void getIdApUsers() {
    }

    @SneakyThrows
    private static void loadDriver() {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
}
