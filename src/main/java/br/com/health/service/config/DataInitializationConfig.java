package br.com.health.service.config;

import br.com.health.domain.entity.security.Role;
import br.com.health.domain.entity.security.User;
import br.com.health.domain.enums.StatusEnum;
import br.com.health.domain.repository.RoleRepository;
import br.com.health.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class DataInitializationConfig {


	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public DataInitializationConfig(RoleRepository roleRepository,
	                                UserRepository userRepository,
	                                PasswordEncoder passwordEncoder) {
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	public CommandLineRunner initData() {
		return args -> {

			if (userRepository.findByName("admin").isEmpty()) {
				createAndSaveRole("ROLE_ADMIN");
				createAndSaveRole("ROLE_MANAGER");
				createAndSaveUsers("admin", 1L, "admin@teste.com.br");
				createAndSaveUsers("manager", 2L, "manager@teste.com.br");
			}
		};
	}

	private void createAndSaveRole(String roleName) {
		Role role = new Role();
		role.setName(roleName);
		roleRepository.save(role);
	}

	private void createAndSaveUsers(String name, long roleID, String mail) {
		User adminUser = new User.Builder()
				.name(name)
				.email(mail)
				.roleId(roleID)
				.status(StatusEnum.ACTIVE.getValue())
				.createTime(LocalDate.now())
				.password(passwordEncoder.encode("123"))
				.createNewUser();

		userRepository.save(adminUser);
	}
}
