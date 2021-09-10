package entity.location_controller.city;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class City{

	@JsonProperty("City")
	private List<CityItem> city;

	public List<CityItem> getCity(){
		return city;
	}
}