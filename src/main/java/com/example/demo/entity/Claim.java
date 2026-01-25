package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Claims")
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "claim_Number", nullable = false, unique = true)
	private String claimNumber;

	@Column(name = "customer_ID", nullable = false)
	private Long customerId;

	@Column(name = "policy_ID", nullable = true)
	private Long policyId;

	@Column(name = "claim_amount", nullable = false)
	private BigDecimal claimAmount;

	@Column(name = "status", nullable = false)
	private String status;

	// 🔥 AUDIT FIELDS
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public Claim() {

	}

	public Claim(String claimNumber, Long customerId, Long policyId, BigDecimal claimAmount, String status) {

		this.claimNumber = claimNumber;
		this.customerId = customerId;
		this.policyId = policyId;
		this.claimAmount = claimAmount;
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public BigDecimal getClaimAmount() {
		return claimAmount;
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
