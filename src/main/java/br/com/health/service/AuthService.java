package br.com.health.service;

import br.com.health.domain.dto.request.AuthenticationRequest;
import br.com.health.domain.dto.response.AuthenticatedResponse;
import br.com.health.domain.exception.AuthInvalidException;
import br.com.health.service.security.JWTUtilComponent;
import br.com.health.service.security.UserSpringSecurityService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final JWTUtilComponent jwtUtilComponent;

	public AuthService(AuthenticationManager authenticationManager,
	                   JWTUtilComponent jwtUtilComponent) {
		this.authenticationManager = authenticationManager;
		this.jwtUtilComponent = jwtUtilComponent;
	}

	public AuthenticatedResponse getToken(AuthenticationRequest request) {
		try {
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
			);

			String username = ((UserSpringSecurityService) authenticate.getPrincipal()).getUsername();
			String email = ((UserSpringSecurityService) authenticate.getPrincipal()).getEmail();
			Long userId = ((UserSpringSecurityService) authenticate.getPrincipal()).getId();

			String authority = Objects.requireNonNull(((UserSpringSecurityService) authenticate.getPrincipal()).getAuthorities()
					.stream().findFirst().orElse(null)).getAuthority();

			String token = jwtUtilComponent.generateToken(username, userId, authority, email);
			AuthenticatedResponse authenticatedResponse = new AuthenticatedResponse();
			authenticatedResponse.setToken(String.format("Bearer %s", token));

			return authenticatedResponse;
		} catch (AuthenticationException e) {
			throw new AuthInvalidException("Nome de usuário ou senha inválida");
		}
	}
}
