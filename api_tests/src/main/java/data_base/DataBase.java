package data_base;

import lombok.SneakyThrows;
import utils.ConnectionManager;

import java.sql.*;

public class DataBase {

    private static final String GET_USER_BY_ID = "SELECT *" + "FROM user " + "WHERE id = ?";
    private static final String GET_ALL_USERS = "SELECT* FROM user";
    private static final String CREATE_USER = "INSERT INTO user (first_name_ru,last_name_ru,first_name_en,last_name_en,corporate_email)" +
            "VALUES(?,?,?,?,?)";

    public static void main(String[] args) throws SQLException {
        getUserBiId(createUser("Оззи", "Осборн", "Ozzy", "Osbourne", "o.osbourne@andersenlab.com"));
        //getUserBiId(70173);
    }

    @SneakyThrows
    static int createUser(String firstNameRu, String lastNameRu, String firstNameEn, String lastNameEn, String corporateEmail) {
        int id = 0;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, firstNameRu);
            preparedStatement.setString(2, lastNameRu);
            preparedStatement.setString(3, firstNameEn);
            preparedStatement.setString(4, lastNameEn);
            preparedStatement.setString(5, corporateEmail);
            //preparedStatement.setString(5,strings[4]);
            //preparedStatement.setString(6,strings[5]);
            int insertedRows = preparedStatement.executeUpdate();
            if (insertedRows != 1) {
                throw new SQLException();
            }
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
        }
        return id;
    }

    @SneakyThrows
    static void getUserBiId(int id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4));
            }
        }
    }

    @SneakyThrows
    static PreparedStatement preparedStatementObject(String query) {
        try (Connection connection = ConnectionManager.get()) {
            return connection.prepareStatement(query);
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
