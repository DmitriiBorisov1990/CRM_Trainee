package controllers.role_controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleJsonObject {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String roleName;

    @JsonProperty("description in russian")
    private String descriptionRu;

    @JsonProperty("description in english")
    private String descriptionEn;
}
