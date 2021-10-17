package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Office {

    private Integer id;
    private City city = City.getCity();
    private String location = "Zodchego 14";
    private Boolean visibility = true;
    private static Office office = new Office();

    public static Office getOffice(){
        return office;
    }
}
