package com.housing.authority.Repository.Billing;

import com.housing.authority.Tuples.Billing.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillingRepository extends JpaRepository<Billing, String> {

    @Query(value = "SELECT b FROM Billing b WHERE b.tenant.tenantid=:tenantid ")
    List<Billing> findByTenantId(@Param("tenantid") String tenantid);

    @Query(value = "SELECT b FROM Billing b WHERE b.tenantid=:tenantid AND b.billingid=:id")
    Optional<Billing> findByIdAndTenantId(@Param("tenantid") String tenantid, @Param("id") String id);




}
