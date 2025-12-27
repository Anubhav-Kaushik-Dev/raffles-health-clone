package com.example.demo.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ClaimRequestDTO {

	@NotNull
	private Long customerId;

	@NotNull(message = "Claim amount is required")
	@DecimalMin(value = "0.01", message = "Claim amount must be greater than zero")
	@DecimalMax(value = "1000000", message = "Claim amount cannot exceed 10 lakhs")
	private BigDecimal claimAmount;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(BigDecimal claimAmount) {
		this.claimAmount = claimAmount;
	}

}
