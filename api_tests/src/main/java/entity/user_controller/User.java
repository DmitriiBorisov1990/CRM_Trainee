package entity.user_controller;

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
public class User {

	@JsonProperty("birthday")
	private String birthday;

	@JsonProperty("lastNameRu")
	private String lastNameRu;

	@JsonProperty("trainingGroupIds")
	private List<Integer> trainingGroupIds;

	@JsonProperty("roleId")
	private int roleId;

	@JsonProperty("firstNameRu")
	private String firstNameRu;

	@JsonProperty("trainingIds")
	private List<Integer> trainingIds;

	@JsonProperty("lastNameEn")
	private String lastNameEn;

	@JsonProperty("skype")
	private String skype;

	@JsonProperty("firstNameEn")
	private String firstNameEn;

	@JsonProperty("officeId")
	private int officeId;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("email")
	private String email;

	@JsonProperty("status")
	private String status;
}