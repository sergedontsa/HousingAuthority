package com.housing.authority.Repository;


import com.housing.authority.Tuples.Employee.EmployeeAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, String> {

//    @Query(value = "SELECT FROM ")
//    Optional<EmployeeAddress> getById(@Param("employeeid") String employeeid);
}
