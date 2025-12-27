package com.example.demo.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.controller.ClaimController;


@RestControllerAdvice
public class GlobalExceptionHandling {

	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandling.class);

	 
	 
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> HandleResNotFound(ResourceNotFoundException ex) {
		
		log.error("ResourceNotFoundException intercepted.....");

		Map<String, Object> map = new HashMap<>();

		map.put("Timestamp", LocalDateTime.now());
		map.put("Error Code", ex.getMessage());
		map.put("msg", "RESOURCE NOT FOUND.. :( ");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException ex) {
		
		log.error("BusinessException intercepted.....");

		Map<String, Object> map = new HashMap<>();

		map.put("Timestamp", LocalDateTime.now());
		map.put("Error Code", ex.getMessage());
		map.put("msg", "HEY.Its a Business Exception.. :( ");

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);

	}

}
