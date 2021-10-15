package utils.country;

import controllers.location_controller.country.CountryJsonObject;
import entity.Country;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CountryJsonHelper {

    private Country country = Country.getCountry();

    public static CountryJsonObject createJsonObject() {
        return CountryJsonObject.builder()
                .countryCode2(country.getCountryCode2())
                .countryCode3(country.getCountryCode3())
                .countryNameRu(country.getCountryNameRu())
                .countryNameEn(country.getCountryNameEn())
                .visibility(country.getVisibility())
                .build();
    }

    public static CountryJsonObject changeCountryName() {
        String countryCode2 = "IL";
        String countryCode3 = "ILD";
        String countryNameEn = "Island";
        country.setCountryCode2(countryCode2);
        country.setCountryCode3(countryCode3);
        country.setCountryNameEn(countryNameEn);
        return CountryJsonObject.builder()
                .countryCode2(country.getCountryCode2())
                .countryCode3(country.getCountryCode3())
                .countryNameRu(country.getCountryNameEn())
                .countryNameEn(country.getCountryNameRu())
                .visibility(country.getVisibility())
                .build();
    }
}
