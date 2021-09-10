package entity.location_controller.office;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Office {

    @JsonProperty("Office")
    private List<OfficeItem> office;
}