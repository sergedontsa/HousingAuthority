package com.housing.authority.Repository;

import com.housing.authority.Tuples.Personinformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonInformationRepository extends JpaRepository<Personinformation, String>
{

}
