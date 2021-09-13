package entity.location_controller.country;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryItem{

	@JsonProperty("countryNameRu")
	private String countryNameRu;

	@JsonProperty("countryCode3")
	private String countryCode3;

	@JsonProperty("id")
	private int id;

	@JsonProperty("countryCode2")
	private String countryCode2;

	@JsonProperty("countryNameEn")
	private String countryNameEn;
}