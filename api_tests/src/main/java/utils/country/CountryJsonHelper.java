package utils.country;

import controllers.location_controller.country.CountryJsonObject;
import entity.Country;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CountryJsonHelper {

    public static CountryJsonObject createJsonObject(){
        Country country = CountryEntityHelper.createCountryEntity();
        return CountryJsonObject.builder()
                .id(country.getId())
                .countryCode2(country.getCountryCode2())
                .countryCode3(country.getCountryCode3())
                .countryNameRu(country.getCountryNameRu())
                .countryNameEn(country.getCountryNameEn())
                .visibility(country.getVisibility())
                .build();
    }

    public static CountryJsonObject mapEntityToJsonObject(){
        Country country = CountryEntityHelper.updateCountry();
        return CountryJsonObject.builder()
                .id(country.getId())
                .countryCode2(country.getCountryCode2())
                .countryCode3(country.getCountryCode3())
                .countryNameRu(country.getCountryNameRu())
                .countryNameEn(country.getCountryNameEn())
                .visibility(country.getVisibility())
                .build();
    }
}
