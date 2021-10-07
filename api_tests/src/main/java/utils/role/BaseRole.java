package utils.role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class BaseRole {

    private Integer id;
    private String roleName = "AQA";
    private String descriptionRu = "Инженер по автоматизации ПО";
    private String descriptionEn = "QA automation engineer";
}
