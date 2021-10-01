package dao;

import entity.City;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import transaction.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CityDao {

    private static final CityDao INSTANCE = new CityDao();
    private static final String GET_ALL = "SELECT * FROM city";
    private static final String DELETE = "DELETE FROM city WHERE id = ?";
    private static final String GET_CITY_BY_ID = "SELECT* FROM city WHERE id = ?";
    private static final String SAVE_CITY = "INSERT INTO city(post_index, country_id, city_name_ru, city_name_en, visibility) VALUES(?,?,?,?,?)";

    //TODO
    @SneakyThrows
    public City getOne(int id) {
        City city = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CITY_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                city = City.builder()
                        .id(resultSet.getInt("id"))
                        .postIndex(resultSet.getString("post_index"))
                        .countryId(resultSet.getInt("country_id"))
                        .cityNameRu(resultSet.getString("city_name_ru"))
                        .cityNameEn(resultSet.getString("city_name_en"))
                        .visibility(resultSet.getBoolean("visibility"))
                        .build();
            }
        }
        return city;
    }

    @SneakyThrows
    public List<City> getAll() {
        List<City> cityList = new ArrayList<>();
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                City city = City.builder()
                        .id(resultSet.getInt("id"))
                        .postIndex(resultSet.getString("post_index"))
                        .countryId(resultSet.getInt("country_id"))
                        .cityNameRu(resultSet.getString("city_name_ru"))
                        .cityNameEn(resultSet.getString("city_name_en"))
                        .visibility(resultSet.getBoolean("visibility"))
                        .build();
                cityList.add(city);
            }
        }
        return cityList;
    }

    @SneakyThrows
    public City saveCity(City entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_CITY, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getPostIndex());
            preparedStatement.setInt(2, entity.getCountryId());
            preparedStatement.setString(3, entity.getCityNameRu());
            preparedStatement.setString(4, entity.getCityNameEn());
            preparedStatement.setBoolean(5, entity.getVisibility());
            if (preparedStatement.executeUpdate() == 1) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                }
            }
        }
        return entity;
    }

    @SneakyThrows
    public boolean deleteCity(int id) {
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

    public static CityDao getInstance() {
        return INSTANCE;
    }
}
