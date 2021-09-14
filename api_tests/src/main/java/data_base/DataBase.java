package data_base;

import dao.UserDao;
import lombok.SneakyThrows;
import utils.ConnectionManager;

import java.sql.*;

public class DataBase {

    private static final String CREATE_USER = "INSERT INTO user (first_name_ru,last_name_ru,first_name_en,last_name_en,corporate_email)" +
            "VALUES(?,?,?,?,?)";

    public static void main(String[] args) throws SQLException {
        //createUser("Оззи", "Осборн", "Ozzy", "Osbourne", "o.osbourne@andersenlab.com"));
        loadDriver();
        UserDao.getInstance().getOne(70172).ifPresent(System.out::println);
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
    private static void loadDriver() {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
}
