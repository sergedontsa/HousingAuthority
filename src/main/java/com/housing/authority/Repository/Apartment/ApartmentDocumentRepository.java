package com.housing.authority.Repository.Apartment;


import com.housing.authority.Tuples.Apartment.ApartmentDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository

public interface ApartmentDocumentRepository extends JpaRepository<ApartmentDocument, Integer> {
    @Query(value= "SELECT a FROM ApartmentDocument a WHERE a.apartmentId = ?1")
    List<ApartmentDocument> getAllByApartmentId(String employeeId);

    @Query("Select a from ApartmentDocument a where a.apartmentId = ?1 and a.documentType = ?2")
    ApartmentDocument checkDocumentByApartmentId(String apartmentId, String docType);

    @Query("Select a.fileName from ApartmentDocument a where a.apartmentId= ?1 and a.documentType = ?2")
    String getUploadedDocument(String apartmentId, String docType);
}
