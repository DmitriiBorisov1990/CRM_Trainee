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

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CountryDao {

    private static final CountryDao INSTANCE = new CountryDao();
    private static final String DELETE = "DELETE FROM country WHERE id = ?";
    private static final String GET_BY_ID = "SELECT * " + "FROM country " + "WHERE id = ?";
    private static final String SAVE = "INSERT INTO country(country_code_2,country_code_3,country_name_ru,country_name_en) VALUES(?,?,?,?)";

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
                        .visibility(resultSet.getBoolean("visibility"))
                        .build();
            }
        }
        return Optional.ofNullable(country);
    }

    @SneakyThrows
    public Country saveCountry(Country country) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, country.getCountryCode2());
            preparedStatement.setString(2, country.getCountryCode3());
            preparedStatement.setString(3, country.getCountryNameRu());
            preparedStatement.setString(4, country.getCountryNameEn());
            if (preparedStatement.executeUpdate() == 1) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    country.setId(generatedKeys.getInt(1));
                }
            }
        }
        return country;
    }

    @SneakyThrows
    public boolean deleteCountry(int id) {
        boolean result = false;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1,id);
            if(preparedStatement.executeUpdate() == 1){
                result = true;
            }
        }
        return result;
    }

    public static CountryDao getInstance() {
        return INSTANCE;
    }
}
