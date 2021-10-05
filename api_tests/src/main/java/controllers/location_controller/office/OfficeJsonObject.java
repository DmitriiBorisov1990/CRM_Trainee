package controllers.location_controller.office;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfficeJsonObject {

    @JsonProperty("id")
    private int id;

    @JsonProperty("cityId")
    private int cityId;

    @JsonProperty("cityName")
    private String cityName;

    @JsonProperty("location")
    private String location;
}
