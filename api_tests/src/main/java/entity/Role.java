package entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role {

    private Integer id;
    private String roleName;
    private String descriptionRu;
    private String descriptionEn;
}
