package com.housing.authority.Repository.Building;

import com.housing.authority.Tuples.Building.BuildingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface BuildingAddressRepository extends JpaRepository<BuildingAddress, String>
{

}
