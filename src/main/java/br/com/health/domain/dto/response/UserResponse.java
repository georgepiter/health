package br.com.health.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

	private final String name;
	private final String password;
	private final String email;
	private final Long roleId;
	private final Long userId;
	private final String status;
	private final String profile;

	protected UserResponse(String name, String password,
	                       String email, Long roleId, Long userId, String status, String profile) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.roleId = roleId;
		this.userId = userId;
		this.status = status;
		this.profile = profile;
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

	public Long getUserId() {
		return userId;
	}

	public String getStatus() {
		return status;
	}

	public String getProfile() {
		return profile;
	}

	public static final class Builder {
		private String name;
		private String password;
		private String email;
		private Long roleId;
		private Long userId;
		private String status;
		private String profile;

		public Builder() {
		}

		public UserResponse.Builder name(String val) {
			name = val;
			return this;
		}

		public UserResponse.Builder password(String val) {
			password = val;
			return this;
		}

		public UserResponse.Builder email(String val) {
			email = val;
			return this;
		}

		public UserResponse.Builder roleId(Long val) {
			roleId = val;
			return this;
		}

		public UserResponse.Builder userId(Long val) {
			userId = val;
			return this;
		}

		public UserResponse.Builder status(String val) {
			status = val;
			return this;
		}

		public UserResponse.Builder profile(String val) {
			profile = val;
			return this;
		}

		public UserResponse createUserResponse() {
			return new UserResponse(
					name, password,
					email, roleId, userId, status, profile
			);
		}
	}
}
