package entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class User {

    private Integer id;
    private String firstNameRu;
    private String lastNameRu;
    private String firstNameEn;
    private String lastNameEn;
    private Date birthday;
    private String skype;
    private String corporateEmail;
    private String phone;
    private String status; // Enum генерируется на сервере
    private Role role;
    private Date createDate;
    private Office office;
}