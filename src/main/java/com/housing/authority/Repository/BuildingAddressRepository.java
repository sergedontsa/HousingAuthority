package com.housing.authority.Repository;

import com.housing.authority.Tuples.BuildingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface BuildingAddressRepository extends JpaRepository<BuildingAddress, Integer>
{

}
