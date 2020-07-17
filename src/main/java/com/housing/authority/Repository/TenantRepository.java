package com.housing.authority.Repository;

import com.housing.authority.Tuples.Tenant.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String>
{
    @Query(value = "SELECT t FROM Tenant t WHERE t.tenantid=:tenantid AND t.apartmentid=:apartmentid AND t.buildingid=:buildingid")
    Optional<Tenant> findByIdAndApartmentIdAndBuildingId(@Param("tenantid") String tenantid, @Param("apartmentid") String apartmentid, @Param("buildingid") String buildingid);
}
