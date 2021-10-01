package utils.role;

import dao.RoleDao;
import entity.Role;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RoleEntityHelper extends BaseRole {

    public static Role createRoleEntity(){
        return Role.builder()
                .id(id)
                .roleName(roleName)
                .descriptionRu(descriptionRu)
                .descriptionEn(descriptionEn)
                .build();
    }
    public static int saveRoleInDataBaseAndGetId(Role role){
        return RoleDao.getInstance().saveRole(role).getId();
    }

    public static Role getRoleFromDataBaseById(int id){
        return RoleDao.getInstance().getOne(id);
    }

    public static Role updateRole(Role role) {
        return Role.builder()
                .id(role.getId())
                .roleName(role.getRoleName().toUpperCase())
                .descriptionRu(role.getDescriptionRu().toUpperCase())
                .descriptionEn(role.getDescriptionEn().toUpperCase())
                .build();
    }
}
