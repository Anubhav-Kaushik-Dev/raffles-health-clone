package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.BusinessException;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.dto.ClaimRequestDTO;
import com.example.demo.dto.ClaimResponseDTO;
import com.example.demo.dto.ClaimUpdateRequestDTO;
import com.example.demo.entity.Claim;
import com.example.demo.service.ClaimService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/Claim")
public class ClaimController {

	
	 private static final Logger log = LoggerFactory.getLogger(ClaimController.class);
	 
	@Autowired
	private ClaimService srvc;
	

    @GetMapping("/test")
    public String testLogging() {
        log.info("INFO log: Test endpoint called");
        log.debug("DEBUG log: Test endpoint called");
        return "Check console for logs!";
    }

	// CREATE A CLAIM
    @PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/createClaim")
	public ResponseEntity<ClaimResponseDTO> createClaim(@Valid @RequestBody ClaimRequestDTO request) {
		
		log.info("Request received to create claim ");
		ClaimResponseDTO resp = srvc.createClaim(request);
		
		log.info("Claim created .... ");

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	// GET ALL CLAIMS
	@GetMapping("/getAllClaims")
	public ResponseEntity<Page<ClaimResponseDTO>> getAllClaims(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "claimNumber") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		
		log.info("Request received to get all claims ..........  ");

		Page<ClaimResponseDTO> respo = srvc.getAllClaims(page, size, sortBy, direction);
		return ResponseEntity.status(HttpStatus.OK).body(respo);
	}
	
	@GetMapping("/claims")
	public ResponseEntity<Page<ClaimResponseDTO>> getClaims(@RequestParam(required = false) String status, // for filter
			@RequestParam(required = false) Long customerId, // for filter
			@RequestParam(defaultValue = "0") int page, // for pagination
			@RequestParam(defaultValue = "5") int size, // for pagination
			@RequestParam(defaultValue = "claimAmount") String sortBy, // for sorting
			@RequestParam(defaultValue = "asc") String direction // for sorting
	) {
		log.info("Request received to get all claims ....with pagination and sorting......  ");
		Page<ClaimResponseDTO> response = srvc.getClaimsWithFilter(status, customerId, page, size, sortBy, direction);
		log.info("All claims sent on your portal...checkkk ....with pagination and sorting......  ");
		return ResponseEntity.ok(response);
		
	}

	// GET CLAIM BY ID
	@GetMapping("/getClaimById/{id}")
	public ResponseEntity<Claim> getClaimById(@PathVariable Long id) {
		
		log.info("Request received to get claim by Claim ID ..........  ");
		Claim resp = srvc.getClaimByID(id);

		log.info("Claim sent on your portal.... ");
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}

	// UPDATE CLAIM (by ID)  Here id refers to Claim ID
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/updateClaim/{claimNumber}")
	public ResponseEntity<Claim> updateClaim(@PathVariable String claimNumber, @RequestBody ClaimUpdateRequestDTO request)  {
		log.info("Request received to update Claim by claimNumber.....  ");
		Claim resp = srvc.updateClaim(claimNumber, request);
		log.info("Claim updated.....  ");
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}

	// HARD DELETE CLAIM (by ID)
	@DeleteMapping("/DeleteClaim/{id}")
	public ResponseEntity<String> deleteClaim(@PathVariable Long id) {
		log.info("Request received to delete Claim by ClaimID.....  ");
		srvc.deleteClaim(id);
		String resp = "SUCCESSFULLY DELETED ";
		log.info("Claim deleted..  ");
		return ResponseEntity.status(HttpStatus.OK).body(resp);

	}

}
