package com.housing.authority.Repository;

import com.housing.authority.Tuples.Employee.EmployeeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeDocumentRepository extends JpaRepository<EmployeeDocument, Integer> {
    @Query("Select a from EmployeeDocument a where a.employeeId = ?1 and a.documentType = ?2")
    EmployeeDocument checkDocumentByEmployeeId(String employeeId, String docType);

    @Query("Select a.fileName from EmployeeDocument a where a.employeeId = ?1 and a.documentType = ?2")
    String getUploadDocumentPath(String employeeId, String docType);
}
