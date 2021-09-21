package controllers.user_controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserController {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("firstNameRu")
    private String firstNameRu;

    @JsonProperty("lastNameRu")
    private String lastNameRu;

    @JsonProperty("firstNameEn")
    private String firstNameEn;

    @JsonProperty("lastNameEn")
    private String lastNameEn;

    @JsonProperty("birthday")
    private String birthday;

    @JsonProperty("skype")
    private String skype;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("status")
    private String status;

    @JsonProperty("roleId")
    private Integer roleId;

    @JsonProperty("officeId")
    private int officeId;

    @JsonProperty("trainingGroupIds")
    private List<Integer> trainingGroupIds;

    @JsonProperty("trainingIds")
    private List<Integer> trainingIds;
}