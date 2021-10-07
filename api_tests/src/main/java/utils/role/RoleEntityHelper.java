package utils.role;

import dao.RoleDao;
import entity.Role;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RoleEntityHelper {

    public static Role createRoleEntity() {
        BaseRole baseRole = new BaseRole();
        return Role.builder()
                .id(baseRole.getId())
                .roleName(baseRole.getRoleName())
                .descriptionRu(baseRole.getDescriptionRu())
                .descriptionEn(baseRole.getDescriptionEn())
                .build();
    }

    public static Role updateRole() {
        Role role = createRoleEntity();
        return Role.builder()
                .id(role.getId())
                .roleName(role.getRoleName().toUpperCase())
                .descriptionRu(role.getDescriptionRu().toUpperCase())
                .descriptionEn(role.getDescriptionEn().toUpperCase())
                .build();
    }

    public static int saveRoleInDataBaseAndGetId() {
        return RoleDao.getInstance().saveRole(createRoleEntity()).getId();
    }

    public static Role getRoleFromDataBaseById(int id) {
        return RoleDao.getInstance().getOne(id);
    }
}
