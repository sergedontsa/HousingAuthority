package com.housing.authority.Repository;

import com.housing.authority.Tuples.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, String> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Apartment apt SET apt.feeid=:value WHERE apt.apartmentID=:apartmentId")
    void setApartmentFeeId(@Param("value") String value, @Param("apartmentId") String apartmentId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Apartment apt SET apt.dimensionid=:value WHERE apt.apartmentID=:apartmentId")
    void setApartmentDimensionId(@Param("value") String value, @Param("apartmentId") String apartmentId);
}
