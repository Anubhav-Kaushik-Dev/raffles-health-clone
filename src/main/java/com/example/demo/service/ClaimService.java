package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Exception.BusinessException;
import com.example.demo.Exception.GlobalExceptionHandling;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.dto.ClaimRequestDTO;
import com.example.demo.dto.ClaimResponseDTO;
import com.example.demo.dto.ClaimUpdateRequestDTO;
import com.example.demo.entity.Claim;
import com.example.demo.repository.ClaimRepository;
import com.example.demo.specification.ClaimSpecification;

import jakarta.validation.Valid;

@Service
public class ClaimService {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandling.class);

	@Autowired
	private ClaimRepository repo;

	// CREATE A CLAIM:
	// Role of ClaimService:To create a claim record in DB as per request of
	// Customer and return claim status and claim number to customer

	public ClaimResponseDTO createClaim(ClaimRequestDTO request) {

		log.info("Creating Claim with DTO Request");

		// DTO to entity
		Claim claim = new Claim();
		claim.setCustomerId(request.getCustomerId());
		claim.setClaimAmount(request.getClaimAmount());
		claim.setClaimNumber("CLM-" + System.currentTimeMillis());
		claim.setStatus("PENDING");

		// saving into DB
		Claim savedClaim = repo.save(claim);

		log.debug("Claim saved into Database");

		// entity to DTO-Response
		ClaimResponseDTO response = new ClaimResponseDTO(); // Alternatively we could have used Parameterized
															// constructor here and sent vales from parameter itself
		response.setClaimNumber(savedClaim.getClaimNumber());
		response.setStatus(savedClaim.getStatus());
		return response;

	}

	// Service =GET ALL CLAIMS
	public Page<ClaimResponseDTO> getAllClaims(int page, int size, String sortBy, String direction) {

		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		PageRequest pageRequest = PageRequest.of(page, size, sort);
		Page<Claim> claimlist = repo.findAll(pageRequest);

		Page<ClaimResponseDTO> dtoPage = claimlist.map(claim -> new ClaimResponseDTO(

				claim.getClaimNumber(), claim.getStatus()

		));

		return dtoPage;

	}

	public Page<ClaimResponseDTO> getClaimsWithFilter(String status, Long customerId, int page, int size, String sortBy,
			String direction) {

		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		Page<Claim> claims = repo.findAll(ClaimSpecification.filterClaims(status, customerId), pageRequest);

		return claims.map(claim -> new ClaimResponseDTO(claim.getClaimNumber(), claim.getStatus()));
	}

	// SERVICE=GET CLAIM BY ID
	public Claim getClaimByID(Long id) {
		Optional<Claim> clm = repo.findById(id);

		if (clm.isPresent()) {
			return clm.get();
		} else {

			throw new ResourceNotFoundException("Error 404"); // returns nothing but throws an error if id is not
																// present
		}
	}

	// service=UPDATE CLAIM
	public Claim updateClaim(String claimNumber, ClaimUpdateRequestDTO request) {
		Optional<Claim> clm = repo.findByClaimNumber(claimNumber);

		if (clm.isPresent()) {

			Claim existingclaim = clm.get();

			// BUSINESS RULE OR BUSINESS VALIDATION CHECK OR MANUAL CHECK IN SERVICE LAYER
			if (!"PENDING".equals(existingclaim.getStatus())) {
				throw new BusinessException("Its not PENDING ...Only PENDING claims can be updated.:(");
			}

			// update claim amount if present
			if (request.getClaimAmount() != null) {
				existingclaim.setClaimAmount(request.getClaimAmount());
			}

			// update status if present
			if (request.getStatus() != null) {
				existingclaim.setStatus(request.getStatus());
			}

			repo.save(existingclaim);

			return existingclaim;
		} else {
			throw new ResourceNotFoundException("TEST MSG ...404");
		}

	}

	// SERVICE= HARD DELETE BY ID
	public void deleteClaim(Long id) {

		Optional<Claim> existingclaim = repo.findById(id);

		if (existingclaim.isPresent()) {
			repo.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Test msg...its 404");

		}
	}
}
