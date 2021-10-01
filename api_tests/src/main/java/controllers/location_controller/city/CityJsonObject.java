package controllers.location_controller.city;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityJsonObject {

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("postIndex")
	private String postIndex;

	@JsonProperty("countryId")
	private Integer countryId;

	@JsonProperty("countryName")
	private String countryName;

	@JsonProperty("cityName")
	private String cityName;

	@JsonProperty("visibility")
	private Boolean visibility;
}