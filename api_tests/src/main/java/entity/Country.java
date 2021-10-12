package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    private Integer id = 0;
    private String countryCode2 = "IS";
    private String countryCode3 = "ISL";
    private String countryNameRu = "Исландия";
    private String countryNameEn = "Islandiya";
    private Boolean visibility = true;
    private static Country country = new Country();

    public static Country getCountry() {
        return country;
    }
}
