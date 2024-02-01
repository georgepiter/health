package br.com.health.domain.exceptionHandler;


import br.com.health.domain.dto.response.ResponseEntityCustom;
import br.com.health.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({
			FieldException.class
	})
	public ResponseEntityCustom handleBadRequest(Exception e) {
		return new ResponseEntityCustom(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, e.getMessage());
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({
			UserException.class
	})
	public ResponseEntityCustom handleConflict(Exception e) {
		return new ResponseEntityCustom(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, e.getMessage());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({
			UserNotFoundException.class,
			BeneficiaryNotFoundException.class,
			DocumentNotFoundException.class
	})
	public ResponseEntityCustom handleNotFound(Exception e) {
		return new ResponseEntityCustom(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, e.getMessage());
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({
			JWTExpiresException.class,
			AuthInvalidException.class,
	})
	public ResponseEntityCustom handleForbidden(Exception e) {
		return new ResponseEntityCustom(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED, e.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({
			SQLIntegrityConstraintViolationException.class,
			DataBaseException.class
	})
	public ResponseEntityCustom handleInternalServerError(Exception e) {
		return new ResponseEntityCustom(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}
}
