package utils.city;

import dao.CityDao;
import entity.City;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CityEntityHelper extends BaseCity {

    public static City createCityEntity(int countryId) {
        return City.builder()
                .postIndex(postIndex)
                .countryId(countryId)
                .cityNameRu(cityNameRu)
                .cityNameEn(cityNameEn)
                .visibility(visibility)
                .build();
    }

    public static int saveCityInDataBaseAndGetId(City city) {
        return CityDao.getInstance().saveCity(city).getId();
    }

    public static City getCityFromDataBaseById(int id) {
        return CityDao.getInstance().getOne(id);
    }

    public static City updateCity(City city,int countryId){
        return City.builder()
                .id(city.getId())
                .postIndex(city.getPostIndex())
                .countryId(city.getCountryId())
                .cityNameRu(city.getCityNameRu().toUpperCase())
                .cityNameEn(city.getCityNameEn().toUpperCase())
                .visibility(city.getVisibility())
                .build();
    }
}
