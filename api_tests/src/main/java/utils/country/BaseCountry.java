package utils.country;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class BaseCountry {

    private Integer id = 0;
    private String countryCode2 = "IS";
    private String countryCode3 = "ISL";
    private String countryNameRu = "Исландия";
    private String countryNameEn = "Island";
    private Boolean visibility = true;
}
