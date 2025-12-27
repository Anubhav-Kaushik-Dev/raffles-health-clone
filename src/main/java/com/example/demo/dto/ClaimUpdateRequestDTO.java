package com.example.demo.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

	public class ClaimUpdateRequestDTO {

	    @NotNull
	    @Min(1)
	    private BigDecimal claimAmount;

	    @NotBlank
	    private String status;
	    
	    @NotBlank
	    private String claimNumber;

		public BigDecimal getClaimAmount() {
			return claimAmount;
		}

		public String getClaimNumber() {
			return claimNumber;
		}

		public void setClaimNumber(String claimNumber) {
			this.claimNumber = claimNumber;
		}

		public void setClaimAmount(BigDecimal claimAmount) {
			this.claimAmount = claimAmount;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

	    
	    
	    
	}

