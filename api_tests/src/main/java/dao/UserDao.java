package dao;

import entity.Office;
import entity.Role;
import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();
    private static final String FIND_ONE = "SELECT *" + "FROM user " + "WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE" + " FROM user " + "WHERE id = ?";
    private static final String SAVE = "INSERT INTO user" +
            "(first_name_ru,last_name_ru,first_name_en,last_name_en,birthday,skype,corporate_email,phone,role_id,office_id)" +
            "VALUES(?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE user " +
            "SET first_name_ru = ?,last_name_ru = ?,first_name_en = ?,last_name_en = ?,birthday = ?,skype = ?,corporate_email = ?,phone = ?,role_id = ?,office_id = ? " +
            "WHERE id = ?";


    /**
     * @param id search User by id
     * @return Optional user
     * @Description swagger  /users/{id} Get user by id;
     **/
    @SneakyThrows
    public Optional<User> getOne(int id) {
        User user = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getInt("id"))
                        .firstNameRu(resultSet.getString("first_name_ru"))
                        .lastNameRu(resultSet.getString("last_name_ru"))
                        .firstNameEn(resultSet.getString("first_name_en"))
                        .lastNameEn(resultSet.getString("last_name_en"))
                        .birthday(transformDate(resultSet.getDate("birthday")))
                        .skype(resultSet.getString("skype"))
                        .corporateEmail(resultSet.getString("corporate_email"))
                        .phone(resultSet.getString("phone"))
                        .status(resultSet.getString("status"))
                        .role(Role.builder()
                                .id(resultSet.getInt("role_id"))
                                .build())
                        .createDate(resultSet.getDate("created_date"))
                        .office(Office.builder()
                                .id(resultSet.getInt("office_id"))
                                .build())
                        .build();
            }
        }
        return Optional.ofNullable(user);
    }

    /**
     * @param user create User
     * @return User Object
     **/
    @SneakyThrows
    public User saveUser(User user) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstNameRu());
            preparedStatement.setString(2, user.getLastNameRu());
            preparedStatement.setString(3, user.getFirstNameEn());
            preparedStatement.setString(4, user.getLastNameEn());
            preparedStatement.setDate(5, Date.valueOf(user.getBirthday()));
            preparedStatement.setString(6, user.getSkype());
            preparedStatement.setString(7, user.getCorporateEmail());
            preparedStatement.setString(8, user.getPhone());
            preparedStatement.setObject(9, Optional
                    .ofNullable(user.getRole()).map(Role::getId).orElse(null));
            preparedStatement.setObject(10, Optional.ofNullable(user.getOffice()).map(Office::getId).orElse(null));
            if (preparedStatement.executeUpdate() == 1) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }
        }
        return user;
    }

    /**
     * @param id delete User by id
     * @return boolean result
     **/
    @SneakyThrows
    public boolean deleteUser(int id) {
        boolean result = false;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    /**
     * @param user
     * @return User object after update
     **/
    @SneakyThrows
    public User update(User user) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, user.getFirstNameRu());
            preparedStatement.setString(2, user.getLastNameRu());
            preparedStatement.setString(3, user.getFirstNameEn());
            preparedStatement.setString(4, user.getLastNameEn());
            preparedStatement.setDate(5, Date.valueOf(user.getBirthday()));
            preparedStatement.setString(6, user.getSkype());
            preparedStatement.setString(7, user.getCorporateEmail());
            preparedStatement.setString(8, user.getPhone());
            preparedStatement.setObject(9, Optional
                    .ofNullable(user.getRole()).map(Role::getId).orElse(null));
            preparedStatement.setObject(10, Optional.ofNullable(user.getOffice()).map(Office::getId).orElse(null));
            preparedStatement.setInt(11, user.getId());
            preparedStatement.executeUpdate();
        }
        return user;
    }

    private LocalDate transformDate(Date date) {
        return date.toLocalDate();
    }

    /**
     * @return instance UserDao
     * @Noparam method
     */
    public static UserDao getInstance() {
        return INSTANCE;
    }
}

