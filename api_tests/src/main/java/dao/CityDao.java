package dao;

import entity.City;
import entity.Country;
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
    private static final CountryDao countryDao = CountryDao.getInstance();
    private static final String GET_ALL = "SELECT ci.id, ci.post_index, ci.city_name_ru, ci.city_name_en, ci.visibility, " +
            "co.id, co.country_code_2, co.country_code_3, co.country_name_ru, co.country_name_en, co.visibility " +
            "FROM city ci " +
            "JOIN country co " +
            "ON ci.country_id  = co.id";
    private static final String DELETE = "DELETE FROM city WHERE id = ?";
    private static final String GET_CITY_BY_ID = "SELECT ci.id, ci.post_index, ci.city_name_ru, ci.city_name_en, ci.visibility, " +
            "co.id, co.country_code_2, co.country_code_3, co.country_name_ru, co.country_name_en, co.visibility " +
            "FROM city ci " +
            "JOIN country co " +
            "ON ci.country_id  = co.id " +
            "WHERE ci.id = ?";
    private static final String SAVE_CITY = "INSERT INTO city(post_index, country_id, city_name_ru, city_name_en, visibility) VALUES(?,?,?,?,?)";
    private static final String GET_CITY_ID_BY_INDEX = "SELECT id FROM city WHERE post_index = ?";

    @SneakyThrows
    public City getOne(int id) {
        City city = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CITY_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                city = City.builder()
                        .id(resultSet.getInt("ci.id"))
                        .postIndex(resultSet.getString("ci.post_index"))
                        .country(Country.builder()
                                .id(resultSet.getInt("co.id"))
                                .countryCode2(resultSet.getString("co.country_code_2"))
                                .countryCode3(resultSet.getString("co.country_code_3"))
                                .countryNameRu(resultSet.getString("co.country_name_ru"))
                                .countryNameEn(resultSet.getString("co.country_name_en"))
                                .visibility(resultSet.getBoolean("co.visibility"))
                                .build())
                        .cityNameRu(resultSet.getString("ci.city_name_ru"))
                        .cityNameEn(resultSet.getString("ci.city_name_en"))
                        .visibility(resultSet.getBoolean("ci.visibility"))
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
                        .id(resultSet.getInt("ci.id"))
                        .postIndex(resultSet.getString("ci.post_index"))
                        .country(Country.builder()
                                .id(resultSet.getInt("co.id"))
                                .countryCode2(resultSet.getString("co.country_code_2"))
                                .countryCode3(resultSet.getString("co.country_code_3"))
                                .countryNameRu(resultSet.getString("co.country_name_ru"))
                                .countryNameEn(resultSet.getString("co.country_name_en"))
                                .visibility(resultSet.getBoolean("co.visibility"))
                                .build())
                        .cityNameRu(resultSet.getString("ci.city_name_ru"))
                        .cityNameEn(resultSet.getString("ci.city_name_en"))
                        .visibility(resultSet.getBoolean("ci.visibility"))
                        .build();
                cityList.add(city);
            }
        }
        return cityList;
    }

    @SneakyThrows
    public City saveCity(City city) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_CITY, RETURN_GENERATED_KEYS)) {
            Country country = countryDao.saveCountry(Country.getCountry());
            city.setCountry(country);
            preparedStatement.setString(1, city.getPostIndex());
            preparedStatement.setObject(2, city.getCountry().getId());
            preparedStatement.setString(3, city.getCityNameRu());
            preparedStatement.setString(4, city.getCityNameEn());
            preparedStatement.setBoolean(5, city.getVisibility());
            if (preparedStatement.executeUpdate() == 1) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        }
        return city;
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

    @SneakyThrows
    public int getCityIdByCityIndex(String postIndex) {
        int id = 0;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CITY_ID_BY_INDEX)) {
            preparedStatement.setString(1, postIndex);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        }
        return id;
    }

    //TODO
    /*@SneakyThrows
    public void testM() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement("BEGIN " +
                     "INSERT INTO country(country_code_2,country_code_3,country_name_ru,country_name_en)" +
                     "  VALUES('TE', 'TES','TEST','TEST') " +
                     "INSERT INTO city (post_index,country_id,city_name_ru,city_name_en,visibility) " +
                     "  VALUES('123456',LAST_INSERT_ID(),'test_ru', 'test_en','1'); " +
                     "COMMIT;")) {
            preparedStatement.executeUpdate();
        }
    }*/

    public static CityDao getInstance() {
        return INSTANCE;
    }
}
