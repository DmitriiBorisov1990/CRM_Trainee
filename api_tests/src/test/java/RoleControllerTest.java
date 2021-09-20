import dao.RoleDao;
import entity.Role;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.api.Authorization;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class RoleControllerTest {

    @BeforeTest
    void start() {
        Authorization.login();
        loadDriver();
    }

    @Test
    static void createRoleTest(){
        /*Role role = Role.builder().roleName("LAZY_PERSON").descriptionRu("Лентяй").descriptionEn("lazy person").build();
        UserRoleDao.getInstance().saveRole(role);
        Optional<Role> newTestRole = UserRoleDao.getInstance().getOne(role.getId());
        System.out.println(newTestRole.toString());*/
        //create_role_in_swagger.go_to_db_and_check_it;
    }
    @Test
    static void getRoleByIdTest(){
        Optional<Role> role = RoleDao.getInstance().getOne(1000);
        System.out.println(role.toString());
    }

    @Test
    static void deleteRole(){
        Assert.assertTrue(RoleDao.getInstance().deleteRole(1018));
    }

    @Test
    static void updateRoleTest(){
        RoleDao.getInstance()
                .getOne(1018)
                .map(peek(it ->it.setRoleName("TEST_ROLE_new"))).ifPresent(RoleDao.getInstance()::update);
    }

    @SneakyThrows
    private static void loadDriver() {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public static <T> UnaryOperator<T> peek(Consumer<T> consumer){
        return object -> {
            consumer.accept(object);
            return object;
        };
    }
}
