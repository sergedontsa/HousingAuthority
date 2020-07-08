package com.housing.authority.Repository;

import com.housing.authority.Tuples.Employee;
import com.housing.authority.Tuples.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, Integer>
{
    @Query(value="SELECT e FROM EmployeeDetail e WHERE e.employee.employeeId=:employeeid ")
    EmployeeDetail findByEmployeeId(@Param("employeeid") String employeeid);

    Optional<EmployeeDetail> findEmployeeDetailByEmployee(Employee employee);

}
