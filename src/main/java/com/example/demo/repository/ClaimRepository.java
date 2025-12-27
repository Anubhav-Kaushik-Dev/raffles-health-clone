package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Claim;

@Repository
public interface ClaimRepository extends  JpaRepository<Claim,Long>,JpaSpecificationExecutor<Claim>{

	Optional<Claim> findByClaimNumber(String claimNumber);

	

	
}
