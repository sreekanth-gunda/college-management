package com.colleage.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.colleage.response.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobelExceptionHandler {


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex,HttpServletRequest request) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});

		ErrorResponse response = new ErrorResponse();
		response.setMessage("Validation failed");
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setTimestamp(LocalDateTime.now());
		response.setPath(request.getRequestURI());
		response.setErrors(errors);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	 @ExceptionHandler(StudentNotFoundException.class)
	    public ResponseEntity<Map<String, Object>> handleNotFound(
	            StudentNotFoundException ex,
	            HttpServletRequest request) {

	        Map<String, Object> response = new HashMap<>();
	        response.put("message", ex.getMessage());
	        response.put("status", HttpStatus.NOT_FOUND.value());
	        response.put("timestamp", LocalDateTime.now());
	        response.put("path", request.getRequestURI());

	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }


}
