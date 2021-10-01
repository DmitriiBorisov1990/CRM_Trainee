package utils.country;

import dao.CountryDao;
import entity.Country;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CountryEntityHelper extends BaseCountry {

    public static Country createCountryEntity() {
        return Country.builder()
                .countryCode2(countryCode2)
                .countryCode3(countryCode3)
                .countryNameRu(countryNameRu)
                .countryNameEn(countryNameEn)
                .visibility(true)
                .build();
    }

    public static Country updateCountry(Country country) {
        return Country.builder()
                .id(country.getId())
                .countryCode2(country.getCountryCode2().toUpperCase())
                .countryCode3(country.getCountryCode3().toUpperCase())
                .countryNameRu(country.getCountryNameRu().toUpperCase())
                .countryNameEn(country.getCountryNameEn().toUpperCase())
                .visibility(true)
                .build();
    }

    public static Country getCountryFromDataBaseById(int id){
        return CountryDao.getInstance().getOne(id);
    }

    public static int saveCountryInDataBaseAndGetId(Country country){
        return CountryDao.getInstance().saveCountry(country).getId();
    }
}
