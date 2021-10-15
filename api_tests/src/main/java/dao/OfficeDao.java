package dao;

import entity.City;
import entity.Country;
import entity.Office;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import transaction.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OfficeDao {

    private static final OfficeDao INSTANCE = new OfficeDao();
    private static final String GET_ALL_OFFICE = "SELECT o.id, o.location, o.visibility, " +
                               "c.id, c.post_index, c.city_name_ru, c.city_name_en, c.visibility, " +
                                "co.id, co.country_code_2, co.country_code_3, co.country_name_ru, co.country_name_en, co.visibility " +
                        "FROM dev_crmreq_t.office o " +
                        "LEFT JOIN dev_crmreq_t.city c " +
                        "ON o.city_id = c.id " +
                        "JOIN dev_crmreq_t.country co " +
                        "ON c.country_id  = co.id " +
                        "ORDER BY o.id";


    @SneakyThrows
    public List<Office> getAll() {
        List<Office> officeList = new ArrayList<>();
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_OFFICE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Office office = Office.builder()
                        .id(resultSet.getInt("o.id"))
                        .city(City.builder()
                                .id(resultSet.getInt("c.id"))
                                .postIndex(resultSet.getString("c.post_index"))
                                .country(Country.builder()
                                        .id(resultSet.getInt("co.id"))
                                        .countryCode2(resultSet.getString("co.country_code_2"))
                                        .countryCode3(resultSet.getString("co.country_code_3"))
                                        .countryNameRu(resultSet.getString("co.country_name_ru"))
                                        .countryNameEn(resultSet.getString("co.country_name_en"))
                                        .visibility(resultSet.getBoolean("co.visibility"))
                                        .build())
                                .cityNameRu(resultSet.getString("c.city_name_ru"))
                                .cityNameEn(resultSet.getString("c.city_name_en"))
                                .visibility(resultSet.getBoolean("c.visibility"))
                                .build())
                        .location(resultSet.getString("o.location"))
                        .visibility(resultSet.getBoolean("o.visibility"))
                        .build();
                officeList.add(office);
            }
        }
        return officeList;
    }

    public static OfficeDao getInstance() {
        return INSTANCE;
    }
}
