package controllers.location_controller.country;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryJsonObject {

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("countryCode2")
	private String countryCode2;

	@JsonProperty("countryCode3")
	private String countryCode3;

	@JsonProperty("countryNameRu")
	private String countryNameRu;

	@JsonProperty("countryNameEn")
	private String countryNameEn;

	@JsonProperty("visibility")
	private Boolean visibility;
}