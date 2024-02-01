package br.com.health.domain.dto.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class UserRequest {

	@ApiModelProperty(example = "teste")
	@NotNull
	private final String name;

	@ApiModelProperty(example = "123")
	@NotNull
	private final String password;

	@ApiModelProperty(example = "teste123@terra.com.br")
	@NotNull
	private final String email;

	@ApiModelProperty(example = "1")
	@NotNull
	private final Long roleId;

	@NotNull
	private final Long userId;

	@ApiModelProperty(example = "ACTIVE")
	private final String status;

	protected UserRequest(Long userId, String name, String password, String email, Long roleId, String status) {
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.email = email;
		this.roleId = roleId;
		this.status = status;
	}

	public static final class Builder {
		private Long userId;
		private String name;
		private String password;
		private String email;
		private Long roleId;
		private String status;
		private String perfil;

		public Builder() {
		}

		public Builder userId(Long val) {
			userId = val;
			return this;
		}

		public Builder name(String val) {
			name = val;
			return this;
		}

		public Builder password(String val) {
			password = val;
			return this;
		}

		public Builder email(String val) {
			email = val;
			return this;
		}

		public Builder roleId(Long val) {
			roleId = val;
			return this;
		}

		public Builder status(String val) {
			status = val;
			return this;
		}

		public Builder perfil(String val) {
			perfil = val;
			return this;
		}

		public UserRequest createNewUser() {
			return new UserRequest(
					userId, name, password, email,
					roleId, status
			);
		}
	}

	public Long getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public Long getRoleId() {
		return roleId;
	}

	public String getStatus() {
		return status;
	}

}
