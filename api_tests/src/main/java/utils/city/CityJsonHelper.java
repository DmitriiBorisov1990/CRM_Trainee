package utils.city;

import controllers.location_controller.city.CityJsonObject;
import dao.CountryDao;
import entity.City;
import entity.Country;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CityJsonHelper {

    private City city = City.getCity();
    private Country country = Country.getCountry();
    private CountryDao countryDao = CountryDao.getInstance();

    public static CityJsonObject createJsonObject() {
        country = countryDao.saveCountry(country);
        city.setCountry(country);
        return CityJsonObject.builder()
                .postIndex(city.getPostIndex())
                .countryId(city.getCountry().getId())
                .cityName(city.getCityNameEn())
                .visibility(city.getVisibility())
                .build();
    }

    public static CityJsonObject changeCityNameAndPostIndex() {
        String postIndex = "3639892";
        String cityName = "Reykjavik";
        city.setPostIndex(postIndex);
        city.setCityNameEn(cityName);
        return CityJsonObject.builder()
                .postIndex(city.getPostIndex())
                .countryId(city.getCountry().getId())
                .countryName(city.getCountry().getCountryNameRu())
                .cityName(city.getCityNameEn())
                .visibility(city.getVisibility())
                .build();
    }
}
