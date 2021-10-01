package entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Country {

    private Integer id;
    private String countryCode2;
    private String countryCode3;
    private String countryNameRu;
    private String countryNameEn;
    private Boolean visibility;
}
