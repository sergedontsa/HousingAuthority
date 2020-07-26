package com.housing.authority.Repository.Apartment;

import com.housing.authority.Tuples.Apartment.ApartmentDimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentDimensionRepository extends JpaRepository<ApartmentDimension, String> {
}
