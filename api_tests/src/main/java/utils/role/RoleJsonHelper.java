package utils.role;

import controllers.role_controller.RoleJsonObject;
import entity.Role;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RoleJsonHelper {

    public static RoleJsonObject createJsonObject() {
        Role role = RoleEntityHelper.createRoleEntity();
        return RoleJsonObject.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .descriptionRu(role.getDescriptionRu())
                .descriptionEn(role.getDescriptionEn())
                .build();
    }

    public static RoleJsonObject updateRole() {
        Role updateRole = RoleEntityHelper.updateRole();
        return RoleJsonObject.builder()
                .id(updateRole.getId())
                .roleName(updateRole.getRoleName())
                .descriptionRu(updateRole.getDescriptionRu())
                .descriptionEn(updateRole.getDescriptionEn())
                .build();
    }
}
