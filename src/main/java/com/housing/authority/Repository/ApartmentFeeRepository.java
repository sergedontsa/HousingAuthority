package com.housing.authority.Repository;

import com.housing.authority.Tuples.ApartmentFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentFeeRepository extends JpaRepository<ApartmentFee, String> {
}
