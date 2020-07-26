package com.housing.authority.Repository.Tenant;

import com.housing.authority.Tuples.Tenant.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, String>
{
    @Query(value = "SELECT t FROM Tenant t WHERE t.tenantid=:tenantid AND t.apartmentid=:apartmentid AND t.buildingid=:buildingid")
    Optional<Tenant> findByIdAndApartmentIdAndBuildingId(@Param("tenantid") String tenantid, @Param("apartmentid") String apartmentid, @Param("buildingid") String buildingid);


    @Query(value = "SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END FROM Tenant t WHERE lower(t.email)=lower(:email) AND lower(t.phonenumber)=lower(:phonenumber)")
    boolean checkIfTenantExist(@Param("email") String email, @Param("phonenumber")String phonenumber );

    @Query(value="SELECT t FROM Tenant t WHERE t.email=:email AND t.phonenumber=:phonenumber")
    Optional<Tenant> findTenantByEmailAndPhoneNumber(@Param("email") String email, @Param("phonenumber")String phonenumber );

    @Modifying
    @Transactional
    @Query(value="UPDATE Tenant t SET t.status=:value WHERE t.tenantid=:tenantId")
    void activateByIdAndAndApartmentIdAndAndBuildingId(@Param("value")String value, @Param("tenantId") String tenantId);

    @Query(value="SELECT t FROM Tenant t WHERE t.tenantid=:tenantId OR t.apartmentid=:apartmentId OR t.buildingid=:buildingId")
    Optional<Tenant> findByIdAndAAndApartmentIdAndAndBuildingId(@Param("tenantId") String tenantId,@Param("apartmentId") String apartmentId, @Param("buildingId") String buildingId);


}
