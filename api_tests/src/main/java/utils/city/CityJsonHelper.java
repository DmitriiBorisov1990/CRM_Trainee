package utils.city;

import controllers.location_controller.city.CityJsonObject;
import dao.CountryDao;
import entity.City;
import entity.Country;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CityJsonHelper {

    private static String postIndex = "3639892";
    private static String cityName = "Reykjavik";
    private static City city = City.getCity();
    private static Country country = Country.getCountry();
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

    public static CityJsonObject changeCityNameAndPostIndex(){
        city.setPostIndex(postIndex);
        city.setCityNameEn(cityName);
        return CityJsonObject.builder()
                .postIndex(city.getPostIndex())
                .countryId(city.getCountry().getId())
                .countryName(city.getCityNameEn())
                .visibility(city.getVisibility())
                .build();
    }
}
