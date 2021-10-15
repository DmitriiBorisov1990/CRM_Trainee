package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City {

    private Integer id = 0;
    private String postIndex = "3634492";
    private Country country = Country.getCountry();
    private String cityNameRu = "Рейкьявик";
    private String cityNameEn = "Reykjavic";
    private Boolean visibility = true;
    private static City city = new City();

    public static City getCity() {
        return city;
    }
}
