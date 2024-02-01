package br.com.health.domain.exception;

public class JWTExpiresException extends Exception {

	public JWTExpiresException(String message) {
		super(message);
	}
}
