package com.housing.authority.Repository;

import com.housing.authority.Tuples.ApartmentDimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentDimensionRepository extends JpaRepository<ApartmentDimension, String> {
}
