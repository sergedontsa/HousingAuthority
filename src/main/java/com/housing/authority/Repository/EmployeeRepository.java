package com.housing.authority.Repository;

import com.housing.authority.Tuples.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, String> {
}
