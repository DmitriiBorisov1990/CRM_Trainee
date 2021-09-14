package entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class City {

    private Integer id;
    private String postIndex;
    private Country country;
    private String cityNameRu;
    private String cityNameEn;
}
