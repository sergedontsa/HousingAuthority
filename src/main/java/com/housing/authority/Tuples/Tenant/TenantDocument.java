package com.housing.authority.Tuples.Tenant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.housing.authority.AuditModel.AuditModel;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tenant_document", schema = "housingauthority", catalog = "")
public class TenantDocument extends AuditModel {
    @Id
    @Column(name = "documentid", nullable = false)
    private int documentId;
    @Basic
    @Column(name = "filename", nullable = false, length = 100)
    private String fileName;
    @Basic
    @Column(name = "documenttype", nullable = false, length = 500)
    private String documentType;
    @Basic
    @Column(name = "documentformat", nullable = false, length = 500)
    private String documentFormat;
    @Basic
    @Column(name = "uploaddir", nullable = true, length = 500)
    private String uploadDir;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tenantid")
    @JsonBackReference("tenant_documnet")
    private Tenant tenant;

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }


    public String getDocumentFormat() {
        return documentFormat;
    }

    public void setDocumentFormat(String documentFormat) {
        this.documentFormat = documentFormat;
    }


    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

}
