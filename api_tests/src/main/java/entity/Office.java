package entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Office {

    private Integer id;
    private City city;
    private String location; //TODO
}
