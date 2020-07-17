package com.housing.authority.Repository;

import com.housing.authority.Tuples.Employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Modifying
    @Transactional
    @Query("UPDATE Employee emp SET emp.addressid=:value WHERE emp.employeeId=:employeeid")
    void changeEmployeeAddressIdToNull(@Param("value") String value,@Param("employeeid") String employeeid);

    @Modifying
    @Transactional
    @Query("UPDATE Employee emp SET emp.addressid=:value WHERE emp.employeeId=:employeeid")
    void setAddressId(@Param("value") String value,@Param("employeeid") String employeeid);

    @Modifying
    @Transactional
    @Query("UPDATE Employee emp SET emp.detailid=:value WHERE emp.employeeId=:employeeid")
    void setDetailId(@Param("value") String value,@Param("employeeid") String employeeid);


    @Modifying
    @Transactional
    @Query("UPDATE Employee emp SET emp.addressid=:employeeid WHERE emp.employeeId=:employeeid")
    void setEmployeeAddressId(@Param("employeeid") String employeeid);
}
