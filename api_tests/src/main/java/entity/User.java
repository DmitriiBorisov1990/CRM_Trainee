package entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Builder
public class User {

    private Integer id;
    private String firstNameRu;
    private String lastNameRu;
    private String firstNameEn;
    private String lastNameEn;
    private LocalDate birthday;
    private String skype;
    private String corporateEmail;
    private String phone;
    private String status; // Enum генерируется на сервере
    private Role role;
    private Date createDate; // дата создания пользователя на сервере
    private Office office;
}
