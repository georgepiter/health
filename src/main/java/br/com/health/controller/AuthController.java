package br.com.health.controller;

import br.com.health.domain.dto.request.AuthenticationRequest;
import br.com.health.domain.dto.response.AuthenticatedResponse;
import br.com.health.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "1.Login", description = "Controlador responsável pela autenticação dos usuários de sistema.")
@Api(produces = MediaType.APPLICATION_JSON_VALUE, tags = {"1.Login"})
@RequestMapping("api/v1/")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("login")
	@ApiOperation(value = "Realiza login e gera o token", response = AuthenticatedResponse.class)
	public AuthenticatedResponse login(@RequestBody AuthenticationRequest request) {
		return authService.getToken(request);
	}
}