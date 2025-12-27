package com.example.demo.specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.entity.Claim;

import jakarta.persistence.criteria.Predicate;

public class ClaimSpecification {

    public static Specification<Claim> filterClaims(
            String status,
            Long customerId
    ) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            if (customerId != null) {
                predicates.add(cb.equal(root.get("customerId"), customerId));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
