package com.example.demo.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ClaimResponseDTO {

	private String claimNumber;

	private String status;
	
	

	public ClaimResponseDTO() {
	
	}



	public ClaimResponseDTO(String claimNumber, String status) {
	
		this.claimNumber = claimNumber;
		this.status = status;
	}



	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
