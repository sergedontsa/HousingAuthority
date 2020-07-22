package com.housing.authority.Repository;

import com.housing.authority.Tuples.ContactInformation.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInformationRepository extends JpaRepository<ContactInformation, Integer> {
}
