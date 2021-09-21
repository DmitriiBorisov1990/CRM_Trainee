package dao;

import entity.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import transaction.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleDao {

    private static final String GET_ALL = "SELECT* FROM user_role";
    private static final String DELETE = "DELETE FROM user_role WHERE id = ?";
    private static final String FIND_ONE = "SELECT* " + " FROM user_role " + " WHERE id = ?";
    private static final String SAVE = "INSERT INTO user_role (role_name,description_ru,description_en)" + "VALUES(?,?,?)";
    private static final String UPDATE_ROLE = "UPDATE user_role SET role_name = ?, description_ru = ?, description_en = ?" + " WHERE id = ?";
    private static final RoleDao INSTANCE = new RoleDao();

    /**
     * @param id search Role by id
     * @return Optional role
     * @Description swagger /role Get all user roles;
     **/
    @SneakyThrows
    public Optional<Role> getOne(int id) {
        Role role = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role = Role.builder()
                        .id(resultSet.getInt("id"))
                        .roleName(resultSet.getString("role_name"))
                        .descriptionRu(resultSet.getString("description_ru"))
                        .descriptionEn(resultSet.getString("description_en"))
                        .build();
            }
        }
        return Optional.ofNullable(role);
    }

    @SneakyThrows
    public List<Role> getAll(){
        List<Role> roleList = new ArrayList<>();
        try (Connection connection = ConnectionManager.get();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
               Role role = Role.builder().id(resultSet.getInt("id"))
                       .roleName(resultSet.getString("role_name"))
                       .descriptionRu(resultSet.getString("description_ru"))
                       .descriptionEn(resultSet.getString("description_en"))
                       .build();
                roleList.add(role);
            }
        }
        return roleList;
    }

    @SneakyThrows
    public Role saveRole(Role role) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setString(2, role.getDescriptionRu());
            preparedStatement.setString(3, role.getDescriptionEn());
            if (preparedStatement.executeUpdate() == 1) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    role.setId(generatedKeys.getInt(1));
                }
            }
        }
        return role;
    }

    /**
     * @param role
     * @return Role object after update
     **/
    @SneakyThrows
    public Role update(Role role) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROLE)) {
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setString(2, role.getDescriptionRu());
            preparedStatement.setString(3, role.getDescriptionEn());
            preparedStatement.setInt(4, role.getId());
            preparedStatement.executeUpdate();
        }
        return role;
    }

    @SneakyThrows
    public boolean deleteRole(int id) {
        boolean result = false;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    /**
     * @return instance UserRoleDao
     * @Noparam method
     */
    public static RoleDao getInstance() {
        return INSTANCE;
    }
}
