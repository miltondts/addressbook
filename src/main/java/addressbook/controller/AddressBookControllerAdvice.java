package addressbook.controller;

import java.util.HashMap;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@ControllerAdvice
public class AddressBookControllerAdvice {
	private ResponseEntity<HashMap<String, String>> buildResponse(String message, HttpStatus httpStatus) {
		HashMap<String, String> response = new HashMap<>();
		response.put("error", message);
		return new ResponseEntity<HashMap<String, String>>(response, httpStatus);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<HashMap<String, String>> invalidAttException(final MethodArgumentNotValidException e) {
		return buildResponse(
				"Invalid payload: " + e.getBindingResult().getFieldError().getDefaultMessage(),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<HashMap<String, String>> contactNotFoundException(final EntityNotFoundException e) {
		return buildResponse("Resource not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<HashMap<String, String>> contactNotFoundException(final EmptyResultDataAccessException e) {
		return buildResponse("Resource not found", HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<HashMap<String, String>> mediaTypeException(final HttpMediaTypeNotSupportedException e) {
		return buildResponse("Media type should be:" + MediaType.APPLICATION_JSON_UTF8_VALUE,
				HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<HashMap<String, String>> mediaTypeException(final JsonParseException e) {
		return buildResponse("Invalid Json", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<HashMap<String, String>> jsonMappingException(JsonMappingException e) {
		return buildResponse("Invalid Json", HttpStatus.BAD_REQUEST);
	}
}
