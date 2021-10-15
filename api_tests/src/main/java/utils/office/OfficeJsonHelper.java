package utils.office;

import controllers.location_controller.city.CityJsonObject;
import controllers.location_controller.office.OfficeJsonObject;
import dao.CityDao;
import entity.Office;
import utils.city.CityJsonHelper;

public class OfficeJsonHelper {

    private static Office office = Office.getOffice();
    private static CityDao cityDao = CityDao.getInstance();

    public static OfficeJsonObject createJsonObject(){
        CityJsonObject jsonObject = CityJsonHelper.createJsonObject();
        int cityId = cityDao.getCityIdByCityIndex(jsonObject.getPostIndex());
        return OfficeJsonObject.builder()
                .cityId(cityId)
                .location(office.getLocation())
                .visibility(office.getVisibility())
                .build();

    }
}
