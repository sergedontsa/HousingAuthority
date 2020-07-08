package com.housing.authority.Repository;

import com.housing.authority.Tuples.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingRepository extends JpaRepository<Billing, String> {

    @Query(value = "SELECT b FROM Billing b WHERE b.tenant.tenantid=:tenantid ")
    List<Billing> findByTenantId(@Param("tenantid") String tenantid);
}
