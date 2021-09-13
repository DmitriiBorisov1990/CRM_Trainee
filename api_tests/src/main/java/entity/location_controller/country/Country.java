package entity.location_controller.country;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Country{

	@JsonProperty("Country")
	private List<CountryItem> country;
}