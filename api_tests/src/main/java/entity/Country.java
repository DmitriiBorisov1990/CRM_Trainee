package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("country_code_2")
    private String countryCode2;

    @JsonProperty("country_code_3")
    private String countryCode3;

    @JsonProperty("country_name_ru")
    private String countryNameRu;

    @JsonProperty("country_name_en")
    private String countryNameEn;

    @JsonProperty("visibility")
    private Boolean visibility;
}
