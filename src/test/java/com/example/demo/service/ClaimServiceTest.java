package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Exception.BusinessException;
import com.example.demo.dto.ClaimUpdateRequestDTO;
import com.example.demo.entity.Claim;
import com.example.demo.repository.ClaimRepository;

@ExtendWith(MockitoExtension.class)
public class ClaimServiceTest {

	@Mock
	private ClaimRepository repo;

	@InjectMocks
	private ClaimService claimService;

	@Test
	void updateClaim_shouldUpdate_WhenStatusIsPending() {		//HAPPY PATH

		// given existing claim
		Claim claim = new Claim();
		claim.setClaimNumber("C123");
		claim.setStatus("PENDING");

		when(repo.findByClaimNumber("C123")).thenReturn(Optional.of(claim));

		ClaimUpdateRequestDTO request = new ClaimUpdateRequestDTO();
		request.setClaimAmount(BigDecimal.valueOf(5000.25));

		when(repo.save(any(Claim.class))).thenAnswer(i -> i.getArgument(0));

		// when
		Claim updated = claimService.updateClaim("C123", request);

		// then
		assertEquals(BigDecimal.valueOf(5000.25), updated.getClaimAmount());
	}

}
