package utils.office;

import controllers.location_controller.office.OfficeJsonObject;
import entity.Office;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OfficeJsonHelper {

    private static Office office = Office.getOffice();

    public static OfficeJsonObject createJsonObject(int cityId) {
        return OfficeJsonObject.builder()
                .cityId(cityId)
                .location(office.getLocation())
                .visibility(office.getVisibility())
                .build();
    }

    public static OfficeJsonObject updateOfficeLocation(OfficeJsonObject office){
        office.setLocation("Pobedy 12");
        return OfficeJsonObject.builder()
                .id(office.getId())
                .cityId(office.getCityId())
                .cityName(office.getCityName())
                .location(office.getLocation())
                .visibility(office.getVisibility())
                .build();
    }
}
