package br.com.health.service;

import br.com.health.domain.dto.response.ResponseEntityCustom;
import br.com.health.domain.dto.request.UserRequest;
import br.com.health.domain.dto.response.UserResponse;
import br.com.health.domain.entity.security.User;
import br.com.health.domain.enums.RoleEnum;
import br.com.health.domain.enums.StatusEnum;
import br.com.health.domain.exception.UserException;
import br.com.health.domain.exception.UserNotFoundException;
import br.com.health.domain.repository.UserRepository;
import br.com.health.utils.EmailUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	private final BCryptPasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public UserService(BCryptPasswordEncoder passwordEncoder,
					   UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	public ResponseEntityCustom registerNewUser(UserRequest userRequest) throws UserException {
		if (!EmailUtils.isValidEmailFormat(userRequest.getEmail())) {
			return new ResponseEntityCustom(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "O formato do email é inválido");
		}
		if (userRepository.existsByNameOrEmail(userRequest.getName(), userRequest.getEmail())) {
			throw new UserException("Usuário já existe com o nome ou email especificado");
		}

		User newUser = new User.Builder()
				.name(userRequest.getName())
				.email(userRequest.getEmail())
				.roleId(userRequest.getRoleId())
				.status(StatusEnum.ACTIVE.getValue())
				.createTime(LocalDate.now())
				.password(passwordEncoder.encode(userRequest.getPassword()))
				.createNewUser();
		userRepository.save(newUser);
		return new ResponseEntityCustom(HttpStatus.CREATED.value(), HttpStatus.CREATED, "Usuário criado com sucesso");
	}

	public ResponseEntityCustom deleteUserById(Long idUser) {
		User user = userRepository.findById(idUser).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado pelo ID na base"));
		userRepository.delete(user);
		return new ResponseEntityCustom(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT, "Usuário deletado com sucesso!");
	}

	public List<UserResponse> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserResponse> userResponses = new ArrayList<>();

		for (User user : users) {
			String status = (user.getStatus().equals(StatusEnum.ACTIVE.getValue()))
					? StatusEnum.ACTIVE.getLabel() : StatusEnum.INACTIVE.getLabel();

			String profile = (user.getRoleId().equals(RoleEnum.ADMIN.getCod()))
					? RoleEnum.ADMIN.getDescription() : RoleEnum.MANAGER.getDescription();

			UserResponse userResponse = new UserResponse.Builder()
					.email(user.getEmail())
					.name(user.getName())
					.userId(user.getUserId())
					.roleId(user.getRoleId())
					.status(status)
					.profile(profile)
					.createUserResponse();

			userResponses.add(userResponse);
		}

		return userResponses;
	}

	public ResponseEntity<HttpStatus> updateUserStatus(UserRequest userRequest) {
		User user = userRepository.findById(userRequest.getUserId()).orElseThrow(() -> new UsernameNotFoundException("User não encontrado na base"));
		user.setUserId(userRequest.getUserId());
		user.setStatus(userRequest.getStatus().equals(StatusEnum.ACTIVE.getLabel()) ? StatusEnum.ACTIVE.getValue() : StatusEnum.INACTIVE.getValue());
		userRepository.save(user);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	public ResponseEntity<HttpStatus> updateUserRole(Long userId, Long roleId) throws UserNotFoundException {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User não encontrado pelo Id"));
		user.setRoleId(roleId);
		userRepository.save(user);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
