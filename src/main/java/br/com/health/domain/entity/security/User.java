package br.com.health.domain.entity.security;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "user")
public class User implements Serializable {

	@ManyToOne
	@JoinColumn(name = "role_id", updatable = false, insertable = false)
	private Role role;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long userId;

	@Column(name = "username")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "create_time")
	private LocalDate createTime;

	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "status")
	private Integer status;

	public User() {
	}

	protected User(Role role, Long userId, String name, String password,
	               String email, LocalDate createTime, Long roleId, Integer status) {
		this.role = role;
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.email = email;
		this.createTime = createTime;
		this.roleId = roleId;
		this.status = status;
	}

	public static final class Builder {
		private Role role;
		private Long userId;
		private String name;
		private String password;
		private String email;
		private LocalDate createTime;
		private Long roleId;
		private Integer status;

		public Builder() {
		}

		public Builder role(Role val) {
			role = val;
			return this;
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

		public Builder createTime(LocalDate val) {
			createTime = val;
			return this;
		}

		public Builder roleId(Long val) {
			roleId = val;
			return this;
		}

		public Builder status(Integer val) {
			status = val;
			return this;
		}

		public User createNewUser() {
			return new User(
					role, userId, name, password, email, createTime, roleId, status);
		}
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(role, user.role) && Objects.equals(userId, user.userId)
				&& Objects.equals(name, user.name)
				&& Objects.equals(password, user.password) && Objects.equals(email, user.email)
				&& Objects.equals(createTime, user.createTime) && Objects.equals(roleId, user.roleId)
				&& Objects.equals(status, user.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(role, userId, name, password, email, createTime, roleId, status);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
				.add("role=" + role)
				.add("userId=" + userId)
				.add("name='" + name + "'")
				.add("password='" + password + "'")
				.add("email='" + email + "'")
				.add("createTime=" + createTime)
				.add("roleId=" + roleId)
				.add("status=" + status)
				.toString();
	}
}
