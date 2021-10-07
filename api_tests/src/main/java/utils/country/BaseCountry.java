package utils.country;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class BaseCountry {

    private Integer id;
    private String countryCode2 = "BE";
    private String countryCode3 = "BEL";
    private String countryNameRu = "Бельгия";
    private String countryNameEn = "Belgique";
    private Boolean visibility = true;
}
