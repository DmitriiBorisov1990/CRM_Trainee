package entity.token_controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Token {

	@JsonProperty("password")
	private String password;

	@JsonProperty("email")
	private String email;
}