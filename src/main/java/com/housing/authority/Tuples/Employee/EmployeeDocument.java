package com.housing.authority.Tuples.Employee;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.housing.authority.AuditModel.AuditModel;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.*;

@ConfigurationProperties(prefix = "file")
@Entity
@Table(name = "employee_document", schema = "housingauthority", catalog = "")
public class EmployeeDocument extends AuditModel {
    @Id
    @Column(name = "documentid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int documentId;
    @Basic
    @Column(name = "employeeid", insertable = false, updatable = false)
    private String employeeId;
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
    @Column(name = "uploaddir", nullable = false, length = 500)
    private String uploadDir;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employeeid")
    @JsonBackReference("employee_document")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(final Employee employee) {
        this.employee = employee;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(final int documentId) {
        this.documentId = documentId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(final String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(final String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentFormat() {
        return documentFormat;
    }

    public void setDocumentFormat(final String documentFormat) {
        this.documentFormat = documentFormat;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(final String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
