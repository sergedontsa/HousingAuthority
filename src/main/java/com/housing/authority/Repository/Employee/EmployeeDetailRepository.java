package com.housing.authority.Repository.Employee;

import com.housing.authority.Tuples.Employee.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, String>
{
    @Query(value="SELECT e FROM EmployeeDetail e WHERE e.employeeid=:employeeid ")
    EmployeeDetail findByEmployeeId(@Param("employeeid") String employeeid);

    @Query(value="SELECT e FROM EmployeeDetail e WHERE e.employeeid=:employeeid ")
    Optional<EmployeeDetail> findEmployeeDetailByEmployee(@Param("employeeid") String employeeid);

}
