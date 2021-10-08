package utils.country;

import dao.CountryDao;
import entity.Country;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CountryEntityHelper {

    public static Country createCountryEntity() {
        BaseCountry baseCountry = new BaseCountry();
        return Country.builder()
                .id(baseCountry.getId())
                .countryCode2(baseCountry.getCountryCode2())
                .countryCode3(baseCountry.getCountryCode3())
                .countryNameRu(baseCountry.getCountryNameRu())
                .countryNameEn(baseCountry.getCountryNameEn())
                .visibility(baseCountry.getVisibility())
                .build();
    }

    public static Country updateCountry() {
        Country country = createCountryEntity();
        return Country.builder()
                .id(country.getId())
                .countryCode2(country.getCountryCode2())
                .countryCode3(country.getCountryCode3())
                .countryNameRu(country.getCountryNameRu().toUpperCase())
                .countryNameEn(country.getCountryNameEn().toUpperCase())
                .visibility(country.getVisibility())
                .build();
    }

    public static int saveCountryInDataBaseAndGetId(Country country){
        return CountryDao.getInstance().saveCountry(country).getId();
    }

    public static Country getCountryFromDataBaseById(int id){
        return CountryDao.getInstance().getOne(id);
    }
}
