package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    private Integer id;
    private String countryCode2;
    private String countryCode3;
    private String countryNameRu;
    private String countryNameEn;
}
