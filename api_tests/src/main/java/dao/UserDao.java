package dao;

import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();
    private static final String FIND_ONE = "SELECT *" + "FROM user " + "WHERE id = ?";

    @SneakyThrows
    public Optional<User> getOne(int id){
        User user = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
               user = User.builder()
                        .id(resultSet.getInt("id"))
                        .firstNameRu(resultSet.getString("first_name_ru"))
                        .lastNameRu(resultSet.getString("last_name_ru"))
                        .firstNameEn(resultSet.getString("first_name_en"))
                        .lastNameEn(resultSet.getString("last_name_en"))
                        .birthday(resultSet.getDate("birthday"))
                        .skype(resultSet.getString("skype"))
                        .corporateEmail(resultSet.getString("corporate_email"))
                        .phone(resultSet.getString("phone"))
                        .status(resultSet.getString("status"))
                        /*.role(Role.builder()
                                .id(resultSet.getInt("role_id"))
                                .roleName(resultSet.getString("role_name"))
                                .descriptionRu(resultSet.getString("description_ru"))
                                .descriptionEn(resultSet.getString("description_en"))
                                .build())*/
                        .createDate(resultSet.getDate("created_date"))
                        /*.office(Office.builder()
                                .id(resultSet.getInt("office_id"))
                                .city(City.builder()
                                        .id(resultSet.getInt("city_id"))
                                        .postIndex()
                                        .build())
                                .build())*/
                        .build();
            }
        }
        return Optional.ofNullable(user);
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

    public static UserDao getInstance() {
        return INSTANCE;
    }
}

