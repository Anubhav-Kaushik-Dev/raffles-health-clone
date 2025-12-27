package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {

	}

	// Constructor with message
	public ResourceNotFoundException(String message) { // will be invoked by new ResourceNotFoundException("TEST 404")
														// in Service class
		super(message);
	}

}
