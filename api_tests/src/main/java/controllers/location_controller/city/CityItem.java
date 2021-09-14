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
public class CityItem{

	@JsonProperty("cityName")
	private String cityName;

	@JsonProperty("postIndex")
	private String postIndex;

	@JsonProperty("countryName")
	private String countryName;

	@JsonProperty("id")
	private int id;

	@JsonProperty("countryId")
	private int countryId;
}