package controllers.token_controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenController {

	@JsonProperty("password")
	private String password;

	@JsonProperty("email")
	private String email;
}