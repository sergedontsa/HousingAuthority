package com.housing.authority.Repository;

import com.housing.authority.Tuples.Tenant.TenantDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.util.StringUtils;

public interface TenantDocumentRepository extends JpaRepository<TenantDocument, Integer>
{
    @Query("Select a from TenantDocument a where a.tenantId = ?1 and a.documentType = ?2")
    TenantDocument checkDocumentByTenantId(String tenantId, String docType);

    @Query("Select a.fileName from TenantDocument a where a.tenantId = ?1 and a.documentType = ?2")
    String getUploadDocumentPath(String tenantId, String docType);
}
