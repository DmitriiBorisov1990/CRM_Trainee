package dao;

import entity.Country;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import transaction.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CountryDao {

    private static final CountryDao INSTANCE = new CountryDao();
    private static final String GET_BY_ID = "SELECT * " + "FROM country " + "WHERE id = ?";

    @SneakyThrows
    public Optional<Country> getOne(Integer id) {
        Country country = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                country = Country.builder()
                        .id(resultSet.getInt("id"))
                        .countryCode2(resultSet.getString("country_code_2"))
                        .countryCode3(resultSet.getString("country_code_3"))
                        .countryNameRu(resultSet.getString("country_name_ru"))
                        .countryNameEn(resultSet.getString("country_name_en"))
                        .build();
            }
        }
        return Optional.ofNullable(country);
    }

    public static CountryDao getInstance() {
        return INSTANCE;
    }
}
