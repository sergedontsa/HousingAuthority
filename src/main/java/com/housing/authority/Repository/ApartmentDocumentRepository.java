package com.housing.authority.Repository;


import com.housing.authority.Tuples.Apartment.ApartmentDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentDocumentRepository extends JpaRepository<ApartmentDocument, Integer> {
    @Query(value= "SELECT a FROM ApartmentDocument a WHERE a.apartmentId = ?1")
    List<ApartmentDocument> getAllByApartmentId(String employeeId);
}
