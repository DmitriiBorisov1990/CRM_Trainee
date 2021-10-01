package utils.role;

import controllers.role_controller.RoleJsonObject;
import entity.Role;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RoleJsonHelper {

    public static RoleJsonObject mapEntityToJsonObject(Role role){
        return RoleJsonObject.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .descriptionRu(role.getDescriptionRu())
                .descriptionEn(role.getDescriptionEn())
                .build();
    }
}
