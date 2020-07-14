package com.housing.authority.Repository;

import com.housing.authority.Tuples.EmployeeAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, String> {

//    @Query(value = "SELECT FROM ")
//    Optional<EmployeeAddress> getById(@Param("employeeid") String employeeid);
}
