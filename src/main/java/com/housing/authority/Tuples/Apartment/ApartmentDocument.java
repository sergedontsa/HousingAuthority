package com.housing.authority.Tuples.Apartment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.housing.authority.AuditModel.AuditModel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;


@ConfigurationProperties(prefix = "file")
@Entity
@Table(name = "apartment_document", schema = "housingauthority", catalog = "")
public class ApartmentDocument extends AuditModel {
    @Id
    @Column(name = "documentid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int documentId;
    @Basic
    @Column(name = "apartmentid", insertable = false, updatable = false)
    private String apartmentId;
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
    @Column(name = "uploaddir")
    private String uploadDir;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "apartmentid")
    @JsonBackReference("apartment_document")
    private Apartment apartment;

    public ApartmentDocument() {}

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
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

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}
