package utils.country;

import controllers.location_controller.country.CountryJsonObject;
import entity.Country;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CountryJsonHelper extends BaseCountry {

    public static CountryJsonObject createJsonObject(){
        return CountryJsonObject.builder()
                .id(id)
                .countryCode2(countryCode2)
                .countryCode3(countryCode3)
                .countryNameRu(countryNameRu)
                .countryNameEn(countryNameEn)
                .visibility(true)
                .build();
    }

    public static CountryJsonObject mapEntityToJsonObject(Country entity){
        return CountryJsonObject.builder()
                .id(entity.getId())
                .countryCode2(entity.getCountryCode2())
                .countryCode3(entity.getCountryCode3())
                .countryNameRu(entity.getCountryNameRu())
                .countryNameEn(entity.getCountryNameEn())
                .visibility(entity.getVisibility())
                .build();
    }
}
