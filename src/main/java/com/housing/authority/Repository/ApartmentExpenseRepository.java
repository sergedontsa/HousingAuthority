package com.housing.authority.Repository;

import com.housing.authority.Tuples.Apartment.ApartmentExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApartmentExpenseRepository extends JpaRepository<ApartmentExpense, String>
{
    @Query(value="SELECT a FROM ApartmentExpense a WHERE a.expenseId=:expenseId AND a.employeeId=:employeeId AND a.apartmentId=:apartmentId AND a.buildingId=:buildingId")
    Optional<ApartmentExpense> findByIdAndEmployeeIdAndApartmentIdAndBuildingId(
            @Param("expenseId") String expenseId,
            @Param("employeeId") String employeeId,
            @Param("apartmentId") String apartmentId,
            @Param("buildingId") String buildingId);
}
