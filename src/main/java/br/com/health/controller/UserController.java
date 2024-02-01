package br.com.health.controller;

import br.com.health.domain.dto.response.ResponseEntityCustom;
import br.com.health.domain.dto.request.UserRequest;
import br.com.health.domain.dto.response.UserResponse;
import br.com.health.domain.exception.UserException;
import br.com.health.domain.exception.UserNotFoundException;
import br.com.health.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "2.Utilizador", description = "Controlador responsável por gerenciar os Utilizadores do sistema")
@Api(produces = MediaType.APPLICATION_JSON_VALUE, tags = {"2.Utilizador"})
@RequestMapping("api/v1/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/create")
	@ApiOperation(value = "Método que registra um novo utilizador")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntityCustom registerNewUser(@RequestBody UserRequest userRequest) throws UserException {
		return userService.registerNewUser(userRequest);
	}

	@GetMapping(value = "/all")
	@ApiOperation(value = "Método retorna todos users")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public List<UserResponse> getAllUsers() {
		return userService.getAllUsers();
	}

	@PutMapping(value = "/status")
	@ApiOperation(value = "Altera status do user, ativa ou desativa")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<HttpStatus> updateUserStatus(@RequestBody UserRequest userRequest) {
		return userService.updateUserStatus(userRequest);
	}

	@PutMapping(value = "/role/{userId}/{roleId}")
	@ApiOperation(value = "Altera o perfil do user")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<HttpStatus> updateUserRole(@PathVariable Long userId, @PathVariable Long roleId) throws UserNotFoundException {
		return userService.updateUserRole(userId, roleId);
	}

	@DeleteMapping(value = "/{userId}")
	@ApiOperation(value = "Método que deleta o user utilizador pelo ID")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntityCustom deleteUserById(@PathVariable Long userId) throws UsernameNotFoundException {
		return userService.deleteUserById(userId);
	}
}
