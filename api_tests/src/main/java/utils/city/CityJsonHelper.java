package utils.city;

import controllers.location_controller.city.CityJsonObject;
import entity.City;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CityJsonHelper extends BaseCity {

    public static CityJsonObject createJsonObject(int countryId,String countryName){
        return CityJsonObject.builder()
                .id(id)
                .postIndex(postIndex)
                .countryId(countryId)
                .countryName(countryName)
                .cityName(cityNameEn)
                .visibility(visibility)
                .build();
    }
    // TODO ?
    public static CityJsonObject mapEntityToJsonObject(City city,String countryNameRu){
        return CityJsonObject.builder()
                .id(city.getId())
                .postIndex(city.getPostIndex())
                .countryId(city.getCountryId())
                .countryName(countryNameRu)
                .cityName(city.getCityNameRu())
                .visibility(city.getVisibility())
                .build();
    }
}
