package com.example.demo.dto;

public class ClaimUpdateResponseDTO {
	

	    private String claimNumber;   // Business identifier
	    private Long customerId;      // Optional, show if needed
	    private Long policyId;        // Optional, show if needed
	    private Double claimAmount;   // Amount approved/requested
	    private String status;        // Current status
	    private String createdAt;     // Optional: formatted date
	    private String updatedAt;     // Optional: formatted date

	    // Constructors
	    public ClaimUpdateResponseDTO() {}

	    public ClaimUpdateResponseDTO(String claimNumber, Long customerId, Long policyId,
	                            Double claimAmount, String status,
	                            String createdAt, String updatedAt) {
	        this.claimNumber = claimNumber;
	        this.customerId = customerId;
	        this.policyId = policyId;
	        this.claimAmount = claimAmount;
	        this.status = status;
	        this.createdAt = createdAt;
	        this.updatedAt = updatedAt;
	    }

	    // Getters and Setters
	    public String getClaimNumber() { return claimNumber; }
	    public void setClaimNumber(String claimNumber) { this.claimNumber = claimNumber; }

	    public Long getCustomerId() { return customerId; }
	    public void setCustomerId(Long customerId) { this.customerId = customerId; }

	    public Long getPolicyId() { return policyId; }
	    public void setPolicyId(Long policyId) { this.policyId = policyId; }

	    public Double getClaimAmount() { return claimAmount; }
	    public void setClaimAmount(Double claimAmount) { this.claimAmount = claimAmount; }

	    public String getStatus() { return status; }
	    public void setStatus(String status) { this.status = status; }

	    public String getCreatedAt() { return createdAt; }
	    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

	    public String getUpdatedAt() { return updatedAt; }
	    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
	}



