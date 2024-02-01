package br.com.health.domain.dto.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class AuthenticationRequest {

	@NotNull
	@ApiModelProperty(example = "admin")
	private final String userName;

	@NotNull
	@ApiModelProperty(example = "123")
	private final String password;

	public AuthenticationRequest(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}
