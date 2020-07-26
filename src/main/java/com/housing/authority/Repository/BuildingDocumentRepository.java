package com.housing.authority.Repository;

import com.housing.authority.Tuples.Building.BuildingDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingDocumentRepository extends JpaRepository<BuildingDocument, Integer>
{
    @Query("Select a from BuildingDocument a where a.buildingId = ?1 and a.documentType = ?2")
    BuildingDocument checkDocumentByBuildingId(String buildingId, String docType);

    @Query("Select a.fileName from BuildingDocument a where a.buildingId= ?1 and a.documentType = ?2")
    String getUploadedDocument(String buildingId, String docType);
}
