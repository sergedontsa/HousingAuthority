package com.housing.authority.Repository;

import com.housing.authority.Tuples.EmployeeDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, Integer>
{

}
