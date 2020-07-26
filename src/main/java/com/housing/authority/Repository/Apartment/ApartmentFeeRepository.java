package com.housing.authority.Repository.Apartment;

import com.housing.authority.Tuples.Apartment.ApartmentFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentFeeRepository extends JpaRepository<ApartmentFee, String> {
}
