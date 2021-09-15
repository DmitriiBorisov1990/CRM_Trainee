package data_base;

import dao.UserDao;
import entity.Office;
import entity.Role;
import entity.User;
import lombok.SneakyThrows;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class DataBase {

    public static void main(String[] args) {
        loadDriver();
        UserDao.getInstance()
                .getOne(70183)
                .map(peek(it ->it.setPhone("777-77-77")))
                .ifPresent(UserDao.getInstance()::update);
    }

    //TODO
    private static void testSaveUser(){
        User user = UserDao.getInstance().saveUser(User.builder()
                        .firstNameRu("Дмитрий")
                        .lastNameRu("Борисов")
                        .firstNameEn("Dmitrii")
                        .lastNameEn("Borisov")
                        //.birthday()
                        .skype("@skype")
                        .corporateEmail("d.barysay@andersenlab.com")
                        .phone("123-34-56")
                        .role(Role.builder()
                                .id(1001)
                                .build())
                        .office(Office.builder()
                                .id(1)
                                .build())
                .build());
        System.out.println(user.getId());
    }

    public static <T> UnaryOperator<T> peek(Consumer<T> consumer){
        return object -> {
            consumer.accept(object);
            return object;
        };
    }

    private static void testDeleteUser(){
        System.out.println(UserDao.getInstance().deleteUser(70180));
    }
    @SneakyThrows
    private static void loadDriver() {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
}
