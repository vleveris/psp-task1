package com.users.web.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorRestHandler {
	@ExceptionHandler(ApiError.class)
	public ResponseEntity<ErrorResponse> onApiException(ApiError e) {
		return ResponseEntity.status(e.getCode()).body(new ErrorResponse(e.getCode(), e.getMessage(), null));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> onException(Exception ex) {
		return ResponseEntity.status(500).body(new ErrorResponse(500, ex.getMessage(), null));
	}
}
