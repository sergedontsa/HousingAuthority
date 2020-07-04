package com.housing.authority.Repository;

import com.housing.authority.Tuples.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<Billing, String> {}
