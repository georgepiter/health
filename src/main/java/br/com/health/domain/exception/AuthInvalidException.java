package br.com.health.domain.exception;

public class AuthInvalidException extends RuntimeException {

    public AuthInvalidException(String message) {
        super(message);
    }
}
